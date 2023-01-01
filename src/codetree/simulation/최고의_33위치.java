package codetree.simulation;

import java.util.Scanner;

public class 최고의_33위치 {

    public static int solution(int n, int[][] arr) {
        int answer = 0;

        // 3x3을 넘어가기 전까지
        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= n - 3; j++) {
                int count = countCoin(i, j, arr);
                answer = Math.max(answer, count);
            }
        }

        return answer;
    }

    // 3x3 탐색하여 동전 개수 반환
    private static int countCoin(int i, int j, int[][] arr) {
        int count = 0;

        for (int k = i; k <= i + 2; k++) {
            for (int l = j; l <= j + 2; l++) {
                if (arr[k][l] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int answer = solution(n, arr);
        System.out.println(answer);
    }

}
