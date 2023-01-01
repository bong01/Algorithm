package codetree.simulation;

import java.util.Scanner;

public class 트로미노 {

    public static int solution(int n, int m, int[][] arr) {
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer = Math.max(answer, slide(n, m, i, j, arr));
            }
        }

        return answer;
    }

    private static int slide(int n, int m, int x, int y, int[][] arr) {
        int[][][] windows = new int[][][]{
                {{1, 1, 1},
                {0, 0, 0},
                {0, 0, 0}},

                {{1, 0, 0},
                {1, 0, 0},
                {1, 0, 0}},

                {{1, 1, 0},
                {1, 0, 0},
                {0, 0, 0}},

                {{0, 1, 0},
                {1, 1, 0},
                {0, 0, 0}},

                {{1, 1, 0},
                {0, 1, 0},
                {0, 0, 0}},

                {{1, 0, 0},
                {1, 1, 0},
                {0, 0, 0}}
        };

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < 6; i++) {
            int sum = 0;
            boolean flag = true;

            for (int dx = 0; dx < 3; dx++) {
                for (int dy = 0; dy < 3; dy++) {
                    if (windows[i][dx][dy] == 0) {
                        continue;
                    }
                    if (x + dx >= n || y + dy >= m) {
                        flag = false;
                    } else {
                        sum += arr[x + dx][y + dy];
                    }
                }
            }

            if (flag) {
                max = Math.max(max, sum);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int answer = solution(n, m, arr);
        System.out.println(answer);
    }

}
