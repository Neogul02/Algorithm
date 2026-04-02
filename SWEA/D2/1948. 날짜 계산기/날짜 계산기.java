import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine().trim());
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // 월, 일로 이루어진 날짜 2개를 입력받아 두번째 날짜가 첫번째날짜의 며칠째인지??
        for(int tc=1; tc<=T; tc++){
           st = new StringTokenizer(br.readLine().trim());
           int month_A = Integer.parseInt(st.nextToken());
           int day_A = Integer.parseInt(st.nextToken());
           int month_B = Integer.parseInt(st.nextToken());
           int day_B = Integer.parseInt(st.nextToken());

           sb.append("#").append(tc).append(" ");

           // 달이 같은 경우
           if(month_A == month_B) {
            sb.append(Math.abs(day_B-day_A+1)).append("\n");
            continue;
           }
            
           // 달이 다른 경우
           int result = 0;
           int startMonth = days[month_A] - day_A + 1;
           for(int i = month_A+1; i<month_B; i++){
            result += days[i];
           }
           result += startMonth + day_B;

           
           sb.append(result).append("\n");
        }

        System.out.print(sb);
    }
}