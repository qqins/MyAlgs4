package algorithms4.chapter2.priorityqueue;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/6/13 21:04
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq = (Key[]) new Comparable[2];
    private int N = 0;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        Key[] temp = (Key[]) new Comparable[max];
        for (int i = 0; i < N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public void insert(Key v) {
        if (N == pq.length) {
            resize(2 * pq.length);
        }
        pq[N] = v;
        swim(++N);
    }

    public Key delMax() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        Key max = pq[0];
        /**
         * 在书上的写法中：之所以不用exch(1,N); pq[N]==null;
         * 交换的是1与N，然后将N即总数减1
         * 再将之前N指向的位置置为空
         * 或者这样写：exch(1,N); pq[N]=null; N--;
         *
         * 以下的写法是为了利用0下标，其中N始终指向一个空位置，即N比实际
         * 长度大1，这也是为啥exch和less中i和j都要减1
         *
         * 以下的exch实际交换的是首尾元素
         */
        exch(1, N);
        pq[--N] = null;
        sink(1);
        if (N > 0 && N == pq.length / 4) {
            resize(pq.length / 2);
        }
        return max;
    }

    private boolean less(int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < N; i++) {
            sb.append(pq[i] + ", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ();
        Random randomInt = new Random();
        for (int i = 0; i < 10; i++) {
            maxPQ.insert(randomInt.nextInt(100));
        }
        System.out.println(maxPQ.toString());
        maxPQ.delMax();
        maxPQ.delMax();
        maxPQ.delMax();
        System.out.println(maxPQ.toString());
    }
}
