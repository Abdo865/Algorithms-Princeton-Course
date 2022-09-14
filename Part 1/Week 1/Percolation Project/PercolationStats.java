import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats
{
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] fractions;
    private double mean, stddev;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if (trials <= 0 || n <= 0)
            throw new IllegalArgumentException();

        fractions = new double[trials];
        for (int i = 0; i < trials; i++)
        {
            Percolation p = new Percolation(n);
            while (!p.percolates())
            {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                p.open(row, col);
            }
            fractions[i] = p.numberOfOpenSites() * 1.0 / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        mean = StdStats.mean(fractions);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        stddev = StdStats.stddev(fractions);
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return mean - (CONFIDENCE_95 * stddev / Math.sqrt(fractions.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean + (CONFIDENCE_95 * stddev / Math.sqrt(fractions.length));
    }

    // test client (see below)
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats s = new PercolationStats(n, trials);

        System.out.println("mean                    = " + s.mean());
        System.out.println("stddev                  = " + s.stddev());
        System.out.println("95% confidence interval = [" + s.confidenceLo() +
                            ", " + s.confidenceHi() + "]");
    }

}