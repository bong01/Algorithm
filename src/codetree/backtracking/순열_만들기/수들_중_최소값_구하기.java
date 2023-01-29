package codetree.backtracking.순열_만들기;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 수들_중_최소값_구하기 {
    static final int MAX_N = 10;

    static int n;
    static int ans = Integer.MIN_VALUE;
    static int[][] arr = new int[MAX_N][MAX_N];
    static boolean[] visit = new boolean[MAX_N];
    static List<Integer> selected = new ArrayList<>();

    public static void bt(int row) {
        if (row == n) {
            ans = Math.max(ans, calc());
            return;
        }

        for (int col = 0; col < n; col++) {
            if (visit[col]) continue;
            visit[col] = true;
            selected.add(col);
            bt(row + 1);
            selected.remove(selected.size() - 1);
            visit[col] = false;
        }
    }

    public static int calc() {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int j = selected.get(i);
            min = Math.min(min, arr[i][j]);
        }

        return min;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        bt(0);
        System.out.println(ans);
    }
}
