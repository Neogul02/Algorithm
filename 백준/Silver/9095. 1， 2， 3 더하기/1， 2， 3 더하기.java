import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine().trim());
        for(int tc=1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine().trim());
            solve();
        }
        System.out.print(sb);
    }

    public static void solve(){
        int[] dp = new int [11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i=4; i<=N; i++){
            dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
        }
        sb.append(dp[N]).append('\n');
    }
}