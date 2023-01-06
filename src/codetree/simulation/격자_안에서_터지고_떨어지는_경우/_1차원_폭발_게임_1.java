package codetree.simulation.격자_안에서_터지고_떨어지는_경우;

import java.util.Scanner;

public class _1차원_폭발_게임_1 {
    static int n;
    static int m;
    static int[] arr;
    static int[] temp;

    public static void solution() {
        boolean isExploded;

        do {
            isExploded = false;

            for (int i = 0; i < n; i++) {
                if (arr[i] == 0) {
                    continue;
                }

                int endIdx = getEndIdxOfExplosion(i, arr[i]);

                if (endIdx - i + 1 >= m) {
                    fillZero(i, endIdx);
                    isExploded = true;
                }
            }

            moveToTemp();
            copy();
        } while (isExploded);
    }

    public static void copy() {
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
    }

    public static void moveToTemp() {
        int idx = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                temp[idx++] = arr[i];
            }
        }

        n = idx;
    }

    public static void fillZero(int startIdx, int endIdx) {
        for (int i = startIdx; i <= endIdx; i++) {
            arr[i] = 0;
        }
    }

    public static int getEndIdxOfExplosion(int startIdx, int num) {
        int endIdx = startIdx + 1;

        while (endIdx < n) {
            if (arr[endIdx] == num) {
                endIdx++;
            } else {
                break;
            }
        }

        return endIdx - 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];
        temp = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        solution();

        System.out.println(n);
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
    }
}
