package codetree.dfs;

import java.util.Scanner;

public class 안전_지대 {
    static final int MAX_N = 50;
    static final int MAX_M = 50;
    static final int MAX_K = 100;
    static final int DIR_N = 4;

    static int n, m;
    static int cnt;
    static int ans = 0;
    static int max = Integer.MIN_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] arr = new int[MAX_N][MAX_M];
    static boolean[][] visit = new boolean[MAX_N][MAX_M];

    public static void solution() {
        for (int k = 1; k <= MAX_K; k++) {
            initVisit();
            cnt = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (canGo(i, j, k)) {
                        visit[i][j] = true;
                        cnt++;
                        dfs(i, j, k);
                    }
                }
            }

            if (cnt > max) {
                max = cnt;
                ans = k;
            }
        }
    }

    public static void initVisit() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visit[i][j] = false;
            }
        }
    }

    public static void dfs(int x, int y, int k) {
        for (int i = 0; i < DIR_N; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (canGo(nx, ny, k)) {
                visit[nx][ny] = true;
                dfs(nx, ny, k);
            }
        }
    }

    public static boolean canGo(int x, int y, int k) {
        return inRange(x, y) && !visit[x][y] && arr[x][y] > k;
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
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

        solution();

        System.out.println(ans + " " + max);
    }
}
