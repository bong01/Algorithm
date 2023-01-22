package codetree.backtracking.K개_중_하나를_N번_선택하기_Conditional;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _1차원_윷놀이 {
    static final int MAX_N = 12;

    static int n, m, k;
    static int max = Integer.MIN_VALUE;
    static int[] dist = new int[MAX_N];
    static Map<Integer, Integer> map = new HashMap<>();

    public static void backtracking(int cnt) {
        max = Math.max(max, calc());

        if (cnt == n) {
            return;
        }

        for (int i = 1; i <= k; i++) {
            if (map.containsKey(i) && map.get(i) >= m) {
                continue;
            }
            map.put(i, map.getOrDefault(i, 1) + dist[cnt]);
            backtracking(cnt + 1);
            map.put(i, map.get(i) - dist[cnt]);
        }
    }

    public static int calc() {
        int result = 0;

        for (int key : map.keySet()) {
            if (map.get(key) >= m) {
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        for (int i = 0; i < n; i++) {
            dist[i] = sc.nextInt();
        }

        backtracking(0);
        System.out.println(max);
    }
}
