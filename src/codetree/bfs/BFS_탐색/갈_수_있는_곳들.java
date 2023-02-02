package codetree.bfs.BFS_탐색;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 갈_수_있는_곳들 {
    static final int MAX_N = 100;

    static int[][] arr = new int[MAX_N][MAX_N];
    static boolean[][] visit = new boolean[MAX_N][MAX_N];
    static Queue<Point> q = new LinkedList<>();
    static int n , k;

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int ans = 0;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void bfs() {
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

    static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    static boolean canGo(int x, int y) {
        return inRange(x, y) && !visit[x][y] && arr[x][y] == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < k; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;

            q.add(new Point(r, c));
            visit[r][c] = true;
        }

        bfs();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visit[i][j]) ans++;
            }
        }

        System.out.println(ans);
    }
}
