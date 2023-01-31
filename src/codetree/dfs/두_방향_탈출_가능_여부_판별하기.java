package codetree.dfs;

import java.util.Scanner;

public class 두_방향_탈출_가능_여부_판별하기 {
    static final int MAX_N = 100;
    static final int MAX_M = 100;
    static final int DIR_NUM = 2;

    static int n, m;
    static int ans;
    static int[][] arr = new int[MAX_N][MAX_M];
    static boolean[][] visit = new boolean[MAX_N][MAX_M];
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    public static void dfs(int x, int y) {
        for (int i = 0; i < DIR_NUM; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx == n - 1 && ny == m - 1) {
                ans = 1;
                return;
            }

            if (canGo(nx, ny)) {
                visit[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }

    public static boolean canGo(int x, int y) {
        return inRange(x, y) && arr[x][y] == 1 && !visit[x][y];
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

        visit[0][0] = true;
        dfs(0, 0);
        System.out.println(ans);
    }
}
