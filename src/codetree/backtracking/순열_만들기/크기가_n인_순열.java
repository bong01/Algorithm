package codetree.backtracking.순열_만들기;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 크기가_n인_순열 {
    static int n;
    static List<Integer> list = new ArrayList<>();
    static boolean[] visited;

    public static void bt(int cnt) {
        if (cnt == n) {
            print();
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            list.add(i);
            bt(cnt + 1);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

    public static void print() {
        for (int e : list) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visited = new boolean[n + 1];
        bt(0);
    }
}
