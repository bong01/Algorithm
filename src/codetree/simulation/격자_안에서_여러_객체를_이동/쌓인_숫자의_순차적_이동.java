package codetree.simulation.격자_안에서_여러_객체를_이동;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 쌓인_숫자의_순차적_이동 {
    static int n;
    static int m;
    static List<Integer>[][] arr;
    static int[] dx = {0, 0, 1, 1, 1, -1, -1, -1};
    static int[] dy = {1, -1, 0, 1, -1, 0, 1, -1};

    public static void simulate(int num) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j].contains(num)) {
                    int max = Integer.MIN_VALUE;
                    int mx = -1;
                    int my = -1;
                    for (int k = 0; k < 8; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (inRange(nx, ny) && !arr[nx][ny].isEmpty()) {
                            int maxInList = findMaxInList(nx, ny);
                            if (maxInList > max) {
                                max = maxInList;
                                mx = nx;
                                my = ny;
                            }
                        }
                    }

                    // 움직일 곳이 없다면 끝내기
                    if (mx == -1) {
                        return;
                    }

                    int idxOfNum = arr[i][j].indexOf(num);

                    // 이동
                    for (int k = idxOfNum; k < arr[i][j].size(); k++) {
                        arr[mx][my].add(arr[i][j].get(k));
                    }

                    // 기존에 저장되어 있던 원소들 삭제
                    while (arr[i][j].get(arr[i][j].size() - 1) != num) {
                        arr[i][j].remove(arr[i][j].size() - 1);
                    }
                    arr[i][j].remove(arr[i][j].size() - 1);
                    return;
                }
            }
        }
    }

    public static int findMaxInList(int x, int y) {
        int max = Integer.MIN_VALUE;
        for (int num : arr[x][y]) {
            if (num > max) max = num;
        }
        return max;
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new ArrayList[n][n];
        m = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = new ArrayList<>();
                arr[i][j].add(sc.nextInt());
            }
        }

        for (int i = 0; i < m; i++) {
            int num = sc.nextInt();
            simulate(num);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!arr[i][j].isEmpty()) {
                    for (int k = arr[i][j].size() - 1; k >= 0; k--) {
                        System.out.print(arr[i][j].get(k) + " ");
                    }
                } else {
                    System.out.print("None");
                }
                System.out.println();
            }
        }
    }
}
