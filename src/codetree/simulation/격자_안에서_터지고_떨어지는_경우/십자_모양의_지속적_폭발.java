package codetree.simulation.격자_안에서_터지고_떨어지는_경우;

import java.util.Scanner;

public class 십자_모양의_지속적_폭발 {
    static int n; // 격자 크기
    static int m; // 폭탄을 터뜨릴 횟수
    static int c; // 폭탄을 터뜨릴 열의 위치
    static int[][] arr;
    static int[][] temp;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void solution() {
        bomb();
        shift();
    }

    public static void bomb() {
        for (int i = 1; i <= n; i++) {
            if (arr[i][c] != 0) {
                int step = arr[i][c] - 1;
                arr[i][c] = 0;
                for (int d = 0; d < 4; d++) {
                    for (int j = 1; j <= step; j++) {
                        int nx = i + dx[d] * j;
                        int ny = c + dy[d] * j;
                        if (!inRange(nx, ny)) {
                            break;
                        }
                        arr[nx][ny] = 0;
                    }

                }
                break;
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 1 && x <= n && y >= 1 && y <= n;
    }

    public static void clearTemp() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                temp[i][j] = 0;
            }
        }
    }

    public static void shift() {
        clearTemp();

        for (int j = 1; j <= n; j++) {
            int idx = n;
            for (int i = n; i >= 1; i--) {
                if (arr[i][j] != 0) {
                    temp[idx--][j] = arr[i][j];
                }
            }
        }

        copy();
    }

    public static void copy() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] = temp[i][j];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n + 1][n + 1];
        temp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            c = sc.nextInt();
            solution();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
