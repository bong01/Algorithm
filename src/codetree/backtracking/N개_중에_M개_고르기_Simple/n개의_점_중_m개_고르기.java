package codetree.backtracking.N개_중에_M개_고르기_Simple;

import java.util.Scanner;

public class n개의_점_중_m개_고르기 {
    static int n, m;
    static int ans = Integer.MAX_VALUE;
    static Point[] points;
    static boolean[] isSelected;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bt(int idx, int cnt) {
        if (cnt == m) {
            ans = Math.min(ans, calc());
            return;
        }

        if (idx == n) {
            return;
        }

        // 선택 o
        isSelected[idx] = true;
        bt(idx + 1, cnt + 1);
        isSelected[idx] = false;

        // 선택 x
        bt(idx + 1, cnt);
    }

    public static int calc() {
        Point[] temp = new Point[m];
        int ti = 0;

        for (int i = 0; i < n; i++) {
            if (isSelected[i]) temp[ti++] = points[i];
        }

        return findMaxDist(temp);
    }

    public static int findMaxDist(Point[] temp) {
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                int d = dist(temp[i], temp[j]);
                result = Math.max(result, d);
            }
        }

        return result;
    }

    public static int dist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        points = new Point[n];
        isSelected = new boolean[n];

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points[i] = new Point(x, y);
        }

        bt(0, 0);

        System.out.println(ans);
    }
}
