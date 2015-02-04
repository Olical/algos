public class Percolation {
    private int size;
    private int width;
    private int height;
    private WeightedQuickUnionUF sites;
    private WeightedQuickUnionUF backwash;
    private boolean[] openSites;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("size has to be > 0");
        }

        size = N * N;
        width = N;
        height = N;
        sites = new WeightedQuickUnionUF(size + 2);
        backwash = new WeightedQuickUnionUF(size + 1);
        openSites = new boolean[size];
    }

    private boolean isValidSite(int row, int column) {
        return row > 0 && column > 0 && row <= height && column <= width;
    }
    
    private void union(int row, int column, int to) {
        if (isValidSite(row, column) && isOpen(row, column)) {
            int from = sitePos(row, column);
            sites.union(from, to);
            backwash.union(from, to);
        }
    }

    public void open(int row, int column) {
        if (!isValidSite(row, column)) {
            throw new java.lang.IndexOutOfBoundsException("site out of bounds");
        }

        int site = sitePos(row, column);

        if (!openSites[site]) {
            openSites[site] = true;

            union(row + 1, column, site);
            union(row - 1, column, site);
            union(row, column + 1, site);
            union(row, column - 1, site);

            if (row == 1) {
                sites.union(size, site);
                backwash.union(size, site);
            }

            if (row == height) {
                sites.union(size + 1, site);
            }
        }
    }

    public boolean isOpen(int row, int column) {
        if (!isValidSite(row, column)) {
            throw new java.lang.IndexOutOfBoundsException("site out of bounds");
        }

        int i = sitePos(row, column);
        return openSites[i];
    }

    private int sitePos(int row, int column) {
        return (column - 1) + width * (row - 1);
    }

    public boolean isFull(int row, int column) {
        if (!isValidSite(row, column)) {
            throw new java.lang.IndexOutOfBoundsException("site out of bounds");
        }

        return sites.connected(sitePos(row, column), size) && backwash.connected(sitePos(row, column), size);
    }

    public boolean percolates() {
        return sites.connected(size, size + 1);
    }
}
