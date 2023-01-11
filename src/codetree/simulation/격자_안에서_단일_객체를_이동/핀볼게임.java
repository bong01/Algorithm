package codetree.simulation.격자_안에서_단일_객체를_이동;

import java.util.Scanner;

public class 핀볼게임 {
    static int n;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int simulate(int x, int y, int d) {
        int t = 1;

        while (inRange(x, y)) {
            if (arr[x][y] == 1) {
                d = 3 - d; // 0 <-> 3, 1 <-> 2
            } else if (arr[x][y] == 2) {
                d = (d < 2) ? d + 2 : d - 2; // 0 <-> 2, 1 <-> 3
            }

            x += dx[d];
            y += dy[d];
            t++;
        }

        return t;
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int t = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            t = Math.max(t, simulate(n - 1, i, 0));
            t = Math.max(t, simulate(0, i, 1));
            t = Math.max(t, simulate(i, n - 1, 2));
            t = Math.max(t, simulate(i, 0, 3));
        }

        System.out.println(t);
    }
}
