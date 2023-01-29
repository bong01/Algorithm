package codetree.backtracking.순열_만들기;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 수들의_합_최대화하기 {
    static final int MAX_N = 10;

    static int n;
    static int ans = Integer.MIN_VALUE;
    static int[][] arr = new int[MAX_N][MAX_N];
    static boolean[] visit = new boolean[MAX_N];
    static List<Integer> coloredPos = new ArrayList<>();

    public static void bt(int row) {
        if (row == n) {
            ans = Math.max(ans, calc());
            return;
        }

        for (int j = 0; j < n; j++) {
                if (visit[j]) continue;
                visit[j] = true;
                coloredPos.add(j);
                bt(row + 1);
                visit[j] = false;
                coloredPos.remove(coloredPos.size() - 1);
        }
    }

    public static int calc() {
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int j = coloredPos.get(i);
            sum += arr[i][j];
        }

        return sum;
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
