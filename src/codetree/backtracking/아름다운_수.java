package codetree.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 아름다운_수 {
    static int n;
    static List<Integer> seq = new ArrayList<>();
    static int answer = 0;

    public static void solution(int cnt) {
        if (cnt == n) {
            if (isBeautiful()) {
                answer++;
            }
            return;
        }

        for (int i = 1; i <= 4; i++) {
            seq.add(i);
            solution(cnt + 1);
            seq.remove(seq.size() - 1);
        }
    }

    public static boolean isBeautiful() {
        for (int i = 0; i < n; i += seq.get(i)) {
            if (i + seq.get(i) - 1 >= n) {
                return false;
            }
            for (int j = i; j < i + seq.get(i); j++) {
                if (seq.get(j) != seq.get(i)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        solution(0);
        System.out.println(answer);
    }
}
