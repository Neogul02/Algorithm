import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 0 현재속도유지
 * 1 가속
 * 2 감속
 * 
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine().trim());

        for(int tc=1; tc<=T; tc++){
            int N = Integer.parseInt(br.readLine().trim());

            int distance = 0;
            int speed = 0;
            // cmd 갯수만큼 반복
            for(int i=0; i<N; i++){
                
                if(speed<0) speed =0;

                st = new StringTokenizer(br.readLine().trim());

                int cmd = Integer.parseInt(st.nextToken());
                if(cmd == 0) {
                    if(speed>0){
                        distance += speed;
                    }
                    continue;
                }
                    
                int ms = Integer.parseInt(st.nextToken());
                
                if(cmd == 1){
                    speed += ms;
                }else{
                    speed -= ms;
                }

                if(speed>0){
                    distance += speed;
                }

            }

            sb.append("#").append(tc).append(" ")
            .append(distance).append("\n");
        }

        System.out.print(sb);
    }
}