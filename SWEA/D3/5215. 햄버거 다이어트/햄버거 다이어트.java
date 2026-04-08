import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 5215. 햄버거 다이어트 [DP]
 * @author neogul02
 * 
 * - 햄버거 재료의 수 N, 칼로리 제한 L
 * - DP로 풀이 -> Knapsack 문제랑 비슷하게 생각
 * 1. dp[i][j] : i번째 재료까지 고려했을 때, 칼로리 j 이하로 얻을 수 있는 최대 점수
 */
public class Solution {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int N, L; // 햄버거 재료의 수, 칼로리 제한
  static int[][] ingredients; // 재료의 점수와 칼로리 정보
  static int result;

  public static void main(String[] args) throws IOException{
    int T = Integer.parseInt(br.readLine().trim());
    for(int tc=1; tc<=T; tc++){
      input();
      solve();
      sb.append("#").append(tc).append(" ")
      .append(result).append("\n");
    }
    System.out.print(sb);
    
  }

  public static void solve(){
    // dp[i][j] : i번째 재료까지 고려했을 때, 칼로리 j 이하로 얻을 수 있는 최대 점수
    int[][] dp = new int[N+1][L+1];

    for(int i=1; i<=N; i++){
      int score = ingredients[i-1][0]; // 맛 점수
      int calorie = ingredients[i-1][1]; // 칼로리

      for(int j=0; j<=L; j++){
        // i번째 재료를 선택하지 않는 경우
        dp[i][j] = dp[i-1][j]; // 이전 재료의 점수 유지

        // i번째 재료를 선택하는 경우 (칼로리가 j 이상이어야 함)
        if(j >= calorie){
          dp[i][j] = Math.max(dp[i][j], dp[i-1][j-calorie] + score); // 이전 재료에서 칼로리만큼 뺀 점수 + 현재 재료의 점수
        }
      }
    }
    result = dp[N][L];

  }

  public static void input() throws IOException{
    st = new StringTokenizer(br.readLine().trim());
    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());

    ingredients = new int[N][2];
    result = 0;
    for(int i=0; i<N; i++){
      st = new StringTokenizer(br.readLine().trim());
      ingredients[i][0] = Integer.parseInt(st.nextToken()); // 점수
      ingredients[i][1] = Integer.parseInt(st.nextToken()); // 칼로리
    }
  }
  
}