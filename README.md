# Percolation-and-Analysis
Modelled percolation in grids and analysed the probability of percolation and its threshold using Monte Carlo simulation

### How to use:
- Attach the following file for helper functions: https://algs4.cs.princeton.edu/code/algs4.jar <br>
(For details of above .jar file, check https://algs4.cs.princeton.edu/code/)
- The percolation class can be used to model a real life percolation application(involving a fluid to flow through "porous" material, i.e. material with hindrances)
- Create an instance of percolation by calling the percolation(int n) constructor, which creates an nXn grid with all elements initially blocked
- The percolationStats class can be used to determine a threshold for the amount of blockage below which the system almost certainly percolates (which comes out to be approximately 59.3%, i.e. if at least 59.3% of sites are open, the system almost certainly percolates
- Run percolationStats by passing n and t as command line arguments(in order), where n is the size of the grid and t is the number of test cases to average the threshold over
