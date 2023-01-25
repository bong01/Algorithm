package codetree.backtracking.N개_중에_M개_고르기_Simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class n개_중에_m개_뽑기 {
    static int n, m;
    static List<Integer> list = new ArrayList<>();

    public static void backtracking(int num, int cnt) {
        if (num == n + 1) {
            if (cnt == m) {
                for (int e : list) {
                    System.out.print(e + " ");
                }
                System.out.println();
            }
            return;
        }

        // num 사용
        list.add(num);
        backtracking(num + 1, cnt + 1);
        list.remove(list.size() - 1);

        // num 미사용
        backtracking(num + 1, cnt);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        backtracking(1, 0);
    }
}
