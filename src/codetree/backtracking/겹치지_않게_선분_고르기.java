package codetree.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 겹치지_않게_선분_고르기 {
    static final int MAX_N = 15;

    static int n;
    static int max = Integer.MIN_VALUE;
    static Point[] segments = new Point[MAX_N];
    static List<Point> selectedSegs = new ArrayList<>();

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isOverlapped(Point seg1, Point seg2) {
        int ax1 = seg1.x;
        int ax2 = seg1.y;
        int bx1 = seg2.x;
        int bx2 = seg2.y;

        return (ax1 <= bx1 && bx1 <= ax2) || (ax1 <= bx2 && bx2 <= ax2) ||
                (bx1 <= ax1 && ax1 <= bx2) || (bx1 <= ax2 && ax2 <= bx2);
    }

    public static boolean isPossible() {
        for (int i = 0; i < selectedSegs.size(); i++) {
            for (int j = i + 1; j < selectedSegs.size(); j++) {
                if (isOverlapped(selectedSegs.get(i), selectedSegs.get(j))) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void findMaxCount(int cnt) {
        if (cnt == n) {
            if (isPossible()) {
                max = Math.max(max, selectedSegs.size());
            }
            return;
        }

        selectedSegs.add(segments[cnt]);
        findMaxCount(cnt + 1);
        selectedSegs.remove(selectedSegs.size() - 1);

        findMaxCount(cnt + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            segments[i] = new Point(0, 0);
        }

        for (int i = 0; i < n; i++) {
            int x1 = sc.nextInt();
            int x2 = sc.nextInt();
            segments[i] = new Point(x1, x2);
        }

        findMaxCount(0);
        System.out.println(max);
    }
}
