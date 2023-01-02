package codetree.simulation;

import java.util.Scanner;

public class 양수_직사각형의_최대_크기_1 {
    static int n;
    static int m;
    static int[][] arr;

    public static int solution() {
        int answer = -1;

        /* 풀이 1. 완전탐색 */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = i; k < n; k++) {
                    for (int l = j; l < m; l++) {
                        if (isPositive(i, j, k, l)) {
                            answer = Math.max(answer, getRectSize(i, j, k, l));
                        }
                    }
                }
            }
        }

        return answer;
    }

    public static boolean isPositive(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (arr[i][j] <= 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int getRectSize(int x1, int y1, int x2, int y2) {
        return (x2 - x1 + 1) * (y2 - y1 + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int answer = solution();
        System.out.println(answer);
    }
}
