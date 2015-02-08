import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int count;
    
    private class StackIterator implements Iterator<Item> {
        private Item[] shuffled;
        private int current;
        private int localCount;

        public StackIterator() {
            current = 0;
            localCount = count;
            shuffled = (Item[]) new Object[localCount];
            
            for (int i = 0; i < localCount; i++) {
                shuffled[i] = queue[i];
            }
            
            StdRandom.shuffle(shuffled);
        }

        public boolean hasNext() {
            return current != localCount;
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new java.util.NoSuchElementException(
                        "tried to next on empty / used iterator");
            }

            return shuffled[current++];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException(
                    "tried to remove inside deque iterator");
        }
    }
    
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
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < count; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("no adding null");
        }
        
        if (count == queue.length) {
            resize(queue.length * 2);
        }
        
        queue[count++] = item;
    }
    
    private int getRandomIndex() {
        return StdRandom.uniform(count);
    }

    public Item dequeue() {
        if (count == 0) {
            throw new java.util.NoSuchElementException("is empty");
        }
        
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
        if (count == 0) {
            throw new java.util.NoSuchElementException("is empty");
        }
        
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