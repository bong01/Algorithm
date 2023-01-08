package codetree.simulation.격자_안에서_터지고_떨어지는_경우;

import java.util.Scanner;

public class _1차원_폭발_게임 {
    static int n, m;
    static int[] arr;
    static int[] temp;

    public static void solution() {
        while (bomb()) ;
    }

    public static boolean bomb() {
        boolean result;
        temp = arr.clone();

        for (int i = 0; i < n - 1; i++) {
            int startIdx = i;
            int endIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] == arr[i]) {
                    endIdx++;
                } else {
                    break;
                }
            }
            if (endIdx - startIdx + 1 >= m) {
                for (int j = startIdx; j <= endIdx; j++) {
                    temp[j] = 0;
                }
            }
            i = endIdx;
        }

        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (temp[i] != 0) {
                arr[idx++] = temp[i];
            }
        }

        result = n != idx;
        n = idx;

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        if (m == 1) {
            System.out.println(0);
        } else {
            solution();
            System.out.println(n);

            for (int i = 0; i < n; i++) {
                System.out.println(arr[i]);
            }
        }
    }
}
