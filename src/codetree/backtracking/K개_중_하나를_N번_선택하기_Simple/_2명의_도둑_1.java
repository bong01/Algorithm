package codetree.backtracking.K개_중_하나를_N번_선택하기_Simple;

import java.util.Scanner;

public class _2명의_도둑_1 {
    static final int MAX_N = 10;
    static final int MAX_M = 5;

    static int n, m, c;
    static int[][] weight = new int[MAX_N][MAX_N];
    static int[] a = new int[MAX_M];

    static int ans;
    static int max;

    public static void solution() {
        for (int sx1 = 0; sx1 < n; sx1++) {
            for (int sy1 = 0; sy1 < n; sy1++) {
                for (int sx2 = 0; sx2 < n; sx2++) {
                    for (int sy2 = 0; sy2 < n; sy2++) {
                        if (possible(sx1, sy1, sx2, sy2)) {
                            ans = Math.max(ans, findMax(sx1, sy1) + findMax(sx2, sy2));
                        }
                    }
                }
            }
        }
    }

    public static boolean possible(int sx1, int sy1, int sx2, int sy2) {
        // 격자 벗어나는 경우
        if (sy1 + m - 1 >= n || sy2 + m - 1 >= n) {
            return false;
        }

        // 두 도둑의 행이 다를 경우 무조건 가능
        if (sx1 != sx2) {
            return true;
        }

        // 행 같은데 겹칠 경우
        if (intersect(sy1, sy1 + m - 1, sy2, sy2 + m - 1)) {
            return false;
        }

        return true;
    }

    public static boolean intersect(int a, int b, int c, int d) {
        return !(b < c || d < a);
    }

    public static int findMax(int sx, int sy) {
        for (int i = sy; i <= sy + m - 1; i++) {
            // a[0] 부터 삽입
            a[i - sy] = weight[sx][i];
        }

        max = 0;
        findMaxSum(0, 0, 0);
        return max;
    }

    public static void findMaxSum(int idx, int weight, int reward) {
        if (idx == m) {
            if (weight <= c) {
                max = Math.max(max, reward);
            }
            return;
        }

        // idx에 해당하는 weight을 선택하지 않은 경우
        findMaxSum(idx + 1, weight, reward);

        // idx에 해당하는 weight을 선택한 경우
        findMaxSum(idx + 1, weight + a[idx], reward + a[idx] * a[idx]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        c = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                weight[i][j] = sc.nextInt();
            }
        }

        solution();

        System.out.println(ans);
    }
}
