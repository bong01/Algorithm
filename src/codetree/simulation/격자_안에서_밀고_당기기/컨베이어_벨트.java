package codetree.simulation.격자_안에서_밀고_당기기;

import java.util.Scanner;

public class 컨베이어_벨트 {
    static int n;
    static int t;
    static int len;
    static int[] arr;

    public static void solution() {
        for (int i = 0; i < t; i++) {
            int temp = arr[len - 1];
            for (int j = len - 1; j >= 1; j--) {
                arr[j] = arr[j - 1];
            }
            arr[0] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        t = sc.nextInt();
        len = 2 * n;
        arr = new int[len];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = n; i < len; i++) {
            arr[i] = sc.nextInt();
        }

        solution();

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();

        for (int i = n; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
