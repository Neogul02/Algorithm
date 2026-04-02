import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
 
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine().trim());

        for(int tc=1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine().trim());

            int result = 0;
            for(int i=1; i<=N; i++){
                if(i%2==0) result -= i;
                else result += i;
            }

            sb.append("#").append(tc).append(" ")
            .append(result).append("\n");
        }
        System.out.print(sb);
    }
}