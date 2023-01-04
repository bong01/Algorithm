package codetree.simulation.격자_안에서_밀고_당기기;

import java.util.Scanner;

public class 기울어진_직사각형의_회전 {
    static int n, r, c, m1, m2, m3, m4, dir;
    static int[] m;
    static int[][] arr, temp;
    static int[] dx;
    static int[] dy;

    public static void solution() {
        shift(r - 1, c - 1);
    }

    public static void shift(int x, int y) {
        if (dir == 0) {
            dx = new int[]{-1, -1, 1, 1};
            dy = new int[]{1, -1, -1, 1};
            m = new int[]{m1, m2, m3, m4};
        } else {
            dx = new int[]{-1, -1, 1, 1};
            dy = new int[]{-1, 1, 1, -1};
            m = new int[]{m4, m3, m2, m1};
        }

        // temp 배열에 arr 배열 복사
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        // 한 칸씩 민 결과를 temp에 저장
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < m[i]; j++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                temp[nx][ny] = arr[x][y];
                x = nx;
                y = ny;
            }
        }

        // temp 배열을 arr 배열에 복사
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

        r = sc.nextInt();
        c = sc.nextInt();
        m1 = sc.nextInt();
        m2 = sc.nextInt();
        m3 = sc.nextInt();
        m4 = sc.nextInt();
        m = new int[]{m4, m1, m2, m3};
        dir = sc.nextInt();

        solution();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
