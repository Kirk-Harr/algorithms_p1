import java.util.*;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private LineSegment[] segments;
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        checkDupes(points);
        checkNulls(points);
        int length = points.length;
        Point[] pCopy = points.clone();
        Point[] seg;
        Arrays.sort(pCopy);
        ArrayList<LineSegment> segmentList = new ArrayList<>();
        if (length < 4) {
            segments = new LineSegment[0];
            return;
        }
        double workingSlope;
        int count;
        Point[] compareCopy = pCopy.clone();
        for(int i = 0; i < length; i++) {
            int j = 1;
            count = 0;
            Arrays.sort(compareCopy);
            Arrays.sort(compareCopy, pCopy[i].slopeOrder());
            workingSlope = pCopy[i].slopeTo(compareCopy[1]);
            for (j = 1; j < length; j++) {
                double testSlope = pCopy[i].slopeTo(compareCopy[j]);
                if (testSlope == workingSlope) {
                    count++;
                }
                else {
                    workingSlope = testSlope;
                    if (count >= 3) {
                        if (compareCopy[j-1].compareTo(pCopy[i]) < 0 && compareCopy[j-count].compareTo(pCopy[i]) < 0) {
                            seg = sortPoints(pCopy[i], compareCopy[j-count]);
                        }
                        else if (compareCopy[j-1].compareTo(pCopy[i]) > 0 && compareCopy[j-count].compareTo(pCopy[i]) > 0) {
                            seg = sortPoints(pCopy[i], compareCopy[j-1]);
                        }
                        else {
                            seg = sortPoints(compareCopy[j-count],compareCopy[j-1]);
                        }
                        if (seg[0].slopeTo(pCopy[i]) == Double.NEGATIVE_INFINITY) {
                            segmentList.add(new LineSegment(seg[0], seg[1]));
                        }
                    }
                    count = 1;
                }
            }
            if (count >= 3) {
                if (compareCopy[j-1].compareTo(pCopy[i]) < 0 && compareCopy[j-count].compareTo(pCopy[i]) < 0) {
                    seg = sortPoints(pCopy[i], compareCopy[j-count]);
                }
                else if (compareCopy[j-1].compareTo(pCopy[i]) > 0 && compareCopy[j-count].compareTo(pCopy[i]) > 0) {
                    seg = sortPoints(pCopy[i], compareCopy[j-1]);
                }
                else {
                    seg = sortPoints(compareCopy[j-count],compareCopy[j-1]);
                }
                if (seg[0].slopeTo(pCopy[i]) == Double.NEGATIVE_INFINITY) {
                    segmentList.add(new LineSegment(seg[0], seg[1]));
                }
            }
        }
        segments = segmentList.toArray(new LineSegment[0]);
    }

    // Check for duplicate points.
    private void checkDupes(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    // Check for null points.
    private void checkNulls(Point[] points) {
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    private Point[] sortPoints(Point p1, Point p2) {
        Point[] p = new Point[2];
        p[0] = p1;
        p[1] = p2;
        Arrays.sort(p);
        return p;
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
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