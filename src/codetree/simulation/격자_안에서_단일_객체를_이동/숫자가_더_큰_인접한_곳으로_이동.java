package codetree.simulation.격자_안에서_단일_객체를_이동;

import java.util.Scanner;

public class 숫자가_더_큰_인접한_곳으로_이동 {
    static int n;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void solution(int x, int y) {
        System.out.print(arr[x][y] + " ");

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (inRange(nx, ny)) {
                if (arr[nx][ny] > arr[x][y]) {
                    solution(nx, ny);
                    break;
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
        int r = sc.nextInt() - 1;
        int c = sc.nextInt() - 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        solution(r, c);
    }
}
