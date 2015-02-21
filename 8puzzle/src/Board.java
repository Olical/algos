import java.util.Arrays;

public class Board {
    private int n;
    private char[] board;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        n = blocks.length;
        board = new char[n * n];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                board[nth(x, y)] = (char) blocks[x][y];
            }
        }
    }

    private Board(int size, char[] blocks) {
        n = size;
        board = blocks;
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
        int h = 0;

        for (int i = 0; i < n * n; i++) {
            if (i != board[i]) {
                h++;
            }
        }

        return h;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int m = 0;

        for (int i = 0; i < n * n; i++) {
            if (i != board[i]) {
                m += Math.abs(i - board[i]);
            }
        }

        return m;
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
        // If center top is blank, use second row.
        // Otherwise if the left top is not 0, use the two top left.
        // If that's zero then the two top right are correct.
        // Presuming a 3x3 grid, but the rules will apply to all other sizes
        // greater than it.
        if (board[1] == 0) {
            return swap(0, 1, 1, 1);
        } else if (board[0] != 0) {
            return swap(0, 0, 1, 0);
        } else {
            return swap(1, 0, 2, 0);
        }
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
        Stack<Board> neighbors = new Stack<Board>();
        int x = 0;
        int y = 0;

        find: for (; x < n; x++) {
            for (; y < n; y++) {
                if (board[nth(x, y)] == 0) {
                    break find;
                }
            }
        }

        if (x != 0) {
            neighbors.push(swap(x, y, x - 1, y));
        }

        if (x != n - 1) {
            neighbors.push(swap(x, y, x + 1, y));
        }

        if (y != 0) {
            neighbors.push(swap(x, y, x, y - 1));
        }

        if (y != n - 1) {
            neighbors.push(swap(x, y, x, y + 1));
        }

        return neighbors;
    }

    private Board swap(int x1, int y1, int x2, int y2) {
        char[] swapped = board.clone();
        int l = nth(x1, y1);
        int r = nth(x2, y2);

        swapped[l] = board[r];
        swapped[r] = board[l];

        return new Board(n, swapped);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                s.append(String.format("%2d ", (int) board[nth(x, y)]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        int[][] m = { { 3, 1, 0 }, { 4, 2, 5 }, { 7, 8, 6 } };
        Board b = new Board(m);
        StdOut.println(b);

        for (Board bi : b.neighbors()) {
            StdOut.println(bi);
        }
    }
}