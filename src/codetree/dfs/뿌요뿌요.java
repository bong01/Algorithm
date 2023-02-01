package codetree.dfs;

import java.util.Scanner;

public class 뿌요뿌요 {
    static final int MAX_N = 100;
    static final int MAX_DIGIT = 100;
    static final int DIR_N = 4;
    static final int bombCond = 4;

    static int n;
    static int[][] arr = new int[MAX_N][MAX_N];
    static boolean[][] visit = new boolean[MAX_N][MAX_N];

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int maxCnt = Integer.MIN_VALUE;
    static int cnt;
    static int bombCnt = 0;

    public static void solution() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (canGo(i, j, arr[i][j])) {
                    visit[i][j] = true;
                    cnt = 1;
                    dfs(i, j, arr[i][j]);
                    if (cnt >= bombCond) bombCnt++;
                    maxCnt = Math.max(maxCnt, cnt);
                }
            }
        }

    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static boolean canGo(int x, int y, int d) {
        return inRange(x, y) && !visit[x][y] && arr[x][y] == d;
    }

    public static void dfs(int x, int y, int d) {
        for (int i = 0; i < DIR_N; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (canGo(nx, ny, d)) {
                visit[nx][ny] = true;
                cnt++;
                dfs(nx, ny, d);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        solution();

        System.out.println(bombCnt + " " + maxCnt);
    }
}
