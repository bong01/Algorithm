package codetree.simulation.격자_안에서_여러_객체를_이동;

import java.util.ArrayList;
import java.util.Scanner;

public class 합쳐지는_구슬들 {
    static int n, m, t;
    static Marble[][] arr;
    static ArrayList<Marble>[][] temp;
    static int[] dirMapper = new int[128];
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};

    static class Marble {
        int d;
        int w;
        int num;

        public Marble(int d, int w, int num) {
            this.d = d;
            this.w = w;
            this.num = num;
        }
    }

    public static void simulate() {
        while (t-- > 0) {
            initTemp();
            moveAll();
            mix();
            copyFromTemp();
        }
    }

    public static void initTemp() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = new ArrayList<>();
            }
        }
    }

    // 합치기
    public static void mix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (temp[i][j].size() > 1) {
                    int nw = 0;
                    int nn = Integer.MIN_VALUE;
                    int nd = -1;
                    for (Marble marble : temp[i][j]) {
                        nw += marble.w;
                        if (marble.num > nn) {
                            nn = marble.num;
                            nd = marble.d;
                        }
                    }
                    temp[i][j].clear();
                    temp[i][j].add(new Marble(nd, nw, nn));
                }
            }
        }
    }

    public static void copyFromTemp() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!temp[i][j].isEmpty()) {
                    arr[i][j] = temp[i][j].get(0);
                } else {
                    arr[i][j] = null;
                }
            }
        }
    }

    // 범위 내 확인
    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    // 이동
    public static void moveAll() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (arr[x][y] != null) {
                    Marble marble = arr[x][y];
                    int nx = x + dx[marble.d];
                    int ny = y + dy[marble.d];
                    if (!inRange(nx, ny)) {
                        marble.d = 3 - marble.d;
                        temp[x][y].add(marble);
                    } else {
                        temp[nx][ny].add(marble);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        t = sc.nextInt();

        arr = new Marble[n][n];
        temp = new ArrayList[n][n];

        dirMapper['D'] = 0;
        dirMapper['L'] = 1;
        dirMapper['R'] = 2;
        dirMapper['U'] = 3;

        for (int i = 0; i < m; i++) {
            int r = sc.nextInt() -1;
            int c = sc.nextInt() -1;
            char d = sc.next().charAt(0);
            int w = sc.nextInt();

            arr[r][c] = new Marble(dirMapper[d], w, i + 1);
        }

        simulate();

        int cnt = 0;
        int mw = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != null) {
                    cnt++;
                    mw = Math.max(mw, arr[i][j].w);
                }
            }
        }

        System.out.println(cnt + " " + mw);
    }
}
