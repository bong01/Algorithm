package codetree.simulation.격자_안에서_단일_객체를_이동;

import java.util.Scanner;

public class 떨어지는_1차_블록 {
    static int n, m, k;
    static int[][] arr;
    public static void solution() {
        int targetRow = getTargetRow();

        for (int j = k; j <= k + m - 1; j++) {
            arr[targetRow][j] = 1;
        }
    }

    public static int getTargetRow() {
        for (int i = 0; i < n; i++) {
            for (int j = k; j <= k + m -1; j++) {
                if (arr[i][j] == 1) {
                    return i -1;
                }
            }
        }

        return n - 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][n];
        m = sc.nextInt();
        k = sc.nextInt() - 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        solution();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
