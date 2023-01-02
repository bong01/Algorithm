package codetree.simulation;

import java.util.Scanner;

public class 겹쳐지지_않는_두_직사각형 {
    static int n;
    static int m;
    static int[][] arr;
    static int[][] board;

    public static int solution() {
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = i; k < n; k++) {
                    for (int l = j; l < m; l++) {
                        answer = Math.max(answer, getMaxSum(i, j, k, l));
                    }
                }
            }
        }

        return answer;
    }

    public static int getMaxSum(int x1, int y1, int x2, int y2) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = i; k < n; k++) {
                    for (int l = j; l < m; l++) {
                        if (!overlapped(x1, y1, x2, y2, i, j, k, l)) {
                            max = Math.max(max, rectSum(x1, y1, x2, y2) + rectSum(i, j, k, l));
                        }
                    }
                }
            }
        }

        return max;
    }

    public static boolean overlapped(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        clearBoard();
        draw(x1, y1, x2, y2);
        draw(x3, y3, x4, y4);
        return checkBoard();
    }

    public static void clearBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = 0;
            }
        }
    }

    public static void draw(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                board[i][j] += 1;
            }
        }
    }

    public static boolean checkBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] >= 2) {
                    return true;
                }
            }
        }

        return false;
    }

    public static int rectSum(int x1, int y1, int x2, int y2) {
        int sum = 0;

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                sum += arr[i][j];
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int answer = solution();
        System.out.println(answer);
    }
}
