import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1010. 다리 놓기
 * @author neogul02
 * 
 * - 서쪽에는 N개 사이트, 동쪽에는 M개 사이트, N<=M
 * - 한 사이트에는 최대 한개의 다리만 연결 가능
 * - 
 * 1. N개의 사이트에서 M개의 사이트로 다리를 놓는 경우의 수 구하기
 *  -> 서쪽 기준이니까 M C N 으로 풀이
 *  
 */
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
    // dp[i][j] : i개 중에서 j개를 선택하는 경우의 수
    int[][] dp = new int[M + 1][N + 1];
    result = 0;

    for (int i = 0; i <= M; i++) {
        // j는 i개를 넘을 수 없고, 우리가 필요한 N까지만 계산
        for (int j = 0; j <= Math.min(i, N); j++) {
            
            // 기본 케이스 1: i개 중 0개를 뽑는 경우 (안 뽑는 것도 1가지)
            // 기본 케이스 2: i개 중 i개를 다 뽑는 경우 (1가지)
            if (j == 0 || i == j) {
                dp[i][j] = 1;
            } 
            // 점화식: (나를 포함해서 뽑는 경우) + (나를 빼고 뽑는 경우)
            else {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
    }
    
    result = dp[M][N];
  }

  public static void input() throws IOException{
    st = new StringTokenizer(br.readLine().trim());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
  }
  
}
