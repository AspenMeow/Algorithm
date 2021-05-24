import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;

public class BruteCollinearPoints {
    private int i;
    private LineSegment[] lsarry;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("");
        i = 0;
        int len = points.length;
        lsarry = new LineSegment[len];
        for (int p = 0; p < len - 3; p++) {
            for (int q = p + 1; q < len - 2; q++) {
                for (int r = q + 1; r < len - 1; r++) {
                    for (int s = r + 1; s < len; s++) {
                        ArrayList<Point> pl = new ArrayList<Point>();
                        Double s1 = points[p].slopeTo(points[q]);
                        Double s2 = points[p].slopeTo(points[r]);
                        Double s3 = points[p].slopeTo(points[s]);
                        if (s1.equals(s2) && s1.equals(s3)) {
                            pl.add(points[p]);
                            pl.add(points[r]);
                            pl.add(points[q]);
                            pl.add(points[s]);
                            Collections.sort(pl);
                            LineSegment ls = new LineSegment(pl.get(0), pl.get(3));
                            lsarry[i] = ls;
                            i++;

                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return i;
    }

    public LineSegment[] segments() {
        LineSegment[] newls = new LineSegment[i];
        for (int j = 0; j < i; j++) newls[j] = lsarry[j];
        return newls;
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
