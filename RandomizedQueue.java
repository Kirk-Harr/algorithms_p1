import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int capacity = 10;
    private int size = 0;

    private class ArrayIterator implements Iterator<Item> {
        private int index = 0;
        private final int[] randomIndexes = StdRandom.permutation(size);
        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return s[randomIndexes[index++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[capacity];
    }

    private void increaseCapacity() {
        capacity = capacity*2;
        Item[] k = (Item[]) new Object[capacity];
        for(int i = 0; i < size; i++) {
            k[i] = s[i];
        }
        s = k;
    }


    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (size == capacity)
            increaseCapacity();
        s[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        int index;
        if (size == 1) {
            index = 0;
        }
        else {
            index = StdRandom.uniform(size - 1);
        }
        Item i = s[index];
        for (int j = index; j < size-1; j++) {
            s[j] = s[j+1];
        }
        size--;
        return i;
    }

    // return (but do not remove) a random item
    public Item sample() {
        int index;
        if (size == 1) {
            index = 0;
        }
        else {
        index = StdRandom.uniform(size - 1);
        }
        return s[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // unit testing (optional)
    public static void main(String[] args) {
        RandomizedQueue<String> s = new RandomizedQueue<>();
        s.enqueue("First");
        s.enqueue("Second");
        s.enqueue("Third");
        s.enqueue("Fourth");
        s.enqueue("Fifth");
        s.enqueue("Sixth");
        s.enqueue("Seventh");
        s.enqueue("Eighth");
        s.enqueue("Ninth");
        s.enqueue("Tenth");

        System.out.println(s.isEmpty());
        System.out.println(s.dequeue());
        Iterator<String> iter = s.iterator();
        while (iter.hasNext())
            System.out.print(iter.next()+ ": ");
        System.out.println(s.dequeue());
        System.out.println(s.dequeue());
        System.out.println(s.dequeue());
        System.out.println(s.dequeue());
        iter = s.iterator();
        while (iter.hasNext())
            System.out.print(iter.next()+ ": ");
        System.out.println(s.dequeue());
        System.out.println(s.dequeue());
        System.out.println(s.dequeue());
        System.out.println(s.dequeue());
        System.out.println(s.dequeue());
        System.out.println(s.isEmpty());

    }
}
