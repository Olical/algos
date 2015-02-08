import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int count;
    
    private class StackIterator implements Iterator<Item> {
        private Item[] shuffled;
        private int current;

        @SuppressWarnings("unchecked")
        public StackIterator() {
            current = 0;
            shuffled = (Item[]) new Object[count];
            
            for (int i = 0; i < count; i++) {
                shuffled[i] = queue[i];
            }
            
            StdRandom.shuffle(shuffled);
        }

        public boolean hasNext() {
            return current != count;
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new java.util.NoSuchElementException(
                        "tried to next on empty / used iterator");
            }

            return queue[current++];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException(
                    "tried to remove inside deque iterator");
        }
    }
    
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }
    
    private void resize(int capacity) {
        @SuppressWarnings("unchecked")
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < count; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    public void enqueue(Item item) {
        if (count == queue.length) {
            resize(queue.length * 2);
        }
        
        queue[count++] = item;
    }
    
    private int getRandomIndex() {
        int n;
        
        if (count > 1) {
            n = StdRandom.uniform(0, count - 1);
        }
        else {
            n = 0;
        }
        
        return n;
    }

    public Item dequeue() {
        int n = getRandomIndex();
        Item selected = queue[n];
        
        if (n == count -1) {
            queue[n] = null;
        }
        else {
            queue[n] = queue[count - 1];
            queue[count - 1] = null;
        }

        count--;
        
        if (count > 0 && count == queue.length / 4) {
            resize(queue.length / 2);
        }
        
        return selected;
    }

    public Item sample() {
        int n = getRandomIndex();
        return queue[n];
    }

    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        
        rq.enqueue(0);
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        
        for (int i : rq) {
            StdOut.println(i);
        }
        
        StdOut.println("--------");
        
        while (!rq.isEmpty()) {
            StdOut.println(rq.dequeue());
        }
    }
}