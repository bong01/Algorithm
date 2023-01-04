package codetree.simulation.격자_안에서_밀고_당기기;

import java.util.Scanner;

public class 최단_Run_Length_인코딩 {
    static String str;

    public static int solution(String str) {
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < str.length(); i++) {
            shift();
            answer = Math.min(answer, encoding());
        }
        return answer;
    }

    public static void shift() {
        // String 이므로 substring 메서드 이용하여 shift
        str = str.substring(str.length() - 1) + str.substring(0, str.length() - 1);
    }

    public static int encoding() {
        StringBuilder sb = new StringBuilder();

        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else{
                sb.append(str.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        sb.append(str.charAt(str.length() - 1)).append(count);
        return sb.length();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        str = sc.next();
        int answer = solution(str);
        System.out.println(answer);
    }
}
