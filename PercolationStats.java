import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    // perform trials independent experiments on an n-by-n grid
    private double[] percResults;
    private Percolation p;
    private int t;
    private int n;

    public PercolationStats(int n, int trials) {
        t = trials;
        this.n = n;
        percResults = new double[trials];
        for (int i = 1; i<= trials; i++){
            p = new Percolation(n);
            int[] perm = StdRandom.permutation(n*n);
            for (int k: perm){
                int col = intToCol(k);
                int row = intToRow(k);
                p.open(row, col);
                if (p.percolates()) break;
            }
            percResults[i-1] = ((double)p.numberOfOpenSites()/(double)(n*n));
        }
    }

    private int intToCol(int input) {
        return (input % n)+1;
    }

    private int intToRow(int input){
        return (input / n)+1;
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
        return StdStats.mean(percResults)+((StdStats.stddev(percResults)*1.96)/Math.sqrt(t));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return StdStats.mean(percResults)-((StdStats.stddev(percResults)*1.96)/Math.sqrt(t));
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
        String ciOutput = "[ " + ps.confidenceLo() + ", " + ps.confidenceHi() + " ]";
        System.out.printf(format, mean, ps.mean());
        System.out.printf(format, stddev, ps.stddev());
        System.out.printf(format, confidenceInterval, ciOutput);
    }
}