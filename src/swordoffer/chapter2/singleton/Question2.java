package swordoffer.chapter2.singleton;

import java.io.*;

/**
 * @author: Hello World
 * @date: 2018/6/3 21:20
 * <p>
 * 面试题2: 实现Singleton模式
 * 设计一个类，我们只能生产该类的一个实例
 * <p>
 * 静态方法与静态内部类只有被调用时才会加载
 * <p>
 * 参考: https://itimetraveler.github.io/2016/09/08/%E3%80%90Java%E3%80%91%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F%EF%BC%9A%E6%B7%B1%E5%85%A5%E7%90%86%E8%A7%A3%E5%8D%95%E4%BE%8B%E6%A8%A1%E5%BC%8F/
 */
public class Question2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
        oos.writeObject(Singleton3.getInstance());
        File file = new File("tempFile");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Singleton3 newInstance = (Singleton3) ois.readObject();
        System.out.println(newInstance == Singleton3.getInstance());
    }
}

/**
 * 饿汉式
 * 顾名思义，饿汉式就是在第一次引用该类的时候就创建对象实例，而不管实际是否需要创建。
 */
class Singleton1 {
    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}

/**
 * 懒汉式(双重校验锁)
 * 可用于多线程环境下
 * volatile这个关键字有两层语义：
 * 第一层语义就是可见性。可见性指的是在一个线程中对该变量的修改会马上由工作内存（Work Memory）
 * 写回主内存（Main Memory），所以会马上反应在其它线程的读取操作中。顺便一提，工作内存和主内存
 * 可以近似理解为实际电脑中的高速缓存和主存，工作内存是线程独享的，主存是线程共享的。
 * volatile的第二层语义是禁止指令重排序优化。大家知道我们写的代码（尤其是多线程代码），由于编译
 * 器优化，在实际执行的时候可能与我们编写的顺序不同。编译器只保证程序执行结果与源代码相同，却不
 * 保证实际指令的顺序与源代码相同。这在单线程看起来没什么问题，然而一旦引入多线程，这种乱序就可
 * 能导致严重问题。volatile关键字就可以从语义上解决这个问题。
 * <p>
 * 禁止指令重排优化这条语义直到jdk1.5以后才能正确工作。此前的JDK中即使将变量声明为volatile也
 * 无法完全避免重排序所导致的问题。所以，在jdk1.5版本前，双重检查锁形式的单例模式是无法保证线
 * 程安全的
 * <p>
 * new Singleton()并非是一个原子操作，它有多条指令组成：
 * memory = allocate(); //1：分配对象的内存空间
 * ctorInstance(memory); //2：初始化对象
 * instance = memory; //3：设置instance指向刚分配的内存地址
 * <p>
 * 但是经过重排序后：
 * memory = allocate(); //1：分配对象的内存空间
 * instance = memory; //3：设置instance指向刚分配的内存地址，此时对象还没被初始化
 * ctorInstance(memory); //2：初始化对象
 * <p>
 * 指令重排之后，instance指向分配好的内存放在了前面，而这段内存的初始化被排在了后面，
 * 在线程A初始化完成这段内存之前，线程B虽然进不去同步代码块，但是在同步代码块之前的
 * 判断就会发现instance不为空，此时线程B获得instance对象进行使用就可能发生错误。
 * <p>
 * 此种方法在反序列化时会破坏单例
 * 普通的Java类的反序列化过程中，会通过反射调用类的默认构造函数来初始化对象。所以，
 * 即使单例中构造函数是私有的，也会被反射给破坏掉。由于反序列化后的对象是重新new出
 * 来的，所以这就破坏了单例。
 */
class Singleton2 implements Serializable {
    private static volatile Singleton2 INSTANCE = null;

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton2.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton2();
                }
            }
        }
        return INSTANCE;
    }

    /**
     *  防止序列化破坏单例模式
     *  http://www.hollischuang.com/archives/1144
     */
    private Object readResolve() {
        return INSTANCE;
    }
}

/**
 * 懒汉式(静态内部类)
 * <p>
 * 注意: 静态内部类只有在被调用时才会被加载
 * 因为在JVM进行类加载的时候他会保证数据是同步的，我们可以这样实现：采用内部类，在这个内部类
 * 里面去创建对象实例。这样的话，只要应用中不使用内部类 JVM 就不会去加载这个单例类，也就不会
 * 创建单例对象，从而实现「懒汉式」的延迟加载和线程安全。
 * <p>
 * 类的初始化是由ClassLoader完成的,至于为什么ClassLoader加载类是线程安全的，这里可以先直接
 * 回答：ClassLoader的loadClass方法在加载类的时候使用了synchronized关键字。也正是因为这样,
 * 除非被重写，这个方法默认在整个装载过程中都是同步的（线程安全的）
 *
 * 此种方法在反序列化时会破坏单例
 */
class Singleton3 implements Serializable{
    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Singleton3 INSTANCE = new Singleton3();
    }

    /**
     *  防止序列化破坏单例模式
     */
    private Object readResolve() {
        return SingletonHolder.INSTANCE;
    }
}

/**
 * 枚举
 * 如果把枚举类进行反序列化，会发现他也是使用了static final来修饰每一个枚举项
 * <p>
 * 枚举来实现单例可以防止被反序列化
 * Java的序列化机制针对枚举类型是特殊处理的。简单来讲，在序列化枚举类型时，只会
 * 存储枚举类的引用和枚举常量的名称。随后的反序列化的过程中，这些信息被用来在运
 * 行时环境中查找存在的枚举类型对象。这样你就可以在同一个运行时环境中反序列化枚
 * 举常量，并且你会得到同一个实例对象。枚举类型的序列化机制保证只会查找已经存在
 * 的枚举类型实例，而不是创建新的实例
 *
 * 经过enum关键词修饰的类继承Enum，而Enum实现了Serializable接口
 * http://www.hollischuang.com/archives/2498
 * http://www.hollischuang.com/archives/197
 */
enum Singleton4 implements MySingleton {
    INSTANCE {
        @Override
        public void doSomething() {

        }
    };

    public static MySingleton getInstance() {
        return Singleton4.INSTANCE;
    }
}