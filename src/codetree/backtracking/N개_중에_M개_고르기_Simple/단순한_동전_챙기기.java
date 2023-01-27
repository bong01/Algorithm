package codetree.backtracking.N개_중에_M개_고르기_Simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 단순한_동전_챙기기 {
    static final int MAX_N = 20;

    static int n;
    static int m = 3;
    static int ans = Integer.MAX_VALUE;
    static List<Point> coins = new ArrayList<>();
    static List<Point> selectedCoins = new ArrayList<>();
    static char[][] arr = new char[MAX_N][MAX_N];
    static Point start;
    static Point end;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void backtracking(int idx, int cnt) {
        if (cnt == m) {
            ans = Math.min(ans, calc());
            return;
        }

        if (idx == coins.size()) return;

        // idx에 해당하는 동전 선택 x
        backtracking(idx + 1, cnt);

        // idx에 해당하는 동전 선택 o
        selectedCoins.add(coins.get(idx));
        backtracking(idx + 1, cnt + 1);
        selectedCoins.remove(selectedCoins.size() - 1);
    }

    public static int calc() {
        int result = dist(start, selectedCoins.get(0));

        for (int i = 1; i < m; i++) {
            result += dist(selectedCoins.get(i-1), selectedCoins.get(i));
        }

        result += dist(selectedCoins.get(m - 1), end);

        return result;
    }

    public static int dist(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String input = sc.next();
            for (int j = 0; j < n; j++) {
                arr[i][j] = input.charAt(j);
                if (arr[i][j] == 'S') start = new Point(i, j);
                else if (arr[i][j] == 'E') end = new Point(i, j);
            }
        }

        for (int num = 1; num <= 9; num++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == num + '0') coins.add(new Point(i, j));
                }
            }
        }

        backtracking(0, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
