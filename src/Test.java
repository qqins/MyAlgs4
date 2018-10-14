public class Test {
    private int[] array = new int[5];
    private int N = 0;

    public void push(int item) {
        if (N == array.length) {
            resize(2 * array.length);
        }
        array[N++] = item;
    }

    private void resize(int size) {
        int[] temp = new int[size];
        for (int i = 0; i < N; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }
}
