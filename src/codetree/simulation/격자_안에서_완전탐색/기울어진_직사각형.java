package codetree.simulation.격자_안에서_완전탐색;

import java.util.Scanner;

public class 기울어진_직사각형 {
    static int n;
    static int[][] arr;
    static int[] dx = {1, -1, -1, 1};
    static int[] dy = {-1, -1, 1, 1};

    public static int solution() {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 1; k < n; k++) {
                    for (int l = 1; l < n; l++) {
                        answer = Math.max(answer, getSum(i, j, k, l));
                    }
                }
            }
        }

        return answer;
    }

    public static int getSum(int x, int y, int k, int l) {
        int sum = 0;
        int[] moveCount = new int[]{k, l, k, l};

        // 대각선 방향으로 누적합
        for (int i = 0; i < 4; i++) {
            for (int count = 0; count < moveCount[i]; count++) {
                x += dx[i];
                y += dy[i];
                if (!inRange(x, y)) {
                    return 0;
                }
                sum += arr[x][y];
            }
        }

        return sum;
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

        int answer = solution();
        System.out.println(answer);
    }
}
