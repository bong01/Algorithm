package codetree.backtracking.N개_중에_M개_고르기_Simple;

import java.util.Scanner;

public class _2n개_중에_n개의_숫자를_적절하게_고르기 {
    static final int MAX_N = 20;

    static int n;
    static int len;
    static int ans = Integer.MAX_VALUE;
    static boolean[] selected = new boolean[MAX_N];
    static int[] arr = new int[MAX_N];

    public static void backtracking(int idx, int cnt) {
        if (cnt == n) {
            ans = Math.min(ans, calc());
            return;
        }

        if (idx == len) {
            return;
        }

        selected[idx] = true;
        backtracking(idx + 1, cnt + 1);
        selected[idx] = false;

        backtracking(idx + 1, cnt);
    }

    public static int calc() {
        int s1 = 0;
        int s2 = 0;

        for (int i = 0; i < len; i++) {
            if (selected[i]) {
                s1 += arr[i];
            } else {
                s2 += arr[i];
            }
        }

        return Math.abs(s1 - s2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        len = 2 * n;
        for (int i = 0; i < len; i++) {
            arr[i] = sc.nextInt();
        }
        backtracking(0, 0);
        System.out.println(ans);
    }
}
