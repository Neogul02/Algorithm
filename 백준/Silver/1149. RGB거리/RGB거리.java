import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;

    static int[][] dp;
    static int[] R, G, B;

    public static void main(String[] args) throws IOException{
        input();
        solve();
    }

    public static void solve(){

        // Init.
        dp[1][0] = R[1];
        dp[1][1] = G[1];
        dp[1][2] = B[1];

        // Logic.
        for(int i=2; i<N+1; i++){  
            // 레드 옆집은 그린이거나 블루여야함
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2])+R[i];
            
            // 그린 옆집은 레드이거나 블루여야함
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2])+G[i];
            
            // 블루 옆집은 레드이거나 그린이여야함
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1])+B[i];
        }

        int result = Integer.MAX_VALUE;
        result = Math.min(dp[N][0], Math.min(dp[N][1],dp[N][2]));
        System.out.print(result);
    }

    public static void input() throws IOException{
        N = Integer.parseInt(br.readLine().trim());

        R = new int[N+1];
        G = new int[N+1];
        B = new int[N+1];

        dp = new int[N+1][3];

        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine().trim());
            R[i] = Integer.parseInt(st.nextToken());
            G[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }
    }
}
