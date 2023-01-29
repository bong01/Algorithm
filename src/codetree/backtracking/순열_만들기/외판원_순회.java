package codetree.backtracking.순열_만들기;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 외판원_순회 {
    static final int MAX_N = 10;

    static int n;
    static int ans = Integer.MAX_VALUE;
    static int[][] arr = new int[MAX_N][MAX_N];
    static boolean[] visit = new boolean[MAX_N];
    static List<Integer> selected = new ArrayList<>();

    public static void bt(int cnt) {
        if (cnt == n) {
            selected.forEach(e -> System.out.print(e + " "));
            System.out.println();
            int total = 0;
            for (int i = 0; i < selected.size() - 1; i++) {
                int cost = arr[selected.get(i)][selected.get(i + 1)];
                if (cost == 0) return;
                total += cost;
            }

            int lastPos = selected.get(selected.size() - 1);
            int lastCost = arr[lastPos][0];
            if (lastCost == 0) return;
            total += lastCost;

            ans = Math.min(ans, total);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (visit[col]) continue;
            visit[col] = true;
            selected.add(col);
            bt(cnt + 1);
            selected.remove(selected.size() - 1);
            visit[col] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        visit[0] = true;
        selected.add(0);
        bt(1);
        System.out.println(ans);
    }
}
