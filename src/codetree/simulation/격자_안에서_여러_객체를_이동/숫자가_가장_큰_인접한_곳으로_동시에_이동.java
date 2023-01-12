package codetree.simulation.격자_안에서_여러_객체를_이동;

import java.util.Scanner;

public class 숫자가_가장_큰_인접한_곳으로_동시에_이동 {
    static int n, m, t;
    static int[][] arr;
    static int[][] beads;
    static int[][] temp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int solution() {
        while (t-- > 0) {
            clearTemp();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (beads[i][j] == 1) {
                        int mx = -1; // 임시 초기화
                        int my = -1; // 임시 초기화
                        int max = Integer.MIN_VALUE;
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if (inRange(nx, ny) && arr[nx][ny] > max) {
                                    mx = nx;
                                    my = ny;
                                    max = arr[nx][ny];
                            }
                        }
                        temp[mx][my]++;
                    }
                }
            }

            copy();
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cnt += beads[i][j];
            }
        }

        return cnt;
    }

    public static void copy() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (temp[i][j] == 1) {
                    beads[i][j] = temp[i][j];
                } else {
                    beads[i][j] = 0;
                }
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
        beads = new int[n][n];
        temp = new int[n][n];
        m = sc.nextInt();
        t = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            beads[r][c] = 1;
        }

        int answer = solution();
        System.out.println(answer);
    }
}
