import java.util.Arrays;

public class Fast {
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
        Point[] ref = new Point[N];
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
            ref[i] = p;
            p.draw();
        }
        
        for (int a = 0; a < N; a++) {
            Point p = ref[a];
            Arrays.sort(points, p.SLOPE_ORDER);
            double currentSlope;
            double previousSlope = Double.NaN;
            int streak = 0;
            
            for (int i = 0; i < N; i++) {
                if (points[i] == p) {
                    continue;
                }
                
                currentSlope = p.slopeTo(points[i]);
                
                if (Double.isNaN(previousSlope)) {
                    previousSlope = currentSlope;
                }
                
                if (Double.doubleToLongBits(currentSlope) != Double.doubleToLongBits(previousSlope)) {
                    if (streak >= 3) {
                        for (int s = 0; s < streak; s++) {
                            if (s != 0) {
                                StdOut.print(" -> ");
                            }
                            
                            StdOut.print(points[i - (streak - s)].toString());
                        }
                        
                        StdOut.println();
                        p.drawTo(points[i - 1]);
                    }
                    
                    streak = 0;
                    previousSlope = Double.NaN;
                }
                
                streak++;
                previousSlope = currentSlope;
            }
        }
        
        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}