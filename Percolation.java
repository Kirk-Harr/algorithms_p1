import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // create n-by-n grid, with all sites blocked
    private WeightedQuickUnionUF grid;
    private int gridSize;
    public Percolation(int n) {
        if (n <= 0) throw new IndexOutOfBoundsException("grid size " + n + " out of bounds");
        gridSize = n;
        grid = new WeightedQuickUnionUF(n*n);
    }

    // Private methods for utility functions
    private int xyTo1D(int row, int col){
        return ((row-1)*gridSize) + col;
    }
    private WeightedQuickUnionUF getGrid() {
        return grid;
    }

    private void indexInBounds(int row, int col){
        if (row <= 0 || row > gridSize) throw new IndexOutOfBoundsException("row index " + row + " out of bounds");
        else if (col <= 0 || col > gridSize) throw new IndexOutOfBoundsException("col index " + col + " out of bounds");
    }
    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        indexInBounds(row, col);
        int position = xyTo1D(row, col);
        grid.union(position, 0);
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        indexInBounds(row, col);
        int position = xyTo1D(row, col);
        return position-1 != grid.find(position-1);
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        int position = xyTo1D(row, col);
        int leftNeighbor = xyTo1D(row, col-1);
        int rightNeighbor = xyTo1D(row, col+1);
        int upperNeighbor = xyTo1D(row-1, col);
        return (grid.connected(position, leftNeighbor) || grid.connected(position, rightNeighbor) || grid.connected(position, upperNeighbor));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(20);
        p.open(1,1);
        p.open(1,3);
        System.out.println("True is " + p.getGrid().connected(1,3));
    }
}
