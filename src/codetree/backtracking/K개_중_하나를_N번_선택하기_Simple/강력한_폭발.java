package codetree.backtracking.K개_중_하나를_N번_선택하기_Simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 강력한_폭발 {
    static final int MAX_N = 20;

    static int n;
    static int max = Integer.MIN_VALUE;
    static List<Point> bombs = new ArrayList<>();
    static int[][] bombType = new int[MAX_N][MAX_N];
    static boolean[][] bombed = new boolean[MAX_N][MAX_N];
    static Point[][] bombShape = {
            {},
            {new Point(-2, 0), new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(2, 0)},
            {new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1)},
            {new Point(-1, -1), new Point(-1, 1), new Point(0, 0), new Point(1, -1), new Point(1, 1)}
    };

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void findMaxArea(int cnt) {
        if (cnt == bombs.size()) {
            // 갱신
            max = Math.max(max, calc());
            return;
        }
        for (int i = 1; i <= 3; i++) {
            int x = bombs.get(cnt).x;
            int y = bombs.get(cnt).y;

            bombType[x][y] = i;
            findMaxArea(cnt + 1);
            bombType[x][y] = 0;
        }
    }

    public static int calc() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bombed[i][j] = false;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (bombType[i][j] > 0) {
                    bomb(i, j, bombType[i][j]);
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (bombed[i][j]) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static void bomb(int x, int y, int bType) {
        for (int i = 0; i < 5; i++) {
            int dx = bombShape[bType][i].x;
            int dy = bombShape[bType][i].y;

            int nx = x + dx;
            int ny = y + dy;

            if (inRange(nx, ny)) {
                bombed[nx][ny] = true;
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (sc.nextInt() > 0) {
                    bombs.add(new Point(i, j));
                }
            }
        }

        findMaxArea(0);
        System.out.println(max);
    }
}
