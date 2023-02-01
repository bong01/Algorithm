package codetree.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 마을_구분하기 {
    static final int MAX_N = 25;
    static final int DIR_N = 4;

    static int n;
    static int peopleNum;
    static List<Integer> peopleNums = new ArrayList<>();
    static int[][] arr = new int[MAX_N][MAX_N];
    static boolean[][] visit = new boolean[MAX_N][MAX_N];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void solution() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (canGo(i, j)) {
                    visit[i][j] = true;
                    peopleNum = 1;
                    dfs(i, j);
                    peopleNums.add(peopleNum);
                }
            }
        }
    }

    public static void dfs(int x, int y) {
        for (int i = 0; i < DIR_N; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (canGo(nx, ny)) {
                visit[nx][ny] = true;
                peopleNum++;
                dfs(nx, ny);
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static boolean canGo(int x, int y) {
        return inRange(x, y) && arr[x][y] == 1 && !visit[x][y];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        solution();

        System.out.println(peopleNums.size());

        Collections.sort(peopleNums);

        for (int peopleNum : peopleNums) {
            System.out.println(peopleNum);
        }
    }
}
