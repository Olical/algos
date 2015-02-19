import java.util.Arrays;

public class Board {
    private int n;
    private char[] board;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        n = blocks.length;
        board = new char[n];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                board[nth(x, y)] = (char) blocks[x][y];
            }
        }
    }

    private int nth(int x, int y) {
        return y + n * x;
    }

    // board dimension N
    public int dimension() {
        return n;
    }

    // number of blocks out of place
    public int hamming() {
        return 0; // todo
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return 0; // todo
    }

    // is this board the goal board?
    public boolean isGoal() {
        boolean sorted = true;

        for (int i = 0; i < board.length - 1; i++) {
            if (board[i] > board[i + 1]) {
                sorted = false;
                break;
            }
        }

        return sorted;
    }

    // a twin is a board that is obtained by exchanging two adjacent blocks in
    // the same row
    public Board twin() {
        return this; // todo
    }

    // does this board equal y?
    public boolean equals(Object x) {
        if (x == this)
            return true;
        if (x == null)
            return false;
        if (x.getClass() != this.getClass())
            return false;
        Board that = (Board) x;
        return Arrays.equals(this.board, that.board);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null; // todo
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                s.append(String.format("%2d ", board[nth(x, y)]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
    }
}