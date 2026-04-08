
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1952. 수영장 [DP로 풀기]
 * @author neogul02
 * 
 * 1년동안 수영장을 가장 싸게 이용할 수 있는 가격을 출력하기
 * 이용권 종류 4개 - 1일, 1달, 3달, 1년
 * 
 * 모든 경우의 수를 계산해볼까? 1년권은 제외하고 + 
 * 1. 모두 케이스에 1일권만 쓰는 경우
 * 2. 모든 케이스에 1달권만 쓰는 경우
 * 3. 1일권과 1달권을 섞는 경우
 * 4. 1일권과 3달권을 섞는 경우
 * 5. 1달권과 3달권을 섞는 경우 
 * 6. 1일권 1달권 3달권 다 섞는경우
 * 7. 1년권으로 퉁치는 경우
 * 
 * 가장 적게 지출하는 비용을 구하기
 * DP로 풀이하려면? -> dp[i] : i월까지 이용하는데 드는 최소 비용
 * 1. dp[i] = dp[i-1] + plan[i-1]
 * 2. dp[i] = dp[i-1] + price[1]
 * 3. dp[i] = dp[i-3] + price[2]
 * 4. dp[i] = price[3]
 * result = dp[12]과 price[3] 중 작은 값
 */
public class Solution {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int[] price; // 1일, 1달, 3달, 1년 이용권 가격
  static int[] plan; // 1월부터 12월까지 이용 계획
  static int result;

  public static void main(String[] args) throws IOException{
    int T = Integer.parseInt(br.readLine().trim());
    for(int tc=1; tc<=T; tc++){
      input();
      solve();
      sb.append("#").append(tc).append(" ").append(result).append("\n");
    }
    System.out.print(sb);
  }

  public static void solve(){
    // dp[i] : i월까지 이용하는데 드는 최소 비용
    int[] dp = new int[13];

    for(int i=1; i<=12; i++){
      // 1일권으로만 이용하는 경우
      dp[i] = dp[i-1] + plan[i-1] * price[0];

      // 1달권으로 이용하는 경우
      dp[i] = Math.min(dp[i], dp[i-1] + price[1]);

      // 3달권으로 이용하는 경우 (i가 3이상일 때만 계산)
      if(i >= 3){
        dp[i] = Math.min(dp[i], dp[i-3] + price[2]);
      } else {
        dp[i] = Math.min(dp[i], price[2]);
      }
    }

    // 1년권으로 퉁치는 경우
    result = Math.min(dp[12], price[3]);
  }

  public static void input() throws IOException{
    // 이용권 가격 입력
    price = new int[4];
    st = new StringTokenizer(br.readLine().trim());
    for(int i=0; i<4; i++){
      price[i] = Integer.parseInt(st.nextToken());
    }

    // 이용 계획 입력
    plan = new int[12];
    st = new StringTokenizer(br.readLine().trim());
    for(int i=0; i<12; i++){
      plan[i] = Integer.parseInt(st.nextToken());
    }
  }
  
}
