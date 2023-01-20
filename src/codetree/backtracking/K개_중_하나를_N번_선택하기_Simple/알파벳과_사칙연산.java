package codetree.backtracking.K개_중_하나를_N번_선택하기_Simple;

import java.util.Scanner;

public class 알파벳과_사칙연산 {
    static final int MAX_N = 6;

    static String expression;
    static int ans = Integer.MIN_VALUE;
    static int[] num = new int[MAX_N];

    public static void findMax(int cnt) {
        if (cnt == MAX_N) {
            ans = Math.max(ans, calc());
            return;
        }

        for (int i = 1; i <= 4; i++) {
            num[cnt] = i; // 맨 처음 값 설정 후 재귀로
            findMax(cnt + 1);
        }
    }

    public static int calc() {
        int length = expression.length();
        int value = conv(0); // 맨 처음 값으로 초기화

        for (int i = 2; i < length; i += 2) {
            if (expression.charAt(i - 1) == '+') {
                value += conv(i);
            } else if (expression.charAt(i - 1) == '-') {
                value -= conv(i);
            } else {
                value *= conv(i);
            }
        }

        return value;
    }

    public static int conv(int idx) {
        return num[expression.charAt(idx) - 'a'];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        expression = sc.next();
        findMax(0);
        System.out.println(ans);
    }
}
