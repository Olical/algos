public class PercolationStats {
    private double[] results;
    
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("N and T must be more than zero");
        }
        
        Percolation p;
        this.results = new double[T];
        int x, y;
        double attempts;
        
        for (int i = 0; i < T; i++) {
            p = new Percolation(N);
            attempts = 0;
            
            while (!p.percolates()) {
                x = StdRandom.uniform(N) + 1;
                y = StdRandom.uniform(N) + 1;
                
                if (!p.isOpen(y, x)) {
                    p.open(y, x);
                    attempts++;
                }
            }
            
            this.results[i] = attempts / (N * N);
        }
    }
    
    public double mean() {
        return StdStats.mean(this.results);
    }
    
    public double stddev() {
        return StdStats.stddev(this.results);
    }
    
    public double confidenceLo() {
        return this.mean() - (1.96 * this.stddev()) / java.lang.Math.sqrt(this.results.length);
    }
    
    public double confidenceHi() {
        return this.mean() + (1.96 * this.stddev()) / java.lang.Math.sqrt(this.results.length);
    }
    
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);
        StdOut.println();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}
