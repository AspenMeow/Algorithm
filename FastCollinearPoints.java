import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {
    private LineSegment[] lsarr;
    private int couter = 0;
    private ArrayList<Point> found;


    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("");
        int len = points.length;
        found = new ArrayList<Point>();
        lsarr = new LineSegment[points.length];
        Arrays.sort(points);
        for (int i = 0; i < len - 3; i++) {
            Point p = points[i];
            if (exist(found, p)) continue;
            ArrayList<Point> ap = new ArrayList<Point>();
            for (int j = i + 1; j < len; j++) ap.add(points[j]);
            Collections.sort(ap, p.slopeOrder());
            int k = 0;
            while (k < ap.size()) {
                ArrayList<Point> pl = new ArrayList<Point>();
                pl.add(p);
                Double s1 = p.slopeTo(ap.get(k));
                pl.add(ap.get(k));
                for (int j = k + 1; j < ap.size() && s1.equals(p.slopeTo(ap.get(j))); j++) {
                    pl.add(ap.get(j));
                }
                if (pl.size() > 3) {
                    Collections.sort(pl);
                    LineSegment ls = new LineSegment(pl.get(0), pl.get(pl.size() - 1));
                    for (Point point : pl) found.add(point);
                    lsarr[couter] = ls;
                    couter++;
                    k = k + pl.size() - 1;


                } else k++;

            }


        }
    }

    private static boolean exist(ArrayList<Point> arr, Point p) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals(p)) return true;
        }
        return false;
    }


    public int numberOfSegments() {
        return couter;
    }

    public LineSegment[] segments() {
        LineSegment[] newls = new LineSegment[couter];
        for (int i = 0; i < couter; i++) newls[i] = lsarr[i];
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();


    }


}
