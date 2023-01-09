package codetree.simulation.격자_안에서_단일_객체를_이동;

import java.util.Scanner;

public class 벽_짚고_미로_탈출하기 {
    static int n, x, y, dir = 0;
    static char[][] arr;
    static int[][][] visit;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    public static int solution() {
        int count = 0;

        do {
            if (visit[x][y][dir] == 1) {
                return -1;
            }

            visit[x][y][dir]++;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (isWall(nx, ny)) { // 바라보고 있는 방향으로 이동 불가능
                rotateCounterClockWise();
            } else if (!inRange(nx, ny)) { // 탈출
                x = nx;
                y = ny;
                count++;
            } else {
                rotateClockWise();
                int rx = nx + dx[dir];
                int ry = ny + dy[dir];
                if (isWall(rx, ry)) {
                    rotateCounterClockWise(); // 다시 원래대로
                    x = nx;
                    y = ny;
                    count++;
                } else {
                    x = rx;
                    y = ry;
                    count += 2;
                }
            }
        } while (inRange(x, y));

        return count;
    }

    public static void rotateCounterClockWise() {
        dir = (dir + 1) % 4;
    }

    public static void rotateClockWise() {
        dir = (dir - 1 + 4) % 4;
    }

    public static boolean isWall(int x, int y) {
        return inRange(x, y) && arr[x][y] == '#';
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new char[n][n];
        visit = new int[n][n][4];
        x = sc.nextInt() - 1;
        y = sc.nextInt() - 1;

        for (int i = 0; i < n; i++) {
            String input = sc.next();
            for (int j = 0; j < n; j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        int answer = solution();
        System.out.println(answer);
    }
}
