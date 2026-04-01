import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Solution{
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int T = Integer.parseInt(br.readLine().trim());
    for(int tc=1; tc<=T; tc++){
        int N = Integer.parseInt(br.readLine().trim()); 
        int[][] dp = new int[N][N];

        sb.append("#").append(tc).append("\n");
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                
                if (j == 0 || j == i) {
                    dp[i][j] = 1;
                } 
               
                else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
                
                sb.append(dp[i][j]).append(" ");
            }
           
            sb.append("\n");
        }
    }
    System.out.print(sb);
  }
}