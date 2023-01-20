package codetree.backtracking.K개_중_하나를_N번_선택하기_Simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class k개_중에_1개를_n번_뽑기 {
    static int k;
    static int n;
    static List<Integer> answer = new ArrayList<>();

    public static void solution(int cnt) {
        if (cnt == n) {
            print();
            return;
        }
        for (int i = 1; i <= k; i++) {
            answer.add(i);
            solution(cnt + 1);
            answer.remove(answer.size() - 1);
        }
    }

    public static void print() {
        for (int num : answer) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        n = sc.nextInt();
        solution(0);
    }
}
