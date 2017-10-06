import edu.princeton.cs.algs4.StdRandom;

public class Board {
    private int[][] board;
    private int n;


    // construct a board from an n-by-n array of blocks
    public Board(int[][] blocks) {
        // (where blocks[i][j] = block in row i, column j)
        n = blocks[0].length;
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = blocks[i][j];
            }
        }
    }

    // Swap two blocks
    private void swap(int i, int j, int i2, int j2) {
        int temp = board[i][j];
        board[i][j] = board[i2][j2];
        board[i2][j2] = temp;
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of blocks out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0 && board[i][j] != ((n*i)+j+1))
                    count++;
            }
        }
        return count;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int total = 0;
        for (int i = 0; i < board.length; i++) {

        }
        return total;
    }

    // is this board the goal board?
    public boolean isGoal() {
        boolean goal = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int pos = (i*n)+j+1;
                if (board[i][j] != pos && board[i][j] != 0) {
                    goal = false;
                }
                else if (board[i][j] == 0 && i != n-1 && j != n-1) {
                    goal = false;
                }
            }
        }
        return goal;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        Board dupe = new Board(board);
        int i = StdRandom.uniform(n-1);
        int j = StdRandom.uniform(n-1);
        int i2 = i;
        int j2 = StdRandom.uniform(n-1);
        dupe.swap(i,j,i2,j2);
        return dupe;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        String output = "";
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                output = output.concat(board[i][j]+"  ");
            }
            output = output.concat(System.getProperty("line.separator"));
        }
        return output;
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        int[][] test = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board b = new Board(test);
        System.out.println(b.dimension());
        System.out.println(b.hamming());
        System.out.println(b.isGoal());
        System.out.println(b);
        Board c = b.twin();
        System.out.println(c);
        System.out.println(c.hamming());
        System.out.println(c.isGoal());
    }
}
