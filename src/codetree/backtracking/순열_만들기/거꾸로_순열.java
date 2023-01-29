package codetree.backtracking.순열_만들기;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 거꾸로_순열 {
    static int n;
    static List<Integer> nums = new ArrayList<>();
    static boolean[] visit;

    public static void bt(int cnt) {
        if (cnt == n) {
            nums.forEach(x -> System.out.print(x + " "));
            System.out.println();
            return;
        }

        for (int i = n; i >= 1; i--) {
            if (visit[i]) continue;
            visit[i] = true;
            nums.add(i);
            bt(cnt + 1);
            visit[i] = false;
            nums.remove(nums.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visit = new boolean[n + 1];
        bt(0);
    }
}
