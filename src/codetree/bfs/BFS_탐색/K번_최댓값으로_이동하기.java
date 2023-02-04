package codetree.bfs.BFS_탐색;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class K번_최댓값으로_이동하기 {
    static final int MAX_N = 100;
    static final Point NOT_EXISTS = new Point(-1, -1);

    static int n, k;
    static int r, c;
    static int[][] arr = new int[MAX_N][MAX_N];
    static boolean[][] visit = new boolean[MAX_N][MAX_N];

    static Queue<Point> q = new LinkedList<>();

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static Point cur;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void solution() {
        cur = new Point(r, c);

        for (int i = 0; i < k; i++) {
            initVisit();
            bfs();
            Point bestPos = NOT_EXISTS;
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (!visit[x][y] || x == cur.x && y == cur.y) continue;

                    Point newPos = new Point(x, y);
                    if (needUpdate(bestPos, newPos)) {
                        bestPos = newPos;
                    }
                }
            }
            if (bestPos == NOT_EXISTS) { // 움직일 수 없다면 stop
                break;
            } else {
                cur = bestPos;
            }
        }

    }

    static boolean needUpdate(Point bestPos, Point newPos) {
        if (bestPos.x == NOT_EXISTS.x && bestPos.y == NOT_EXISTS.y) {
            return true;
        }

        int bestX = bestPos.x;
        int bestY = bestPos.y;

        int newX = newPos.x;
        int newY = newPos.y;

        if (arr[newX][newY] != arr[bestX][bestY]) {
            return arr[newX][newY] > arr[bestX][bestY];
        }
        if (newX != bestX) {
            return newX < bestX; // 행이 더 작은지
        }
        return newY < bestY; // 행이 같다면 열이 더 작은지
    }

    static void initVisit() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visit[i][j] = false;
            }
        }
    }

    static void bfs() {
        int cx = cur.x;
        int cy = cur.y;

        visit[cx][cy] = true;
        q.add(cur);
        int targetNum = arr[cx][cy];

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (canGo(nx, ny, targetNum)) {
                    visit[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
        }
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    static boolean canGo(int x, int y, int startNum) {
        return inRange(x, y) && !visit[x][y] && arr[x][y] < startNum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt(); // 반복 횟수

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        // 시작 위치
        r = sc.nextInt() - 1;
        c = sc.nextInt() - 1;

        solution();

        System.out.println((cur.x + 1) + " " + (cur.y + 1));
    }
}
