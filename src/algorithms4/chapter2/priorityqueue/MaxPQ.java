package algorithms4.chapter2.priorityqueue;

import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/6/13 21:04
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        /**
         * 之所以不用exch(1,N); pq[N]==null;
         * 交换的是1与N，然后将N即总数减1
         * 再将之前N指向的位置置为空
         * 或者这样写：exch(1,N); pq[N]=null; N--;
         */
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
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
        for (int i = 0; i < N + 1; i++) {
            sb.append(pq[i] + ", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ(10);
        Random randomInt = new Random();
        for (int i = 0; i < 10; i++) {
            maxPQ.insert(randomInt.nextInt(100));
        }
        System.out.println(maxPQ.toString());
        maxPQ.delMax();
        System.out.println(maxPQ.toString());
    }
}
