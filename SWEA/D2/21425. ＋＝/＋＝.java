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
            st = new StringTokenizer(br.readLine().trim());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            int cnt = 0;

            while (A <= target && B <= target) {
                // 더 작은 쪽에 더 큰 쪽을 더해야 target에 가장 빨리 도달함
                if (A < B) {
                    A += B;
                } else {
                    B += A;
                }
                cnt++;
            }
            sb.append(cnt).append("\n");
        }

        System.out.print(sb);
    }
}