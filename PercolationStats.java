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
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private int trialtimes;
    private double[] opensites;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        opensites = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perco = new Percolation(n);
            while (!perco.percolates()) {
                int r = StdRandom.uniform(n) + 1;
                int c = StdRandom.uniform(n) + 1;
                perco.open(r, c);

            }
            double ttl = n * n;
            double stat = perco.numberOfOpenSites() / ttl;
            opensites[i] = stat;
        }
        trialtimes = trials;
    }

    // sample mean of percolation threshold
    public double mean() {
        double sum = 0;
        for (int i = 0; i < trialtimes; i++) sum += opensites[i];
        return sum / trialtimes;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double meanval = mean();
        double sd = 0;
        for (int i = 0; i < trialtimes; i++)
            sd += (opensites[i] - meanval) * (opensites[i] - meanval);
        return Math.sqrt(sd / (trialtimes - 1));

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double meanval = mean();
        double std = stddev();
        return meanval + 1.96 * std / Math.sqrt(trialtimes);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double meanval = mean();
        double std = stddev();
        return meanval - 1.96 * std / Math.sqrt(trialtimes);
    }


    public static void main(String[] args) {
        Stopwatch watch = new Stopwatch();
        PercolationStats ps = new PercolationStats(200, 100);
        StdOut.println(ps.mean());
        StdOut.println(ps.stddev());
        StdOut.println(ps.confidenceLo() + " , " + ps.confidenceHi());
        double time = watch.elapsedTime();
        StdOut.println("seconds:" + time);


    }
}
