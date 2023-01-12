package codetree.simulation.격자_안에서_여러_객체를_이동;

import java.util.*;

public class 벽이_있는_충돌_실험 {
    static int n, m, t;
    static Map<Character, Integer> dirs = new HashMap<>();
    static List<Node> nodes;
    static int[][] nodeCnt;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};

    static class Node {
        int x;
        int y;
        int d;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, d);
        }
    }

    public static int simulate() {
        // 2 * n 까지 돌리면 제자리
        for (int i = 0; i < 2 * n; i++) {
            for (Node node : nodes) {
                int nx = node.x + dx[node.d];
                int ny = node.y + dy[node.d];
                if (inRange(nx, ny)) {
                    // 이동
                    node.x = nx;
                    node.y = ny;
                } else {
                    // 벽에 부딪혔을 땐 방향 전환
                    node.d = 3 - node.d;
                }
            }

            // 중복 원소 모두 삭제
            removeDuplicateNode();
        }

        return nodes.size();
    }

    public static void removeDuplicateNode() {
        ArrayList<Node> temp = new ArrayList<>();

        for (Node node : nodes) {
            nodeCnt[node.x][node.y]++;
        }

        for (int i = 0; i < nodes.size(); i++) {
            if (!hasDuplicateNode(i)) {
                temp.add(nodes.get(i));
            }
        }

        for (Node node : nodes) {
            nodeCnt[node.x][node.y]--;
        }

        nodes = temp;
    }

    private static boolean hasDuplicateNode(int i) {
        Node node = nodes.get(i);
        return nodeCnt[node.x][node.y] >= 2; // 배열을 사용함으로써 O(M) 탐색
    }


    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();

        dirs.put('U', 0);
        dirs.put('R', 1);
        dirs.put('L', 2);
        dirs.put('D', 3);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            n = sc.nextInt();
            nodeCnt = new int[n][n];
            m = sc.nextInt();
            nodes = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                int x = sc.nextInt() - 1;
                int y = sc.nextInt() - 1;
                char d = sc.next().charAt(0);
                nodes.add(new Node(x, y, dirs.get(d)));
            }
            int answer = simulate();
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
