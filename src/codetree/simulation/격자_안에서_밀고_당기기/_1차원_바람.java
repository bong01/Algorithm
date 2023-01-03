package codetree.simulation.격자_안에서_밀고_당기기;

import java.util.Scanner;

public class _1차원_바람 {
    static int n;
    static int m;
    static int q;
    static int[][] arr;
    static boolean[] visit;

    public static void solution(int r, char d) {
        visit[r] = true;

        shift(r, d);

        // 위쪽으로 전파
        for (int i = r - 1, dir = flipDir(d); i >= 0; i--) {
            if (hasSameElementWithSameIndex(arr[i], arr[i + 1])) {
                shift(i, (char) dir);
                dir = flipDir((char) dir);
            } else {
                break;
            }
        }

        // 아래쪽으로 전파
        for (int i = r + 1, dir = flipDir(d); i < n; i++) {
            if (hasSameElementWithSameIndex(arr[i], arr[i - 1])) {
                shift(i, (char) dir);
                dir = flipDir((char) dir);
            } else {
                break;
            }
        }
    }

    public static void shift(int r, char d) {
        int[] row = arr[r];
        int temp;

        if (d == 'L') {
            temp = arr[r][m - 1];

            for (int i = m - 1; i >= 1; i--) {
                row[i] = row[i - 1];
            }

            row[0] = temp;
        } else {
            temp = arr[r][0];

            for (int i = 0; i < m - 1; i++) {
                row[i] = row[i + 1];
            }

            row[m - 1] = temp;
        }
        arr[r] = row;
    }

    public static char flipDir(char d) {
        if (d == 'L') {
            return 'R';
        } else {
            return 'L';
        }
    }

    public static boolean hasSameElementWithSameIndex(int[] row1, int[] row2) {
        for (int i = 0; i < m; i++) {
            if (row1[i] == row2[i]) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();
        arr = new int[n][m];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < q; i++) {
            int r = sc.nextInt() - 1; // 바람에 영향을 받는 행 번호, 행 index는 0부터 시작이므로 -1
            char d = sc.next().charAt(0); // 바람이 불어오는 방향(L, R)
            solution(r, d);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
