package codetree.bfs.BFS_탐색;

import java.util.*;

public class 빙하 {
    static final int MAX_N = 200;
    static final int MAX_M = 200;

    static int n, m;
    static int[][] arr = new int[MAX_N][MAX_M];
    static boolean[][] visit = new boolean[MAX_N][MAX_M];

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static Queue<Point> q = new LinkedList<>();

    static List<Point> glaciers = new ArrayList<>();

    static int elapsedTime = 0;
    static int lastMeltCnt;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void solution() {
        do {
            simulate();
        } while (glacierExist());
    }

    static void simulate() {
        elapsedTime++;
        lastMeltCnt = 0;

        // 빙하에 둘려쌓여 있지 않은 물 찾기
        bfs();

        melt();
    }

    static void melt() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && nearWater(i, j)) {
                    arr[i][j] = 0;
                    lastMeltCnt++;
                }
            }
        }
    }

    static boolean nearWater(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (inRange(nx, ny) && visit[nx][ny]) {
                return true;
            }
        }

        return false;
    }

    static void initVisit() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visit[i][j] = false;
            }
        }
    }

    static void bfs() {
        initVisit();
        q.add(new Point(0, 0));
        visit[0][0] = true;

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

    static boolean glacierExist() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) return true;
            }
        }

        return false;
    }

    static boolean canGo(int x, int y) {
        return inRange(x, y) && !visit[x][y] && arr[x][y] == 0;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] == 1) glaciers.add(new Point(i, j));
            }
        }

        solution();

        System.out.println(elapsedTime + " " + lastMeltCnt);
    }
}
