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

        for(int tc=1; tc<=T; tc++){
            sb.append("#").append(tc).append("\n");

            int N = Integer.parseInt(br.readLine().trim());
            int cnt = 1;

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine().trim());
                char token = st.nextToken().charAt(0);
                int repeat = Integer.parseInt(st.nextToken());

                for(int j=0; j<repeat; j++){
                    sb.append(token);

                    if(cnt%10==0) sb.append("\n");

                    cnt++;
                }
                
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}