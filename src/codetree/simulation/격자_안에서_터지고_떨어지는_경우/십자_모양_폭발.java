package codetree.simulation.격자_안에서_터지고_떨어지는_경우;

import java.util.Scanner;

public class 십자_모양_폭발 {
    static int n, r, c;
    static int[][] arr, temp;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void solution() {
        int step = arr[r][c] - 1;
        arr[r][c] = 0;

        for (int st = 1; st <= step; st++) {
            for (int i = 0; i < 4; i++) {
                int nx = r + (dx[i] * st);
                int ny = c + (dy[i] * st);
                if (inRange(nx, ny)) {
                    arr[nx][ny] = 0;
                }
            }
        }

        shift();
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void shift() {
        for (int j = 0; j < n; j++) {
            int tx = n - 1;
            for (int i = n - 1; i >= 0; i--) {
                if (arr[i][j] > 0) {
                    temp[tx--][j] = arr[i][j];
                }
            }
        }

        // 원래 배열에 복사
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = temp[i][j];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][n];
        temp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        r = sc.nextInt() - 1;
        c = sc.nextInt() - 1;

        solution();

        // 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
