package codetree.simulation.격자_안에서_여러_객체를_이동;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 벽이_없는_충돌_실험 {
    static final int ASCII_NUM = 128;
    static final int BLANK = -1;
    static final int COORD_SIZE = 4000;
    static final int OFFSET = 2000;

    static int t, n;

    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {1, 0, 0, -1};

    static int[] mapper = new int[ASCII_NUM];

    static ArrayList<Marble> marbles = new ArrayList<>();
    static ArrayList<Marble> temp = new ArrayList<>();
    static int[][] tempMarbleIndex = new int[COORD_SIZE + 1][COORD_SIZE + 1];

    static int currTime;
    static int lastCollisionTime;

    static class Marble {
        int x;
        int y;
        int w;
        int dir;
        int num;

        public Marble(int x, int y, int w, int dir, int num) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.dir = dir;
            this.num = num;
        }
    }

    public static void move(Marble marble) {
        marble.x += dx[marble.dir];
        marble.y += dy[marble.dir];
    }

    // 배열로 구현하여 O(1) 탐색
    public static int findDuplicateMarble(Marble marble) {
        return tempMarbleIndex[marble.x][marble.y];
    }

    public static Marble collide(Marble m1, Marble m2) {
        if (m1.w > m2.w || (m1.w == m2.w && m1.num > m2.num)) {
            return m1;
        } else {
            return m2;
        }
    }

    public static boolean outOfActiveCoordinate(Marble marble) {
        int x = marble.x;
        int y = marble.y;

        return x < 0 || x > COORD_SIZE || y < 0 || y > COORD_SIZE;
    }

    public static void pushNextMarble(Marble marble) {
        if (outOfActiveCoordinate(marble)) {
            return;
        }

        int index = findDuplicateMarble(marble);

        if (index == BLANK) {
            temp.add(marble);
            int x = marble.x;
            int y = marble.y;
            tempMarbleIndex[x][y] = temp.size() - 1;
        } else {
            Marble newMarble = collide(temp.get(index), marble);
            temp.set(index, newMarble);
            lastCollisionTime = currTime;
        }
    }

    public static void simulate() {
        for (int i = 0; i < marbles.size(); i++) {
            Marble marble = marbles.get(i);
            move(marble);
            pushNextMarble(marble);
        }

        marbles = (ArrayList<Marble>) temp.clone();

        for (int i = 0; i < temp.size(); i++) {
            int x = temp.get(i).x;
            int y = temp.get(i).y;
            tempMarbleIndex[x][y] = BLANK;
        }
        temp = new ArrayList<>();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        mapper['U'] = 0;
        mapper['R'] = 1;
        mapper['L'] = 2;
        mapper['D'] = 3;

        t = sc.nextInt();

        for (int i = 0; i <= COORD_SIZE; i++) {
            for (int j = 0; j <= COORD_SIZE; j++) {
                tempMarbleIndex[i][j] = BLANK;
            }
        }
        while (t-- > 0) {
            marbles = new ArrayList<>();
            lastCollisionTime = -1;

            n = sc.nextInt();

            for (int i = 0; i < n; i++) {
                int x = sc.nextInt() * 2 + OFFSET;
                int y = sc.nextInt() * 2 + OFFSET;
                int w = sc.nextInt();
                char d = sc.next().charAt(0);

                marbles.add(new Marble(x, y, w, mapper[d], i));
            }

            for (int i = 1; i <= COORD_SIZE; i++) {
                currTime = i;
                simulate();
            }

            System.out.println(lastCollisionTime);
        }
    }
}
