package codetree.backtracking.N개_중에_M개_고르기_Simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class xor_결과_최대_만들기 {
    static final int MAX_N = 20;

    static int n, m;
    static int[] arr = new int[MAX_N];
    static List<Integer> list = new ArrayList<>();
    static int ans = Integer.MIN_VALUE;

    public static void bt(int idx, int cnt) {
        if (cnt == m) {
            // 최대값 갱신
            ans = Math.max(ans, xor());
            return;
        }

        if (idx == n) {
            return;
        }

        list.add(arr[idx]);
        bt(idx + 1, cnt + 1);
        list.remove(list.size() - 1);

        bt(idx + 1, cnt);
    }

    public static int xor() {
        int result = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            result ^= list.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        bt(0, 0);
        System.out.println(ans);
    }
}
