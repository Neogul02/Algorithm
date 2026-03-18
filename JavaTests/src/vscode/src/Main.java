<<<<<<< Updated upstream
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 기념하려는 날
        int N = Integer.parseInt(br.readLine().trim());

        // 2014년 4월 2일을 사귄날로 시작
        int year = 2014;
        int month = 4;
        int day = 2;

        for (int i = 1; i < N; i++) {
            day++;
            // 윤년일때의 2월 
            if (month == 2) {
                if (isLeapYear(year)) {
                    if (day > 29) {
                        day = 1;
                        month++;
                    }
                } else {
                    if (day > 28) {
                        day = 1;
                        month++;
                    }
                }
            }
            // 4월, 6월, 9월, 11월은 30일까지
            else if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day > 30) {
                    day = 1;
                    month++;
                }
            } else {
                if (day > 31) {
                    day = 1;
                    month++;
                }
            }

            // 12월이 넘어가면 new year
            if (month > 12) {
                month = 1;
                year++;
            }
        }
        System.out.printf("%d-%02d-%02d", year, month, day);
    }

    public static boolean isLeapYear(int year) {
        
        if (year % 400 == 0)
            return true;
        if (year % 100 == 0)
            return false;
        if (year % 4 == 0)
            return true;

        return false;
=======
class Main{
    public static void main(String[] args) {
        double a = 0.1;
        double b = 0.2;
        double c = 0.3;
        // 0.1 + 0.2는 0.3과 정확히 같지 않기 때문에 false가 출력됩니다.
        System.out.println(a + b == c);


        String user_str = "hello";
        
        System.out.println(user_str.toUpperCase());
>>>>>>> Stashed changes
    }
}