package codetree.bfs.BFS_탐색;

import java.util.*;

public class 돌_잘_치우기 {
    static final int MAX_N = 100;

    static int n, k, m;
    static int[][] arr = new int[MAX_N][MAX_N];

    static List<Point> sPos = new ArrayList<>();
    static List<Point> stonePos = new ArrayList<>();
    static List<Point> selectedStones = new ArrayList<>();

    static Queue<Point> q = new LinkedList<>();
    static boolean[][] visit = new boolean[MAX_N][MAX_N];

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int ans = Integer.MIN_VALUE;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void backtracking(int idx, int cnt) {
        if (idx == stonePos.size()) {
            if (cnt == m) ans = Math.max(ans, calc());
            return;
        }

        selectedStones.add(stonePos.get(idx));
        backtracking(idx + 1, cnt + 1);
        selectedStones.remove(selectedStones.size() - 1);

        backtracking(idx + 1, cnt);
    }

    public static int calc() {
        // 돌 치우기
        for (int i = 0; i < m; i++) {
            int x = selectedStones.get(i).x;
            int y = selectedStones.get(i).y;

            arr[x][y] = 0;
        }

        // visit 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visit[i][j] = false;
            }
        }

        // 시작 위치 큐에 넣기
        for (int i = 0; i < k; i++) {
            q.add(sPos.get(i));
            visit[sPos.get(i).x][sPos.get(i).y] = true;
        }

        bfs();

        // 치운 돌 원래대로
        for (int i = 0; i < m; i++) {
            int x = selectedStones.get(i).x;
            int y = selectedStones.get(i).y;

            arr[x][y] = 1;
        }

        // 방문 칸 수 세기
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visit[i][j]) cnt++;
            }
        }

        return cnt;
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (canGo(nx, ny)) {
                    q.add(new Point(nx, ny));
                    visit[nx][ny] = true;
                }
            }
        }
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    static boolean canGo(int x, int y) {
        return inRange(x, y) && !visit[x][y] && arr[x][y] == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] == 1) stonePos.add(new Point(i, j));
            }
        }

        for (int i = 0; i < k; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            sPos.add(new Point(r, c));
        }

        backtracking(0, 0);

        System.out.println(ans);
    }
}
