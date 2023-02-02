package codetree.bfs.BFS_탐색;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 네_방향_탈출_가능_여부_판별하기 {
    static final int MAX_N = 100;
    static final int MAX_M = 100;

    static int n, m;
    static int[][] arr = new int[MAX_N][MAX_M];
    static boolean[][] visit = new boolean[MAX_N][MAX_M];
    static Queue<Point> q = new LinkedList<>();

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs() {
        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (canGo(nx, ny)) {
                    q.add(new Point(nx, ny));
                    visit[nx][ny] = true;
                }
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static boolean canGo(int x, int y) {
        return inRange(x, y) && !visit[x][y] && arr[x][y] == 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        bfs();

        System.out.println(visit[n - 1][m - 1] ? 1 : 0);
    }
}
