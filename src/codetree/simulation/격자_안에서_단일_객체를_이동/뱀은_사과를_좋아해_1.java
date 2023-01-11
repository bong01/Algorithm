package codetree.simulation.격자_안에서_단일_객체를_이동;

import java.util.*;

public class 뱀은_사과를_좋아해_1 {
    static final int APPLE = 1;

    static int n;
    static int m;
    static int k;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Deque<Point> snail = new LinkedList<>();
    static Map<Character, Integer> dirs = new HashMap<>();
    static List<Command> commands = new ArrayList<>();

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Command {
        char dir;
        int step;

        public Command(char dir, int step) {
            this.dir = dir;
            this.step = step;
        }
    }


    public static int solution() {
        int t = 0;

        dirs.put('U', 0);
        dirs.put('D', 1);
        dirs.put('R', 2);
        dirs.put('L', 3);

        int x = 0;
        int y = 0;
        snail.offer(new Point(x, y));

        for (Command cmd : commands) {
            char dir = cmd.dir;
            int step = cmd.step;
            for (int i = 0; i < step; i++) {
                t++;
                Point head = snail.peekLast();
                int nx = head.x + dx[dirs.get(dir)];
                int ny = head.y + dy[dirs.get(dir)];

                if (!inRange(nx, ny)) {
                    return t;
                }

                if (arr[nx][ny] == APPLE) {
                    arr[nx][ny] = 0;
                } else {
                    snail.poll();
                }

                if (isTwisted(nx, ny)) {
                    return t;
                }

                // 뱀 머리 이동
                snail.offer(new Point(nx, ny));
            }
        }
        return t;
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static boolean isTwisted(int x, int y) {
        // 움직이는 도중 몸이 꼬여 겹쳐졌을 경우
        for (Point point : snail) {
            if (point.x == x && point.y == y) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n][n];
        m = sc.nextInt();
        k = sc.nextInt();

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            arr[x][y] = APPLE;
        }

        for (int i = 0; i < k; i++) {
            char d = sc.next().charAt(0);
            int p = sc.nextInt();
            commands.add(new Command(d, p));
        }

        int answer = solution();
        System.out.println(answer);
    }
}
