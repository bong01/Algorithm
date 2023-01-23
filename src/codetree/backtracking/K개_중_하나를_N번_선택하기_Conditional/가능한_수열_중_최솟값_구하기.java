package codetree.backtracking.K개_중_하나를_N번_선택하기_Conditional;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 가능한_수열_중_최솟값_구하기 {
    static int n;
    static List<Integer> list = new ArrayList<>();

    public static void backtracking(int cnt) {
        if (cnt == n) {
            for (int num : list) {
                System.out.print(num);
            }
            System.exit(0);
        }

        for (int i = 4; i <= 6; i++) {
            list.add(i);
            if (isPossible()) {
                backtracking(cnt + 1);
            }
            list.remove(list.size() - 1);
        }
    }

    public static boolean isPossible() {
        int len = 1;

        while (true) {
            int e1 = list.size() - 1;
            int s1 = e1 - len + 1;
            int e2 = s1 - 1;
            int s2 = e2 - len + 1;

            if (s2 < 0) {
                break;
            }

            if (isEqual(s1, e1, s2)) {
                return false;
            }

            len++;
        }

        return true;
    }

    public static boolean isEqual(int s1, int e1, int s2) {
        for (int i = 0; i <= e1 - s1; i++) {
            if (!list.get(s1 + i).equals(list.get(s2 + i))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        backtracking(0);
    }
}
