package codetree.simulation;

import java.util.Scanner;

public class 양수_직사각형의_최대_크기_2 {
    static int n;
    static int m;
    static int[][] arr;
    static int[][] downMax;

    /* 풀이 2. 전처리 + 완전탐색 */
    public static int solution() {
        int answer = -1;

        preProcessing();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int bestHeight = Integer.MAX_VALUE;

                for (int l = j; l < m; l++) {
                    bestHeight = Math.min(bestHeight, downMax[i][l]);
                    int k = i + bestHeight - 1;
                    answer = Math.max(answer, getRectSize(i, j, k, l));
                }
            }
        }

        if (answer <= 0) {
            answer = -1;
        }

        return answer;
    }

    public static void preProcessing() {
        // 마지막 행에 대해 계산
        for (int j = 0; j < m; j++) {
            if (arr[n - 1][j] > 0) {
                downMax[n - 1][j] = 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] > 0) {
                    downMax[i][j] = downMax[i + 1][j] + 1;
                }
            }
        }
    }

    public static int getRectSize(int x1, int y1, int x2, int y2) {
        return (x2 - x1 + 1) * (y2 - y1 + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];
        downMax = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int answer = solution();
        System.out.println(answer);
    }
}
