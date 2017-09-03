public class Percolation {
    // create n-by-n grid, with all sites blocked
    private int[][] grid;
    public Percolation(int n) {
        grid = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                grid[i][j] = 0;
            }
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (grid[row][col] == 0){
            grid[row][col] = 1;
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row][col] == 1;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        return grid[row][col] == 0;
    }

    // number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                if (grid[i][j] == 1){
                    count++;
                }
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    // Allow grid to be accessed
    public int[][] getGrid() {
        return grid;
    }

    // Not sure why we would ever use this...
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    // test client (optional)
    public static void main(String[] args) {}
}
