package codetree.simulation.격자_안에서_밀고_당기기;

import java.util.Scanner;

public class _2차원_바람 {
    static int n;
    static int m;
    static int q;
    static int[][] arr;
    static int[][] copy;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void solution(int x1, int y1, int x2, int y2) {
        rotateBorder(x1, y1, x2, y2);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = arr[i][j];
            }
        }

        changeElement(x1, y1, x2, y2);
    }

    public static void rotateBorder(int x1, int y1, int x2, int y2) {
        int temp = arr[x1][y1];

        for (int i = x1; i < x2; i++) {
            arr[i][y1] = arr[i + 1][y1];
        }

        for (int j = y1; j < y2; j++) {
            arr[x2][j] = arr[x2][j + 1];
        }

        for (int j = x2; j > x1; j--) {
            arr[j][y2] = arr[j - 1][y2];
        }

        for (int i = y2; i > y1; i--) {
            arr[x1][i] = arr[x1][i - 1];
        }

        arr[x1][y1 + 1] = temp;
    }

    public static void changeElement(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                int sum = copy[i][j];
                int count = 1;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (inRange(nx, ny)) {
                        sum += copy[nx][ny];
                        count++;
                    }
                }
                int avg = sum / count;
                arr[i][j] = avg;
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();
        arr = new int[n][m];
        copy = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < q; i++) {
            int r1 = sc.nextInt() - 1;
            int c1 = sc.nextInt() - 1;
            int r2 = sc.nextInt() - 1;
            int c2 = sc.nextInt() - 1;

            solution(r1, c1, r2, c2);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
