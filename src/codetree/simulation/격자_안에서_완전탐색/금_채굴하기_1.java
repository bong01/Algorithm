package codetree.simulation.격자_안에서_완전탐색;

import java.util.Scanner;

public class 금_채굴하기_1 {
    static int n;
    static int m;
    static int[][] arr;

    public static int solution() {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= 2 * (n - 1); k++) {
                    int goldCount = getGoldCount(i, j, k);
                    int miningCost = getMiningCost(k);
                    if (m * goldCount >= miningCost) {
                        answer = Math.max(answer, goldCount);
                    }
                }
            }
        }

        return answer;
    }

    public static int getMiningCost(int k) {
        return k * k + (k + 1) * (k + 1);
    }

    public static int getGoldCount(int x, int y, int k) {
        int count = 0;

        /* 풀이 1. 모든 위치 확인 */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 마름모 범위에 해당하고 금이 있다면
                if (Math.abs(x - i) + Math.abs(y - j) <= k && arr[i][j] == 1){
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 1이상 20이하
        m = sc.nextInt(); // 1이상 10이하
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
