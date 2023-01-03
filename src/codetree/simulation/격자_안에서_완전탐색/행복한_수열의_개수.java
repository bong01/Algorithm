package codetree.simulation.격자_안에서_완전탐색;

import java.util.Scanner;

public class 행복한_수열의_개수 {

    public static int solution(int n, int m, int[][] arr) {
        int answer = 0;
        int[] seq = new int[n];

        // 가로
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                seq[j] = arr[i][j];
            }
            if (isHappySequence(n, m, seq)) {
                answer++;
            }
        }

        // 세로
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                seq[i] = arr[i][j];
            }
            if (isHappySequence(n, m, seq)) {
                answer++;
            }
        }

        return answer;
    }

    public static boolean isHappySequence(int n, int m, int[] seq) {
        int max = 1;
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (seq[i] == seq[i - 1]) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 1;
            }
        }

        return max >= m;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int answer = solution(n, m, arr);
        System.out.println(answer);
    }

}
