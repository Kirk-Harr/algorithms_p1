import java.util.Iterator;

public class Board {
    private int[] board;
    private int n;

    private class BoardIterable<Board extends Comparable<Board>> implements Iterable<Board> {
        private Board[] boards;
        private class BoardIterator implements Iterator<Board> {
            int index;
            public BoardIterator() {
                index = 0;
            }
            @Override
            public boolean hasNext() {
                return index < (n*n)-1;
            }

            @Override
            public Board next() {
                return boards[index++];
            }
        }
        @Override
        public Iterator<Board> iterator() {
            return new BoardIterator();
        }

    }
    // construct a board from an n-by-n array of blocks
    public Board(int[][] blocks) {
        // (where blocks[i][j] = block in row i, column j)
        n = blocks[0].length;
        board = new int[n*n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int pos = (i*n)+j;
                board[pos] = blocks[i][j];
            }
        }
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
                int pos = (i*n)+j;
                if (board[pos] != 0 && board[pos] != pos+1)
                    count++;
            }
        }
        return count;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int total = 0;
        for (int i = 0; i < board.length; i++) {
            int cur = board[i];

        }
        return total;
    }

    // is this board the goal board?
    public boolean isGoal() {
        boolean goal = true;
        for (int i = 0; i < (n*n)-2; i++) {
            if (board[i] != i+1)
                goal = false;
        }
        if (board[(n*n)-1] != 0)
            goal = false;
        return goal;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        return null;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new BoardIterable();
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        String output = "";
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                output = output.concat(board[(i*n)+j]+"  ");
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
    }
}
