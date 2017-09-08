import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    // perform trials independent experiments on an n-by-n grid
    private double[] percResults;
    private Percolation p;

    public PercolationStats(int n, int trials) {
        percResults = new double[trials];
        for (int i = 1; i<= trials; i++){
            p = new Percolation(n);
            while (!p.percolates()) {
                int rows[] = StdRandom.permutation(n);
                for (int r: rows){
                    for (int k = 1; k<=n; k++) {
                        int col = StdRandom.uniform(1, n);
                        if (!p.isOpen(r + 1, col)) {
                            p.open(r + 1, col);
                        }
                    }
                }
            }
            percResults[i-1] = ((double)p.numberOfOpenSites()/(double)(n*n));
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percResults);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percResults);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0.0;
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        String format = "%-25s%s%n";
        String mean = "mean";
        String stddev = "stddev";
        String confidenceInterval = "95% confidence interval";
        String ciOutput = "[ " + ps.confidenceLo() + "," + ps.confidenceHi() + " ]";
        System.out.printf(format, mean, ps.mean());
        System.out.printf(format, stddev, ps.stddev());
        System.out.printf(format, confidenceInterval, ciOutput);

    }
}