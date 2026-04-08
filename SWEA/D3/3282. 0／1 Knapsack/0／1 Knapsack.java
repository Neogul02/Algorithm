import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;
    public static void main(String[] args) throws IOException {

      int T = Integer.parseInt(br.readLine().trim());

      for (int tc = 1; tc <= T; tc++) {
        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken()); // 물건의 개수
        int K = Integer.parseInt(st.nextToken()); // 가방의 최대 부피

        // dp[w] : 부피 w에서 얻을 수 있는 최대 가치
        int[] dp = new int[K + 1];

        for (int i = 0; i < N; i++) {
          st = new StringTokenizer(br.readLine().trim());
          int V = Integer.parseInt(st.nextToken()); // 부피
          int C = Integer.parseInt(st.nextToken()); // 가치

          // 배낭의 최대 부피 K부터 현재 물건의 부피 V까지 역순으로 탐색
          for (int w = K; w >= V; w--) {
            dp[w] = Math.max(dp[w], dp[w - V] + C);
          }
        }
        
        sb.append("#").append(tc).append(" ")
        .append(dp[K]).append("\n");
      }
      System.out.print(sb);
    }
}