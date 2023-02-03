package programmers.level_2;

public class 호텔_대실 {
    static final int MAX_MIN = 1_450; // 24 * 60 + 10
    static final int HOUR = 60;
    static final int CLEAN_TIME = 10;

    public static int solution(String[][] book_time) {
        int answer = 0;

        int[] rooms = new int[MAX_MIN];

        for (String[] bt : book_time) {
            String in = bt[0];
            String out = bt[1];

            int inMin = convert2min(in);
            int outMin = convert2min(out);

            rooms[inMin]++;
            rooms[outMin + CLEAN_TIME]--;
        }

        for (int i = 1; i < MAX_MIN; i++) {
            rooms[i] += rooms[i - 1];
            answer = Math.max(answer, rooms[i]);
        }

        return answer;
    }

    static int convert2min(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int min = Integer.parseInt(split[1]);

        return hour * HOUR + min;
    }
}
