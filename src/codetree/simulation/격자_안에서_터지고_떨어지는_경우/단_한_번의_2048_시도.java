package codetree.simulation.격자_안에서_터지고_떨어지는_경우;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 단_한_번의_2048_시도 {
    static int[][] arr = new int[4][4];
    static int[][] temp = new int[4][4];
    static char dir;

    public static void solution() {
        Map<Character, Integer> dirMap = new HashMap<>();
        dirMap.put('L', 0);
        dirMap.put('D', 1);
        dirMap.put('R', 2);
        dirMap.put('U', 3);
        for (int i = 0; i < dirMap.get(dir); i++) {
            rotate();
        }
        shift();
        bind();
        shift();
        for (int i = 0; i < 4 - dirMap.get(dir); i++) {
            rotate();
        }
    }

    public static void shift() {
        for (int i = 0; i < 4; i++) {
            int idx = 0;
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] != 0) {
                    temp[i][idx++] = arr[i][j];
                }
            }
            while (idx < 4) {
                temp[i][idx++] = 0;
            }
        }

        copy();
    }

    public static void bind() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == arr[i][j + 1]) {
                    arr[i][j] = 2 * arr[i][j];
                    arr[i][j + 1] = 0;
                }
            }
        }
    }

    public static void rotate() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = arr[4 - j - 1][i];
            }
        }

        copy();
    }

    public static void copy() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = temp[i][j];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        dir = sc.next().charAt(0);
        solution();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
