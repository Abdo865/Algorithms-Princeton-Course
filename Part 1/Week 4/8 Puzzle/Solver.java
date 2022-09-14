import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver
{

    private List<Board> soln = new LinkedList<>();

    private class Node
    {
        private Board board;
        private Node prev;
        private int moves, priority;

        public Node()
        {
        }

        public Node(Board board)
        {
            this.board = board;
        }

        public Node(Board board, Node prev, int moves)
        {
            this.board = board;
            this.prev = prev;
            this.moves = moves;
            this.priority = moves + board.manhattan();
        }
    }

    // this is for the priority queue to find the minimum priority board
    private static class ManhattanNodeComparator implements Comparator<Node>
    {
        @Override
        public int compare(Node n1, Node n2)
        {
            return n1.priority - n2.priority;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        if (initial == null)
            throw new IllegalArgumentException();

        MinPQ<Node> normal = new MinPQ<>(new ManhattanNodeComparator());
        MinPQ<Node> twin = new MinPQ<>(new ManhattanNodeComparator());

        Node b1 = new Node(initial);
        Node b2 = new Node(initial.twin());
        normal.insert(b1);
        twin.insert(b2);
        while (!b1.board.isGoal() && !b2.board.isGoal())
        {
            b1 = normal.delMin();
            for (Board b : b1.board.neighbors())
            {
                if (b1.prev == null || !b1.prev.board.equals(b))
                    normal.insert(new Node(b, b1, b1.moves + 1));
            }

            b2 = twin.delMin();
            for (Board b : b2.board.neighbors())
            {
                if (b2.prev == null || !b2.prev.board.equals(b))
                    twin.insert(new Node(b, b2, b1.moves + 1));
            }
        }

        if (b1.board.isGoal() && !b2.board.isGoal())
        {
            while (b1 != null)
            {
                soln.add(b1.board);
                b1 = b1.prev;
            }
            Collections.reverse(soln);
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable()
    {
        return !soln.isEmpty();
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves()
    {
        return soln.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
        if (isSolvable())
            return soln;
        return null;
    }

    // test client (see below)
    public static void main(String[] args)
    {

        // create initial board from file
        In in = new In();
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else
        {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
