package codetree.simulation.격자_안에서_터지고_떨어지는_경우;

import java.util.Scanner;

public class 최적의_십자_모양_폭발 {
    static int n;
    static int[][] arr, arr_copy;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static int solution() {
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                saveArr();
                explode(i, j);
                shift();
                int cnt = check();
                answer = Math.max(answer, cnt);
                loadArr();
            }
        }

        return answer;
    }

    public static void explode(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int maxStep = arr[x][y] - 1;
            for (int step = 1; step <= maxStep; step++) {
                int nx = x + dx[i] * step;
                int ny = y + dy[i] * step;
                if (!inRange(nx, ny)) {
                    break;
                }
                arr[nx][ny] = 0;
            }
        }
        arr[x][y] = 0;
    }

    public static void shift() {
        int[][] temp = new int[n][n];

        for (int j = 0; j < n; j++) {
            int tempIdx = n - 1;
            for (int i = n - 1; i >= 0; i--) {
                if (arr[i][j] != 0) {
                    temp[tempIdx--][j] = arr[i][j];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = temp[i][j];
            }
        }
    }

    public static int check() {
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (inRange(nx, ny) && arr[i][j] != 0 && arr[i][j] == arr[nx][ny]) {
                        cnt++;
                    }
                }
            }
        }

        return cnt / 2;
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void saveArr() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr_copy[i][j] = arr[i][j];
            }
        }
    }

    public static void loadArr() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = arr_copy[i][j];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][n];
        arr_copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        int answer = solution();
        System.out.println(answer);
    }
}
