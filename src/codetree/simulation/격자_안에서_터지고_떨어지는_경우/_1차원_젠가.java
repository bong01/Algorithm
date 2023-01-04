package codetree.simulation.격자_안에서_터지고_떨어지는_경우;

import java.util.Scanner;

public class _1차원_젠가 {
    static int n;
    static int[] arr;
    static int[] temp;

    public static void solution(int s, int e) {
        int idx = 0;

        for (int i = 0; i < n; i++) {
            if (i < s || i > e) {
                temp[idx++] = arr[i];
            }
        }

        arr = temp.clone();
        n = idx;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        temp = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < 2; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            solution(s - 1, e - 1);
        }

        // 출력
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
    }
}
