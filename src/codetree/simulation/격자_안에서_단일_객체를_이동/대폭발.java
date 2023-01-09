package codetree.simulation.격자_안에서_단일_객체를_이동;

import java.util.Scanner;

public class 대폭발 {
    static int n, m;
    static int x, y;
    static int[][] arr;
    static int[][] temp;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static int solution() {
        int sum = 0;

        arr[x][y] = 1;

        int dist = 1;

        while (m-- > 0) {
            simulate(dist);
            dist *= 2;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += arr[i][j];
            }
        }

        return sum;
    }

    public static void simulate(int dist) {
        clearTemp();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] > 0) {
                    expand(i, j, dist);
                }
            }
        }

        // 새로 생긴 폭탄 업데이트
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (temp[i][j] > 0) {
                    arr[i][j] = 1;
                }
            }
        }

    }

    public static void expand(int x, int y, int dist) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * dist;
            int ny = y + dy[i] * dist;
            if (inRange(nx, ny)) {
                temp[nx][ny] = 1;
            }
        }
    }

    public static void clearTemp() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = 0;
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][n];
        temp = new int[n][n];

        m = sc.nextInt();
        x = sc.nextInt() - 1;
        y = sc.nextInt() - 1;

        int answer = solution();
        System.out.println(answer);
    }
}
