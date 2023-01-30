package codetree.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 그래프_탐색 {
    static final int MAX_N = 1_000;

    static int n, m;
    static int ans;
    static List<Integer>[] graph = new ArrayList[MAX_N + 1];
    static boolean[] visit = new boolean[MAX_N + 1];


    public static void dfs(int v) {
        for (int i = 0; i < graph[v].size(); i++) {
            int currV = graph[v].get(i);
            if (!visit[currV]) {
                visit[currV] = true;
                ans++;
                dfs(currV);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            graph[x].add(y);
            graph[y].add(x);
        }

        visit[1] = true;
        dfs(1);

        System.out.println(ans);
    }
}
