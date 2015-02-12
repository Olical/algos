import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
            p.draw();
        }
        
        Arrays.sort(points);
        
        for (int a = 0; a < N; a++) {
            for (int b = a + 1; b < N; b++) {
                for (int c = b + 1; c < N; c++) {
                    for (int d = c + 1; d < N; d++) {
                        Point pa = points[a];
                        Point pb = points[b];
                        Point pc = points[c];
                        Point pd = points[d];
                        
                        double s1 = pa.slopeTo(pb);
                        double s2 = pa.slopeTo(pc);
                        double s3 = pa.slopeTo(pd);
                        
                        if (s1 == s2 && s2 == s3) {
                            pa.drawTo(pd);
                            StdOut.println(
                                    pa.toString() + " -> " +
                                    pb.toString() + " -> " +
                                    pc.toString() + " -> " +
                                    pd.toString()
                            );
                        }
                    }
                }
            }
        }
        
        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}