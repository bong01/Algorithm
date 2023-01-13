package codetree.simulation.격자_안에서_여러_객체를_이동;

import java.util.*;

public class 구슬의_이동 {
    static int n, m, t, k;
    static ArrayList<Tuple>[][] arr;
    static ArrayList<Tuple>[][] temp;

    static class Tuple implements Comparable<Tuple> {
        int x;
        int y;
        int z;

        public Tuple(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int compareTo(Tuple o) {
            if (x != o.x) return x - o.x;
            if (y != o.y) return y - o.y;
            return z - o.z;
        }
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static Tuple NextPos(int x, int y, int vNum, int d) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, 1, -1, 0};

        while (vNum-- > 0) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!inRange(nx, ny)) {
                d = 3 - d;
                nx = x + dx[d];
                ny = y + dy[d];
            }
            x = nx;
            y = ny;
        }

        return new Tuple(x, y, d);
    }

    public static void moveAll() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (int i = 0; i < arr[x][y].size(); i++) {
                    int v = arr[x][y].get(i).x;
                    int num = arr[x][y].get(i).y;
                    int d = arr[x][y].get(i).z;

                    int nx, ny, nd;
                    Tuple tuple = NextPos(x, y, -v, d);
                    nx = tuple.x;
                    ny = tuple.y;
                    nd = tuple.z;

                    temp[nx][ny].add(new Tuple(v, num, nd));
                }
            }
        }
    }

    public static void selectMarbles() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (temp[i][j].size() >= k) {
                    Collections.sort(temp[i][j]);
                    while (temp[i][j].size() > k) {
                        temp[i][j].remove(temp[i][j].size() - 1);
                    }
                }
            }
        }
    }

    public static void simulate() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = new ArrayList<>();
            }
        }

        moveAll();

        selectMarbles();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = (ArrayList<Tuple>) temp[i][j].clone();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        t = sc.nextInt();
        k = sc.nextInt();

        arr = new ArrayList[n][n];
        temp = new ArrayList[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = new ArrayList<>();
            }
        }

        int[] dirMapper = new int[128];
        dirMapper['U'] = 0;
        dirMapper['R'] = 1;
        dirMapper['L'] = 2;
        dirMapper['D'] = 3;

        for (int i = 0; i < m; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            char d = sc.next().charAt(0);
            int v = sc.nextInt();
            arr[r][c].add(new Tuple(-v, -(i + 1), dirMapper[d]));
        }

        while (t-- > 0) {
            simulate();
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer += arr[i][j].size();
            }
        }
        System.out.println(answer);
    }
}
