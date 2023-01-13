package baekjoon.simulation;

import java.util.Scanner;

public class 미세먼지_안녕 {
    static final int AIR_CLEANER = -1;
    static final int BLANK = 0;

    static int r, c, t;
    static int ax1 = -1, ax2 = -1;
    static int[][] arr;
    static int[][] temp;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void solution() {
        // 공기 청정기 위치 구하기
        boolean isFirst = true;

        for (int x = 0; x < r; x++) {
            if (arr[x][0] == AIR_CLEANER) {
                if (isFirst) {
                    ax1 = x;
                    isFirst = false;
                } else {
                    ax2 = x;
                }
            }
        }

        while (t-- > 0) {
            spreadDust();
            runAirCleaner();
        }
    }

    // 미세먼지 확산
    public static void spreadDust() {
        initTemp();

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (arr[x][y] != BLANK) {
                    int cnt = 0; // 확산된 방향 수
                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if (!inRange(nx, ny) || arr[nx][ny] == AIR_CLEANER) continue;
                        temp[nx][ny] += arr[x][y] / 5; // 확산
                        cnt++;
                    }
                    temp[x][y] += arr[x][y] - (arr[x][y] / 5) * cnt; // 기존 칸 갱신
                }
            }
        }

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                arr[x][y] = temp[x][y];
            }
        }
    }

    public static void initTemp() {
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                temp[x][y] = BLANK;
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }

    // 공기청정기 작동
    public static void runAirCleaner() {
        // 위
        for (int x = ax1 - 1; x >= 1; x--) {
            arr[x][0] = arr[x - 1][0];
        }

        for (int y = 0; y < c - 1; y++) {
            arr[0][y] = arr[0][y + 1];
        }

        for (int x = 0; x < ax1; x++) {
            arr[x][c - 1] = arr[x + 1][c - 1];
        }

        for (int y = c - 1; y >= 2; y--) {
            arr[ax1][y] = arr[ax1][y - 1];
        }
        arr[ax1][1] = BLANK;

        // 아래
        for (int x = ax2 + 1; x <= r - 2; x++) {
            arr[x][0] = arr[x + 1][0];
        }

        for (int y = 0; y < c - 1; y++) {
            arr[r - 1][y] = arr[r - 1][y + 1];
        }

        for (int x = r - 1; x >= ax2 + 1; x--) {
            arr[x][c - 1] = arr[x - 1][c - 1];
        }

        for (int y = c - 1; y >= 2; y--) {
            arr[ax2][y] = arr[ax2][y - 1];
        }
        arr[ax2][1] = BLANK;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        t = sc.nextInt();

        arr = new int[r][c];
        temp = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        solution();

        int answer = 0;
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (arr[x][y] != BLANK && arr[x][y] != AIR_CLEANER) {
                    answer += arr[x][y];
                }
            }
        }

        System.out.println(answer);
    }
}
