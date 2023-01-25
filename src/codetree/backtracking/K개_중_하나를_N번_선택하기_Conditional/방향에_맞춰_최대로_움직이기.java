package codetree.backtracking.K개_중_하나를_N번_선택하기_Conditional;

import java.util.Scanner;

public class 방향에_맞춰_최대로_움직이기 {
    static final int MAX_N = 4;

    static int n, x, y;
    static int[][] arr = new int[MAX_N][MAX_N];
    static int[][] dir = new int[MAX_N][MAX_N];
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int ans = 0;

    public static void backtracking(int cnt) {
        ans = Math.max(ans, cnt);

        // 현재 숫자보다 큰 곳으로 이동
        for (int i = 1; i < n; i++) {
            int d = dir[x][y];
            int nx = x + dx[d] * i;
            int ny = y + dy[d] * i;

            // 조건을 만족하는지 확인
            if (inRange(nx, ny) && arr[nx][ny] > arr[x][y]) {
                int tx = x;
                int ty = y;
                x = nx;
                y = ny;
                backtracking(cnt + 1);
                x = tx;
                y = ty;
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
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dir[i][j] = sc.nextInt() - 1; // 배열 인덱스와 맞추기 위해 -1
            }
        }

        x = sc.nextInt() - 1;
        y = sc.nextInt() - 1;
        backtracking(0);
        System.out.println(ans);
    }
}
