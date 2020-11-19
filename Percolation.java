package com.company;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    private boolean[][] grid;
    private final int n;
    private int sites;
    private final WeightedQuickUnionUF wquf;
    private final WeightedQuickUnionUF wquf2;

    public Percolation(int n)
    {
        if (n <= 0)
            throw new IllegalArgumentException("IllegalArgumentException");
        this.grid = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                this.grid[i][j] = false;
        this.n = n;
        this.sites = 0;
        wquf = new WeightedQuickUnionUF((n*n) + 2);
        wquf2 = new WeightedQuickUnionUF((n*n) + 1);
    }

    public void open(int row, int column)
    {
        if (row > this.n || row < 1)
            throw new IllegalArgumentException("IllegalArgumentException");
        if (column > this.n || column < 1)
            throw new IllegalArgumentException("IllegalArgumentException");
        if (isOpen(row, column))
            return;
        row--;
        column--;
        this.grid[row][column] = true;
        sites++;

        //upper  
        if (row != 0)
        {
            if (this.grid[row-1][column])
            {
                int p = row*n + column;
                int q = (row-1)*n + column;
                wquf.union(p, q);
                wquf2.union(p, q);
            }
        }
        //lower  
        if (row != n-1)
        {
            if (this.grid[row+1][column])
            {
                int p = row*n + column;
                int q = (row+1)*n + column;
                wquf.union(p, q);
                wquf2.union(p, q);
            }
        }
        //left  
        if (column != 0)
        {
            if (this.grid[row][column-1])
            {
                int p = row*n + column;
                int q = row*n + column-1;
                wquf.union(p, q);
                wquf2.union(p, q);
            }
        }
        //right  
        if (column != n-1)
        {
            if (this.grid[row][column+1])
            {
                int p = row*n + column;
                int q = row*n + column+1;
                wquf.union(p, q);
                wquf2.union(p, q);
            }
        }
        //if top opens, we add path to virtual top  
        if (row == 0)
        {
            int p = row*n + column;
            int q = n*n;
            wquf.union(p, q);
            wquf2.union(p, q);
        }
        //if bottom opens, we add path to virtual bottom  
        if (row == n-1)
        {
            int p = row * n + column;
            int q = (n * n) + 1;
            wquf.union(p, q);
        }
    }

    public boolean isOpen(int row, int column)
    {
        if (row > this.n || row < 1)
            throw new IllegalArgumentException("IllegalArgumentException");
        if (column > this.n || column < 1)
            throw new IllegalArgumentException("IllegalArgumentException");
        return this.grid[row - 1][column - 1];
    }

    public boolean isFull(int row, int column)
    {
        if (isOpen(row, column))
        {
            int pos = (row - 1) * this.n + column - 1;
            if (wquf2.find(pos) == wquf2.find(n*n))
                return true;
        }
        return false;
    }

    public int numberOfOpenSites()
    {
        return this.sites;
    }

    public boolean percolates()
    {
        if (wquf.find(n*n) == wquf.find((n*n)+1))
            return true;
        return false;
    }
}
