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
        // 시각 2개를 입력받아 더하기

        for(int tc=1; tc<=T; tc++){
           st = new StringTokenizer(br.readLine().trim());

           int si_A = Integer.parseInt(st.nextToken());
           int bun_A = Integer.parseInt(st.nextToken());
           int si_B = Integer.parseInt(st.nextToken());
           int bun_B = Integer.parseInt(st.nextToken());

           int result_si = si_A+si_B;
           
           int result_bun = bun_A + bun_B;

           if(result_bun>=60){
            result_si ++;
            result_bun = result_bun % 60;
           }

           if(result_si>12){
            result_si = result_si-12;
           }

           sb.append("#").append(tc).append(" ")
           .append(result_si).append(" ").append(result_bun).append("\n");
    

        }

        System.out.print(sb);
    }
}