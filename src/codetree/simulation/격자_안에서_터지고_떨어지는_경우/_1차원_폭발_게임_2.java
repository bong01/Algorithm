package codetree.simulation.격자_안에서_터지고_떨어지는_경우;

import java.util.Scanner;

public class _1차원_폭발_게임_2 {
    static int n;
    static int m;
    static int[] arr;
    static int[] temp;

    public static void solution() {
        boolean isExploded;
        int currIdx;

        do {
            isExploded = false;
            currIdx = 0;

            while (currIdx < n) {
                int endIdx = getEndIdxOfExplosion(currIdx, arr[currIdx]);

                if (endIdx - currIdx + 1 >= m) {
                    cutArray(currIdx, endIdx);
                    isExploded = true;
                } else {
                    currIdx++;
                }
            }
        } while (isExploded);
    }

    public static void cutArray(int startIdx, int endIdx) {
        int cutLen = endIdx - startIdx + 1;

        for (int i = endIdx + 1; i < n; i++) {
            arr[i - cutLen] = arr[i];
        }

        n -= cutLen;
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
