import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation
{
    private final WeightedQuickUnionUF forward, back;
    private boolean[][] places;
    private final int size;
    private int opened;
    private final int topIndex;
    private final int btmIndex;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if (n <= 0)
            throw new IllegalArgumentException();
        places = new boolean[n][n];
        forward = new WeightedQuickUnionUF(n * n + 1);
        back = new WeightedQuickUnionUF(n * n + 2);
        size = n;
        opened = 0;
        topIndex = n * n;
        btmIndex = n * n + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if ((row > size || row < 1) || (col > size || col < 1))
            throw new IllegalArgumentException();
        if (places[row-1][col-1]) return;       // return if it was already opened

        places[row-1][col-1] = true;            // open it
        int ourPlace = findCellPlace(row-1, col - 1);  // finding its place on the disjoint place
        // to connect with virtual top or bottom row
        if (row == 1)
        {
            forward.union(topIndex, col-1);
            back.union(topIndex, col-1);
        }
        if (row == size)
            back.union(btmIndex, ourPlace);

        // testing the 4 directions to union if it any of them was opened
        if (row != 1 && isOpen(row - 1, col))       //up
        {
            forward.union(ourPlace, findCellPlace(row - 2, col - 1));
            back.union(ourPlace, findCellPlace(row - 2, col - 1));
        }
        if (row != size && isOpen(row + 1, col))    //down
        {
            forward.union(ourPlace, findCellPlace(row, col - 1));
            back.union(ourPlace, findCellPlace(row, col - 1));
        }
        if (col != 1 && isOpen(row, col - 1))       //left
        {
            forward.union(ourPlace, findCellPlace(row - 1, col - 2));
            back.union(ourPlace, findCellPlace(row - 1, col - 2));
        }
        if (col != size && isOpen(row, col+1))      //right  
        {
            forward.union(ourPlace, findCellPlace(row - 1, col));
            back.union(ourPlace, findCellPlace(row - 1, col));
        }
        opened++;
    }
    // this is a helper method to find the place of the cell on the Disjoint array
    private int findCellPlace(int row, int col)
    {
        return row * size + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        if ((row > size || row < 1) || (col > size || col < 1))
            throw new IllegalArgumentException();

        return places[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        if ((row > size || row < 1) || (col > size || col < 1))
            throw new IllegalArgumentException();

        int place = findCellPlace(row-1, col-1);
        return forward.find(topIndex) == forward.find(place);
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return opened;
    }

    // does the system percolate?
    public boolean percolates()
    {
        return back.find(btmIndex) == back.find(topIndex);
    }

    // test client (optional)
    public static void main(String[] args)
    {
        Percolation p = new Percolation(10);
    }
}
