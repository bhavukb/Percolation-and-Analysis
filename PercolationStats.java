import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats
{
    private final double[] x;
    private final int t;

    public PercolationStats(int n, int trials)
    {
        if (n < 1 || trials < 1)
            throw new IllegalArgumentException("IllegalArgumentException");

        t = trials;
        this.x = new double[trials];
        int N = n * n;
        for (int i = 0; i < trials; i++)
        {
            this.x[i] = runSim(n);
            this.x[i] /= N;
        }
    }

    private int runSim(int n)
    {
        Percolation per = new Percolation(n);
        int N = n*n;
        int[] ran = new int[N];
        for (int i = 0; i < N; i++)
            ran[i] = i;
        StdRandom.shuffle(ran);
        int i = 0;

        while (!per.percolates())
        {
            int row = (ran[i] / n) + 1;
            int column = (ran[i] % n) + 1;
            per.open(row, column);
            i++;
        }
        return per.numberOfOpenSites();
    }

    public double mean()
    {
        return StdStats.mean(x);
    }

    public double stddev()
    {
        return StdStats.stddev(x);
    }

    public double confidenceLo()
    {
        double m = this.mean();
        double s = this.stddev();
        return (m  - ((1.96 * s) / Math.sqrt(this.t)));
    }

    public double confidenceHi()
    {
        double m = this.mean();
        double s = this.stddev();
        return (m  + ((1.96 * s) / Math.sqrt(this.t)));
    }

    public static void main(String[] args)
    {
        int n = StdIn.readInt();
        int t = StdIn.readInt();
        PercolationStats ps = new PercolationStats(n, t);

        StdOut.printf("mean                    = %f\n", ps.mean());
        StdOut.printf("stddev                  = %f\n", ps.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());
    }
}
