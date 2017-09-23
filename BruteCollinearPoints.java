import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    // finds all line segments containing 4 points

    private LineSegment[] segments;
    private int segmentCount = 0;
    public BruteCollinearPoints(Point[] points) {
        checkDupes(points);
        ArrayList<LineSegment> segmentArray = new ArrayList<>();
        Point[] sortedPoints = Arrays.copyOf(points, points.length);
        for (int p = 0; p < sortedPoints.length - 3; p++) {
            for (int q = p + 1; q < sortedPoints.length - 2; q++) {
                for (int r = q + 1; r < sortedPoints.length - 1; r++) {
                    for (int s = r + 1; s < sortedPoints.length; s++) {
                        if (sortedPoints[p].slopeTo(sortedPoints[q]) == sortedPoints[p].slopeTo(sortedPoints[r]) &&
                                sortedPoints[p].slopeTo(sortedPoints[q]) == sortedPoints[p].slopeTo(sortedPoints[s])) {
                            segmentArray.add(new LineSegment(sortedPoints[p], sortedPoints[s]));
                        }
                    }
                }
            }
        }
        segments = segmentArray.toArray(new LineSegment[segmentArray.size()]);
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}