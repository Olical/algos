import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private int count;
    private Node first;
    private Node last;

    private class Node {
        private Item value;
        private Node next;
        private Node prev;
    }

    private class NodeIterator implements Iterator<Item> {
        private Node current;

        public NodeIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException(
                        "tried to next on empty / used iterator");
            }

            Node n = current;
            current = current.next;
            return n.value;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException(
                    "tried to remove inside deque iterator");
        }
    }

    public Deque() {
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("tried to add null first");
        }

        count++;
        Node n = new Node();
        n.value = item;
        n.next = first;

        if (first != null) {
            first.prev = n;
        }

        first = n;

        if (last == null) {
            last = first;
        }
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("tried to add null last");
        }

        count++;
        Node n = new Node();
        n.value = item;
        n.prev = last;

        if (last != null) {
            last.next = n;
        }

        last = n;

        if (first == null) {
            first = last;
        }
    }

    public Item removeFirst() {
        if (count == 0) {
            throw new java.util.NoSuchElementException(
                    "tried to remove first from empty");
        }

        count--;
        Node n = first;
        first = n.next;

        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }

        return n.value;
    }

    public Item removeLast() {
        if (count == 0) {
            throw new java.util.NoSuchElementException(
                    "tried to remove last from empty");
        }

        count--;
        Node n = last;
        last = n.prev;

        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }

        return n.value;
    }

    public Iterator<Item> iterator() {
        return new NodeIterator();
    }

    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(1);
        d.addLast(2);
        d.addLast(3);
        d.addFirst(0);

        for (int i : d) {
            StdOut.println(i);
        }

        while (!d.isEmpty()) {
            StdOut.println(d.removeLast());
        }
    }
}