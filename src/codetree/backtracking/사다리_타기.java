package codetree.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 사다리_타기 {
    static final int MAX_N = 11;

    static int n, m;
    static int ans = Integer.MAX_VALUE;
    static List<Point> lines = new ArrayList<>();
    static List<Point> selectedLines = new ArrayList<>();

    static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point another) {
            if (this.x == another.x) return this.y - another.y;
            return this.x - another.x;
        }
    }

    public static void findMinLines(int cnt) {
        if (cnt == m) {
            if (possible()) {
                ans = Math.min(ans, selectedLines.size());
            }
            return;
        }

        selectedLines.add(lines.get(cnt));
        findMinLines(cnt + 1);
        selectedLines.remove(selectedLines.size() - 1);

        findMinLines(cnt + 1);
    }

    public static boolean possible() {
        int[] n1 = new int[MAX_N];
        int[] n2 = new int[MAX_N];
        for (int i = 0; i < n; i++) {
            n1[i] = n2[i] = i;
        }

        for (Point line : lines) {
            int idx = line.y;
            int tmp = n1[idx];
            n1[idx] = n1[idx + 1];
            n1[idx + 1] = tmp;
        }

        for (Point selectedLine : selectedLines) {
            int idx = selectedLine.y;
            int tmp = n2[idx];
            n2[idx] = n2[idx + 1];
            n2[idx + 1] = tmp;
        }

        for (int i = 0; i < n; i++) {
            if (n1[i] != n2[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt();

            lines.add(new Point(b, a));
        }

        Collections.sort(lines);
        findMinLines(0);
        System.out.println(ans);
    }
}
