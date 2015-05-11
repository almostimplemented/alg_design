import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class VisibleLines {
    // minimalist line object
    public class Line {
        double m; // slope
        double b; // y-intercept
        public Line() {
            m = 0;
            b = 0;
        }
        public Line(double slope, double intercept) {
            m = slope;
            b = intercept;
        }
    }

    // problem input is collection of lines
    public class ProblemInstance {
        private List<Line> lines;
        public ProblemInstance() {
            lines = new ArrayList<Line>();
        }
        public ProblemInstance(List<Line> ls) {
            lines = new ArrayList<Line>(ls);
        }
    }

    public class Solver {
        private ProblemInstance inst;
        public Solver(ProblemInstance instance) {
            inst = instance;
        }
        public List<Line> solve() {
            // Call auxiliary recursive divide and conquer method
            return solveHelper(inst.lines);
        }

        private List<Line> solveHelper(List<Line> lines) {
            int size = lines.size();
            if (size > 3) {
                List<Line> firstHalf  = new ArrayList<Line>(lines.subList(0, size/2));
                List<Line> secondHalf = new ArrayList<Line>(lines.subList(size/2, size));
                return merge(solveHelper(firstHalf), solveHelper(secondHalf));
            } else if (size == 3) {
                // The first and third line are visible (min and max slope resp.)
                // The second line is visible iff it intersects the first line to the left of
                // where the third line intersects the first line
                Line l0 = lines.get(0);
                Line l1 = lines.get(1);
                Line l2 = lines.get(2);
                double firstSecondIntersect = (l1.b - l0.b)/(l0.m - l1.m);
                double firstThirdIntersect  = (l2.b - l0.b)/(l0.m - l2.m);
                if (firstSecondIntersect > firstThirdIntersect) {
                    lines.remove(1);
                } 
            } else if (size == 2) {
                Line l0 = lines.get(0);
                Line l1 = lines.get(1);
                if (l0.m == l1.m) {
                    if (l0.b > l1.b)
                        lines.remove(1);
                    else
                        lines.remove(0);
                }
            }
            return lines;
        }

        private List<Line> merge(List<Line> firstHalf, List<Line> secondHalf) {
            // Merge the sorted lists :: O(n)
            List<Line> lines = new ArrayList<Line>();
            int i = 0;
            int j = 0;
            while (i < firstHalf.size() && j < secondHalf.size()) {
                Line l1 = firstHalf.get(i);
                Line l2 = secondHalf.get(j);
                if (l1.m < l2.m) {
                    lines.add(l1);
                    i++;
                } else if (l1.m == l2.m) {
                    if (l1.b > l2.b) 
                        lines.add(l1);
                    else
                        lines.add(l2);
                    i++;
                    j++;
                } else { // l1.m > l2.m
                    lines.add(l2);
                    j++;
                }
            }
            while (i < firstHalf.size()) {
                lines.add(firstHalf.get(i++));
            }
            while (j < secondHalf.size()) {
                lines.add(secondHalf.get(j++));
            }

            // Now we find visible lines :: O(n)
            List<Line> visibleLines = new ArrayList<Line>();
            // Initialize to first two lines in lines
            // We know there are at least two elements, since this is a call to merge
            visibleLines.add(lines.remove(0));
            visibleLines.add(lines.remove(0));
            int numVisible = 2;
            for (Line l : lines) {
                // Compute intersection with second to last line in visibleLines
                Line lastLine        = visibleLines.get(numVisible - 1);
                Line nextToLastLine  = visibleLines.get(numVisible - 2);
                double prevIntersect = (lastLine.b - nextToLastLine.b)/(nextToLastLine.m - lastLine.m);
                double currIntersect = (l.b - nextToLastLine.b)/(nextToLastLine.m - l.m);
                while (currIntersect < prevIntersect) {
                    // This means l intersects the next to last line before the last line
                    // Since l has a greater slope than lastLine, this means lastLine is not visible
                    visibleLines.remove(numVisible - 1);
                    numVisible--;

                    if (numVisible == 1) break;

                    lastLine        = visibleLines.get(numVisible - 1);
                    nextToLastLine  = visibleLines.get(numVisible - 2);
                    prevIntersect = (lastLine.b - nextToLastLine.b)/(nextToLastLine.m - lastLine.m);
                    currIntersect = (l.b - nextToLastLine.b)/(nextToLastLine.m - l.m);
                }
                // l has greatest slope seen thus far, so it is visible over
                // (interesect(l, lastLine), INFINITY). Add l to visibleLines.
                visibleLines.add(l);
                numVisible++;
            }
            // While the above loop "looks" like it is O(n^2), at each O(1) step
            // a line is either added or removed from visibleLines, and we never 
            // add and remove the same line from the list more than once
            return visibleLines;
        }
    }

    public static void main(String[] args) {
        VisibleLines vl = new VisibleLines();
        List<Line> ls = new ArrayList<Line>();
        Line l = vl.new Line(-4, 4);
        ls.add(l);
        l = vl.new Line(-3.0/2.0, 3);
        ls.add(l);
        l = vl.new Line(-1.0/6.0, 1);
        ls.add(l);
        l = vl.new Line(0.2, -1);
        ls.add(l);
        l = vl.new Line(0.5, -2);
        ls.add(l);
        l = vl.new Line(0, -10);
        ls.add(l);
        l = vl.new Line(2, -2);
        ls.add(l);
        ProblemInstance inst = vl.new ProblemInstance(ls);
        Solver solver = vl.new Solver(inst);
        int i = 0;
        for (Line line : solver.solve()) {
            i++;
            System.out.format("L_%d: y = %.2f x + %.2f%n", i, line.m, line.b);
        }
    }
}
