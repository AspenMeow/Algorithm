/*
Algorithm I Week 2 Homework
Linked List Data Structure
 */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int N; //size
    private node<Item> first; // head of the linked list

    private class node<Item> {
        private Item value;
        private node<Item> next;

        public node(Item i) {
            value = i;
        }
    }

    // construct an empty deque
    public Deque() {
        N = 0;
        first = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("argument cannot be null");
        node newnd = new node(item);
        newnd.next = first;
        first = newnd;
        N++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("argument cannot be null");
        node nd = first;
        node newnd = new node(item);
        if (nd == null) {
            first = newnd;
            N++;
            return;
        }
        while (nd.next != null) nd = nd.next;
        nd.next = newnd;
        N++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (N == 0) throw new IllegalArgumentException("argument cannot be null");
        Item rm = first.value;
        first = first.next;
        N--;
        return rm;

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (N == 0) throw new IllegalArgumentException("argument cannot be null");
        if (N == 1) return removeFirst();
        node nd = first;
        while (nd.next.next != null) nd = nd.next;
        Item rm = (Item) nd.next.value;
        nd.next = null;
        N--;
        return rm;


    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>();
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        private node nd = first;

        public boolean hasNext() {
            return nd != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item i = (Item) nd.value;
            nd = nd.next;
            return i;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Character> dq = new Deque<Character>();
        dq.addFirst('a');
        dq.addLast('g');
        dq.addFirst('e');
        dq.addFirst('t');
        dq.removeLast();
        StdOut.println(dq.size());
        for (Character i : dq) StdOut.println(i);
    }


}
