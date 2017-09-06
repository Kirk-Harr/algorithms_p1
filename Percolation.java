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
        return ((row-1)*gridSize) + col-1;
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
        return position != grid.find(position);
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row == 1 && col == 1){
            return true;
        }
        else {
            int position = xyTo1D(row, col);
            int leftNeighbor = xyTo1D(row, col - 1);
            int rightNeighbor = xyTo1D(row, col + 1);
            int upperNeighbor = xyTo1D(row - 1, col);
            return (grid.connected(position, leftNeighbor) || grid.connected(position, rightNeighbor) || grid.connected(position, upperNeighbor));
        }
    }

    // number of open sites
    public int numberOfOpenSites() {
        return (gridSize*gridSize)-grid.count();
    }

    // does the system percolate?
    public boolean percolates() {
        int i = 1;
        boolean percolation = false;
        boolean loopContinue = true;
        while (i <=gridSize && loopContinue) {
            for (int j = 1; j<=gridSize; j++){
                if (i == gridSize) {
                    if (isOpen(i,j) && isFull(i,j)) {
                        percolation = true;
                        i++;
                        break;
                    }
                }
                else if(isOpen(i,j) && isFull(i,j)){
                    i++;
                }
                else if(j==gridSize){
                    loopContinue = false;
                }
            }
        }
        return percolation;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);

        p.open(1,2);
        p.open(2,2);
        p.open(3,2);
        System.out.println("Count is " + p.numberOfOpenSites());

        System.out.println("Percolation is " + p.percolates());

    }
}
