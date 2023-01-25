package codetree.backtracking.K개_중_하나를_N번_선택하기_Conditional;

import java.util.Scanner;

public class 최소_점프_횟수 {
    static final int MAX_N = 10;
    static int n;
    static int ans = Integer.MAX_VALUE;
    static int[] arr = new int[MAX_N];

    public static void backtracking(int x, int cnt) {
        // 끝까지 도달했을 경우
        if (x == n - 1) {
            ans = Math.min(ans, cnt);
            return;
        }

        for (int step = 1; step <= arr[x]; step++) {
            int nx = x + step;
            if (nx < n) {
                backtracking(nx, cnt + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        backtracking(0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
