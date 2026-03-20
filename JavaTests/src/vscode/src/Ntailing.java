import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ntailing {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int[] dp = new int[1001];

        for(int i=0; i<N+1; i++){
            if(i<4){
                dp[i] = i;
                continue;
            }
            dp[i] = (dp[i-1] + dp[i-2])%10007;        
        }

        System.out.print(dp[N]);
    }
}
