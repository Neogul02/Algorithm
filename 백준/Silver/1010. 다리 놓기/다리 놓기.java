import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int N, M; // 서쪽에는 N개, 동쪽에는 M개
  static int result;

  public static void main(String[] args) throws IOException {
    int T = Integer.parseInt(br.readLine().trim());
    for(int tc=1; tc<=T; tc++){
      input();
      solve();
      sb.append(result).append("\n");
    }
    System.out.print(sb);
  }

  public static void solve(){
    int[][] dp = new int[N+1][M+1];
    
    for(int n=1; n<=N; n++){
      for(int m=0; m<=M; m++){

        if(n==m){
          dp[n][m] = 1;
          continue;
        }
        if(m==0){
          dp[n][m] = 1;
          continue;
        }
        dp[n][m] = dp[n-1][m-1] + dp[n-1][m];
      }
    }
    // System.out.println(Arrays.deepToString(dp));
    result = dp[N][M];
  }

  public static void input() throws IOException{
    st = new StringTokenizer(br.readLine().trim());
    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
  }
}
