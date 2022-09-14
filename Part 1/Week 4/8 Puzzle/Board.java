    import edu.princeton.cs.algs4.In;

    import java.util.LinkedList;

public class Board
{
    private int[][] board;
    private int[][] goal;
    private int wrongs;
    private final int length;


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles)
    {
        length = tiles.length;
        board = copyArray(tiles, length);
        goal = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++)
                goal[i][j] = length * i + j + 1;
        }
        goal[length - 1][length - 1] = 0;
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                if (board[i][j] != 0 && board[i][j] != goal[i][j])
                    wrongs++;
            }
        }
    }

    // string representation of this board
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(length + "\n");
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                sb.append(String.format(" %2d", board[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension()
    {
        return length;
    }

    // number of tiles out of place
    public int hamming()
    {
        return wrongs;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan()
    {
        int man = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int now = board[i][j];
                if (now != goal[i][j]) {
                    if (now == 0)
                        continue;
                    man += Math.abs((now - 1) / length - i);
                    man += Math.abs((now - 1) % length - j);
                }
            }
        }
        return man;
    }

    // is this board the goal board?
    public boolean isGoal()
    {
        return wrongs == 0;
    }

    // does this board equal y?
    public boolean equals(Object y)
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass())  return false;
        if (length != ((Board) y).length) return false;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board[i][j] != ((Board) y).board[i][j])
                    return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors()
    {
        LinkedList<Board> ll = new LinkedList<>();
        int v = 0, h = 0;
        for (int i = 0; i < length; i++) {
            int now = 0;
            for (int j = 0; j < length; j++) {
                v = i;
                h = j;
                now = board[i][j];
                if (now == 0)  break;
            }
            if (now == 0) break;
        }
        Board b = iterbaleHelper(v, h, 'u');
        if (!this.equals(b)) ll.add(b);

        b = iterbaleHelper(v, h, 'd');
        if (!this.equals(b)) ll.add(b);

        b = iterbaleHelper(v, h, 'l');
        if (!this.equals(b)) ll.add(b);

        b = iterbaleHelper(v, h, 'r');
        if (!this.equals(b)) ll.add(b);

        return ll;
    }

    // helper method to the iterable function
    private Board iterbaleHelper(int v, int h, char dir)
    {
        int[][] b = copyArray(board, length);
        switch (dir)
        {
            case 'u':
                if (v != 0)  exch(b, v, h, v - 1, h);
                break;
            case 'd':
                if (v != length - 1)  exch(b, v, h, v + 1, h);
                break;
            case 'l':
                if (h != 0)  exch(b, v, h, v, h - 1);
                break;
            case 'r':
                if (h != length - 1)  exch(b, v, h, v, h + 1);
                break;
        }
        return new Board(b);
    }

    // exchange places of the boxes in the tile
    private void exch(int[][] arr, int fv, int fh, int sv, int sh)
    {
        int temp = arr[fv][fh];
        arr[fv][fh] = arr[sv][sh];
        arr[sv][sh] = temp;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin()
    {
        int[][] blocksCopy = copyArray(board, length);
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length - 1; j++)
            {
                if ((blocksCopy[i][j] != 0) && (blocksCopy[i][j + 1] != 0))
                {
                    int toSwap = blocksCopy[i][j];
                    blocksCopy[i][j] = blocksCopy[i][j + 1];
                    blocksCopy[i][j + 1] = toSwap;
                    return new Board(blocksCopy);
                }
            }
        }
        return null;
    }

    // a method to copy arrays
    private int[][] copyArray(int[][] arr, int arrLength)
    {
        int[][] result = new int[arrLength][arrLength];
        for (int i = 0; i < arrLength; i++)
        {
            for (int j = 0; j < arrLength; j++)
                result[i][j] = arr[i][j];
        }
        return result;
    }

    // unit testing (not graded)
    public static void main(String[] args)
    {
        In in = new In();
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
        for (int i = 0; i < initial.length; i++) {
            for (int j = 0; j < initial.length; j++)
                System.out.print(initial.goal[i][j] + " ");
            System.out.println();
        }
    }

}