import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIterNodeList;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item s;
        Node next;
        Node prev;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null)
                throw new NoSuchElementException();
            Item item = current.s;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Node first;
    private Node last;
    private int size;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
    }

    private void checkItem(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
    }

    private void checkSize() {
        if (size == 0)
            throw new NoSuchElementException();
    }
    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        checkItem(item);
        Node n = new Node();
        n.s = item;
        n.next = first;
        if (first != null)
            first.prev = n;
        first = n;
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        checkItem(item);
        Node n = new Node();
        n.s = item;
        if (first == null) {
            first = n;
            size++;
            return;
        }
        else if (last == null){
            last = n;
            first.next = last;
            last.prev = first;
            size++;
            return;
        }
        else {
            Node l = last;
            l.next = n;
            n.prev = l;
            last = n;
            size++;
        }

    }

    // remove and return the item from the front
    public Item removeFirst() {
        checkSize();
        Node next = first.next;
        Node cur = first;
        next.prev = null;
        first = next;
        size--;
        return cur.s;
    }

    // remove and return the item from the end
    public Item removeLast() {
        checkSize();
        Node prev = last.prev;
        Node cur = last;
        prev.next = null;
        last = prev;
        size--;
        return cur.s;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<String> d = new Deque<>();
        d.addFirst("second");
        d.addLast("third");
        d.addLast("fourth");
        d.addFirst("first");

        Iterator<String> iter = d.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        d.removeFirst();
        d.removeLast();

        iter = d.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }
}
