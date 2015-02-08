public class Subset {
    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        
        for (int i = 0; i < count; i++) {
            StdOut.println(rq.dequeue());
        }
    }
}