import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // create n-by-n grid, with all sites blocked
    private WeightedQuickUnionUF grid;
    private int gridSize;
    private boolean[][] openSites;
    private int top = 0;
    private int bottom;
    public Percolation(int n) {
        if (n <= 0) throw new IndexOutOfBoundsException("grid size " + n + " out of bounds");
        gridSize = n;
        grid = new WeightedQuickUnionUF(n*n);
        openSites = new boolean[n][n];
        bottom = n*n-1;
    }

    // Private methods for utility functions
    private int xyTo1D(int row, int col){
        return ((row-1)*gridSize) + col-1;
    }
    private void indexInBounds(int row, int col){
        if (row <= 0 || row > gridSize) throw new IndexOutOfBoundsException("row index " + row + " out of bounds");
        else if (col <= 0 || col > gridSize) throw new IndexOutOfBoundsException("col index " + col + " out of bounds");
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        indexInBounds(row, col);
        int position = xyTo1D(row, col);
        openSites[row-1][col-1] = true;
        if (row == 1) {
            grid.union(position, top);
        }
        if (row == gridSize) {
            grid.union(position, bottom);
        }
        if (col > 1 && isOpen(row, col - 1)) {
            grid.union(position, xyTo1D(row, col - 1));
        }
        if (col < gridSize && isOpen(row, col + 1)) {
            grid.union(position, xyTo1D(row, col + 1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            grid.union(position, xyTo1D(row - 1, col));
        }
        if (row < gridSize && isOpen(row + 1, col)) {
            grid.union(position, xyTo1D(row + 1, col));
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        indexInBounds(row, col);
        return openSites[row-1][col-1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        indexInBounds(row, col);
        return grid.connected(top, xyTo1D(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return (gridSize*gridSize)-grid.count();
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(top, bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
