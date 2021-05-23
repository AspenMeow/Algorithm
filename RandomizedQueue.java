/*
Algorithm I Week 2 Homework
Resizing Array Data Structure
 */


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N;
    private int Capacity;
    private Item[] arr;

    // construct an empty randomized queue
    public RandomizedQueue() {
        N = 0;
        Capacity = 1;
        arr = (Item[]) new Object[Capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    //resize
    private void resize(int size) {
        Capacity = size;
        Item[] newarr = (Item[]) new Object[size];
        for (int i = 0; i < N; i++) {
            newarr[i] = arr[i];
        }
        arr = newarr;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("argument cannot be null");
        arr[N] = item;
        N++;
        if (N == Capacity) resize(N * 2);
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int r = StdRandom.uniform(N);
        Item i = arr[r];
        for (int j = r; j < N - 1; j++) arr[j] = arr[j + 1];
        arr[N - 1] = null;
        N--;
        if (N > 0 && N == Capacity / 4) resize(Capacity / 2);
        return i;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int r = StdRandom.uniform(N);
        return arr[r];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomQueueIterator<Item>();
    }

    private class RandomQueueIterator<Item> implements Iterator<Item> {
        private Item[] newarry;
        private int n;

        public RandomQueueIterator() {
            newarry = (Item[]) arr.clone();
            n = N;

        }

        public boolean hasNext() {
            return n > 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int r = StdRandom.uniform(n);
            Item item = newarry[r];
            for (int j = r; j < n - 1; j++) newarry[j] = newarry[j + 1];
            newarry[n - 1] = null;
            n--;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Character> tst = new RandomizedQueue<Character>();
        tst.enqueue('a');
        tst.enqueue('b');
        tst.enqueue('c');
        tst.enqueue('d');
        for (Character c : tst) StdOut.println(c);
        StdOut.println(tst.size());
    }

}
