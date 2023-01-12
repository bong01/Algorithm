package codetree.simulation.격자_안에서_여러_객체를_이동;

import java.util.Scanner;

public class 숫자의_순차적_이동 {
    static int n, m;
    static int[][] arr;
    static int[] dx = {0, 0, 1, 1, 1, -1, -1, -1};
    static int[] dy = {1, -1, 0, 1, -1, 0, 1, -1};

    public static void simulate() {
        for (int num = 1; num <= n*n; num++) {
            Label: for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == num) {
                        int max = Integer.MIN_VALUE;
                        int mx = -1, my = -1;
                        for (int k = 0; k < 8; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if (inRange(nx, ny) && arr[nx][ny] > max) {
                                max = arr[nx][ny];
                                mx = nx;
                                my = ny;
                            }
                        }
                        int temp = arr[mx][my];
                        arr[mx][my] = arr[i][j];
                        arr[i][j] = temp;
                        break Label;
                    }
                }
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
        m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            simulate();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
