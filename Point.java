/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    public static final Comparator<Point> comparator = new PointComparator();
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    private static class PointComparator implements Comparator<Point> {

        @Override
        public int compare(Point p1, Point p2) {
            return p1.compareTo(p2);
        }
    }

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (this.x == that.x) {
            // Vertical Line
            return Double.POSITIVE_INFINITY;
        } else if (this.x == that.x && this.y == that.y) {
            // Points Equal
            return Double.NEGATIVE_INFINITY;
        } else if (this.y == that.y) {
            // Horizontal Line
            return 0.0;
        } else {
            return (that.y - this.y) / (that.x - this.y);
        }
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (this.y == that.y)
            return this.x - that.x;
        return this.y - that.y;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return comparator;
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point less = new Point(1,1);
        Point more = new Point(2,1);
        System.out.println(less.slopeTo(more));
        System.out.println(less.slopeTo(less));
        System.out.println(more.slopeTo(less));




        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 20);
        StdDraw.setYscale(0, 20);
        Point p1 = new Point(12,7);
        Point p2 = new Point(3,7);
        Point p3 = new Point(3,10);
        Point p4 = new Point(2,5);
        Point p5 = new Point(4,6);
        Point p6 = new Point(12,12);
        Point p7 = new Point(7,7);
        Point p8 = new Point(5,5);
        Point p9 = new Point(2,6);
        p1.draw();
        p2.draw();
        p3.draw();
        p4.draw();
        p5.draw();
        p6.draw();
        p7.draw();
        p8.draw();
        p9.draw();
        p1.drawTo(p2);
        p2.drawTo(p4);
        p3.drawTo(p6);
        p4.drawTo(p4);
        p5.drawTo(p8);
        p6.drawTo(p9);
        p7.drawTo(p7);
        p8.drawTo(p2);
        p9.drawTo(p1);
        p6.drawTo(p5);
        p3.drawTo(p7);
        p7.drawTo(p3);
        p2.drawTo(p8);
        p8.drawTo(p9);
        StdDraw.show();
    }
}