import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private int linesNum;
    private final LineSegment[] lines;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points)
    {
        if (points == null || points.length < 4)
            throw new IllegalArgumentException();

        Point[] tempPoints = new Point[points.length];
        System.arraycopy(points, 0, tempPoints, 0, points.length);
        Arrays.sort(tempPoints);
        LineSegment[] tempLines = new LineSegment[points.length];

        for (int i = 1; i < tempPoints.length; i++) {
            if (tempPoints[i].compareTo(tempPoints[i - 1]) == 0) {
                throw new IllegalArgumentException("duplicate");
            }
        }

        for (int i = 0; i < points.length; i++) {
            Arrays.sort(tempPoints);
            Arrays.sort(tempPoints, points[i].slopeOrder());
            int nowPoints = 2;
            double prevSlope, currSlope;
            int first = 0;
            boolean b = true;
            prevSlope = points[i].slopeTo(tempPoints[0]);
            for (int j = 1; j < tempPoints.length; j++) {
                currSlope = points[i].slopeTo(tempPoints[j]);
                if (prevSlope == currSlope) {
                    nowPoints++;
                    b = false;
                }
                if (b)
                    first = j;
                prevSlope = currSlope;
            }

            if (nowPoints > 3)
                tempLines[linesNum++] = new LineSegment(tempPoints[first], tempPoints[first + nowPoints - 2]);
        }
        int[] dubs = new int[linesNum];
        int j = 0;
        for (int i = 1; i < linesNum; i++) {
            if (tempLines[i].toString().equals(tempLines[i - 1].toString()))
                dubs[j++] = i;
        }

        LineSegment[] tempLines2 = new LineSegment[j];
        tempLines2[0] = tempLines[dubs[0]];
        linesNum = 1;
        for (int i = 1; i < j; i++) {
            if (tempLines[dubs[i]].toString().equals(tempLines[dubs[i - 1]].toString()))
                continue;
            tempLines2[i] = tempLines[dubs[i]];
            linesNum++;
        }
        lines = new LineSegment[linesNum];
        System.arraycopy(tempLines2, 0, lines, 0, linesNum);
    }

    // the number of line segments
    public int numberOfSegments()
    {
        return linesNum;
    }

    // the line segments
    public LineSegment[] segments()
    {
        return lines.clone();
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

class LineSegment {
    private final Point p;   // one endpoint of this line segment
    private final Point q;   // the other endpoint of this line segment

    /**
     * Initializes a new line segment.
     *
     * @param  p one endpoint
     * @param  q the other endpoint
     * @throws NullPointerException if either <tt>p</tt> or <tt>q</tt>
     *         is <tt>null</tt>
     */
    public LineSegment(Point p, Point q) {
        if (p == null || q == null) {
            throw new IllegalArgumentException("argument to LineSegment constructor is null");
        }
        if (p.equals(q)) {
            throw new IllegalArgumentException("both arguments to LineSegment constructor are the same point: " + p);
        }
        this.p = p;
        this.q = q;
    }


    /**
     * Draws this line segment to standard draw.
     */
    public void draw() {
        p.drawTo(q);
    }

    /**
     * Returns a string representation of this line segment
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this line segment
     */
    public String toString() {
        return p + " -> " + q;
    }

    /**
     * Throws an exception if called. The hashCode() method is not supported because
     * hashing has not yet been introduced in this course. Moreover, hashing does not
     * typically lead to good *worst-case* performance guarantees, as required on this
     * assignment.
     *
     * @throws UnsupportedOperationException if called
     */
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported");
    }
}