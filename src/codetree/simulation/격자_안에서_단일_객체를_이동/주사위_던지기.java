package codetree.simulation.격자_안에서_단일_객체를_이동;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 주사위_던지기 {
    static int n, m, x, y;
    static int u = 1, f = 2, r = 3;
    static int[][] arr;
    static char[] dirs;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static int solution() {
        int sum = 0;

        Map<Character, Integer> dirMap = new HashMap<>();
        dirMap.put('L', 0);
        dirMap.put('R', 1);
        dirMap.put('U', 2);
        dirMap.put('D', 3);

        arr[x][y] = 7 - u;

        for (int i = 0; i < m; i++) {
            int dir = dirMap.get(dirs[i]);

            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (!inRange(nx, ny)) {
                continue;
            }

            if (dir == 0) {
                int nu = r;
                int nf = f;
                int nr = 7 - u;
                u = nu;
                f = nf;
                r = nr;
            } else if (dir == 1) {
                int nu = 7 - r;
                int nf = f;
                int nr = u;
                u = nu;
                f = nf;
                r = nr;
            } else if (dir == 2) {
                int nu = f;
                int nf = 7 - u;
                int nr = r;
                u = nu;
                f = nf;
                r = nr;
            } else {
                int nu = 7 - f;
                int nf = u;
                int nr = r;
                u = nu;
                f = nf;
                r = nr;
            }

            int down = 7 - u;
            arr[nx][ny] = down;

            x = nx;
            y = ny;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != 0) sum += arr[i][j];
            }
        }

        return sum;
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][n];

        m = sc.nextInt();
        dirs = new char[m];

        x = sc.nextInt() - 1;
        y = sc.nextInt() - 1;

        for (int i = 0; i < m; i++) {
            dirs[i] = sc.next().charAt(0);
        }

        int answer = solution();
        System.out.println(answer);
    }
}
