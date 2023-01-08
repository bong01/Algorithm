package codetree.simulation.격자_안에서_터지고_떨어지는_경우;

import java.util.Scanner;

public class _2차원_폭발_게임 {
    public static final int MAX_N = 100;
    public static final int BLANK = -1;
    public static final int WILL_EXPLODE = 0;
    static int n, m, k, endOfArr_1d, endOfTemp_1d;
    static int[][] arr_2d = new int[MAX_N][MAX_N];
    static int[][] temp_2d = new int[MAX_N][MAX_N];
    static int[] arr_1d = new int[MAX_N];
    static int[] temp_1d = new int[MAX_N];

    public static int solution() {
        int cnt = 0;

        simulate();
        for (int i = 0; i < k; i++) {
            rotate();
            simulate();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr_2d[i][j] != BLANK) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static void simulate() {
        for (int col = 0; col < n; col++) {
            copyColumn(col);
            explode();
            copyResult(col);
        }
    }

    public static void copyColumn(int col) {
        endOfArr_1d = 0;
        for (int i = 0; i < n; i++) {
            if (arr_2d[i][col] != BLANK) {
                arr_1d[endOfArr_1d++] = arr_2d[i][col];
            }
        }
    }

    public static void copyResult(int col) {
        int resultIdx = endOfArr_1d - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (resultIdx >= 0) {
                arr_2d[i][col] = arr_1d[resultIdx--];
            } else {
                arr_2d[i][col] = BLANK;
            }
        }
    }

    public static void explode() {
        boolean isExploded;
        do {
            isExploded = false;
            for (int i = 0; i < endOfArr_1d; i++) {
                if (arr_1d[i] == WILL_EXPLODE) {
                    continue;
                }

                int endIdx = getEndIdxOfExplosion(i, arr_1d[i]);

                if (endIdx - i + 1 >= m) {
                    fillZero(i, endIdx);
                    isExploded = true;
                }
            }
            moveToTemp();
            copyFromTemp();
        } while (isExploded);
    }

    public static int getEndIdxOfExplosion(int startIdx, int num) {
        int endIdx = startIdx + 1;
        while(endIdx < endOfArr_1d) {
            if(arr_1d[endIdx] == num)
                endIdx++;
            else{
                break;
            }
        }

        return endIdx - 1;
    }

    public static void fillZero(int startIdx, int endIdx) {
        for(int i = startIdx; i <= endIdx; i++) {
            arr_1d[i] = WILL_EXPLODE;
        }
    }

    public static void moveToTemp() {
        endOfTemp_1d = 0;
        for(int i = 0; i < endOfArr_1d; i++) {
            if(arr_1d[i] != WILL_EXPLODE) {
                temp_1d[endOfTemp_1d++] = arr_1d[i];
            }
        }
    }

    public static void copyFromTemp() {
        endOfArr_1d = endOfTemp_1d;
        for(int i = 0; i < endOfArr_1d; i++) {
            arr_1d[i] = temp_1d[i];
        }
    }

    public static void rotate() {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                temp_2d[i][j] = BLANK;

        int currIdx;
        for(int i = n - 1; i >= 0; i--) {
            currIdx = n - 1;
            for(int j = n - 1; j >= 0; j--) {
                if(arr_2d[i][j] != BLANK)
                    temp_2d[currIdx--][n - i - 1] = arr_2d[i][j];
            }
        }

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                arr_2d[i][j] = temp_2d[i][j];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        arr_2d = new int[n][n];
        temp_2d = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr_2d[i][j] = sc.nextInt();
            }
        }

        int answer = solution();
        System.out.println(answer);
    }
}
