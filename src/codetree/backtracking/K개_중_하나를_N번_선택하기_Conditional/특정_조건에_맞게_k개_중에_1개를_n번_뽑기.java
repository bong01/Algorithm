package codetree.backtracking.K개_중_하나를_N번_선택하기_Conditional;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 특정_조건에_맞게_k개_중에_1개를_n번_뽑기 {
    static int k, n;
    static List<Integer> nums = new ArrayList<>();

    public static void backtracking(int cnt) {
        if (cnt == n) {
            print();
            return;
        }

        for (int i = 1; i <= k; i++) {
            if (nums.size() >= 2 && nums.get(cnt - 1) == i && nums.get(cnt - 2) == i) {
                continue;
            }
            nums.add(i);
            backtracking(cnt + 1);
            nums.remove(nums.size() - 1);
        }
    }

    public static void print() {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        n = sc.nextInt();

        backtracking(0);
    }
}
