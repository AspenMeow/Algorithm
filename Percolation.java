/* *****************************************************************************
 *  Name:    Ada Lovelace
 *  NetID:   alovelace
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private boolean[] sites;
    private int N; // matrix size n*n
    private int num; // number of open site

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        sites = new boolean[n * n + 1];
        sites[0] = true;  //virtual head
        for (int i = 1; i <= n * n; i++) sites[i] = false;
        N = n;
        uf = new WeightedQuickUnionUF(n * n + 1);
        for (int i = 1; i <= n; i++) uf.union(0, i); // connect virtual head to top row

    }

    //helper to convert row, col to sites index
    private int siteindx(int r, int c) {
        if (r < 1 || c < 1 || r > N || c > N)
            throw new IllegalArgumentException("wrong index out of bound");
        return (r - 1) * N + c; //1-1 is the upper left
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > N || col > N)
            throw new IllegalArgumentException("index out of bound");
        return sites[siteindx(row, col)];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > N || col > N)
            throw new IllegalArgumentException("index out of bound");
        if (isOpen(row, col)) return;
        sites[siteindx(row, col)] = true;
        num++;
        int q = siteindx(row, col);
        //check top/down/right/left if open union if open and not connected
        if (col > 1) checkunion(row, col - 1, q); //left
        if (col < N) checkunion(row, col + 1, q); // right
        if (row > 1) checkunion(row - 1, col, q); // up
        if (row < N) checkunion(row + 1, col, q); // down

    }

    //help function check if open union if open and not connected
    private void checkunion(int r, int c, int q) {
        if (isOpen(r, c)) {
            int p = siteindx(r, c);
            if (uf.find(p) != uf.find(q)) {
                uf.union(p, q);
            }

        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > N || col > N)
            throw new IllegalArgumentException("index out of bound");
        return uf.find(siteindx(row, col)) == uf.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return num;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int c = 1; c <= N; c++) {
            if (isFull(N, c)) return true;
        }
        return false;
    }


    public static void main(String[] args) {
        int cnt = 20;
        //for (int i = 0; i < 15; i++) {

        // StdOut.println(r + " " + c);
        // }
        Percolation perco = new Percolation(cnt);
        while (!perco.percolates()) {
            int r = StdRandom.uniform(cnt) + 1;
            int c = StdRandom.uniform(cnt) + 1;
            perco.open(r, c);

        }
        StdOut.println(perco.numberOfOpenSites());

    }
}
