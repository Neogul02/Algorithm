import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 연산횟수의 최솟값을 구해야함
 * 1. 더하는건 그냥 이전 숫자에서 +1 한 번 추가
 * 2. i/2 위치에서 *2 하면 i 니까 [i/2] + 1
 * 3. i/3 위치에서 *3 하면 i 니까 [i/3] + 1
 */
public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();

  static int N;
  static int[] dp;
  public static void main(String[] args) throws IOException{
    N = Integer.parseInt(br.readLine().trim());

    dp = new int[N+1];
    dp[1] = 0;
    
    for(int i=2; i<N+1; i++){
      // 1을 더하는 경우
      dp[i] = dp[i-1] + 1;
      
      // 2로 나누는 경우
      if(i%2==0) dp[i] = Math.min(dp[i], dp[i/2]+1);

      // 3으로 나누는 경우
      if(i%3==0) dp[i] = Math.min(dp[i], dp[i/3]+1);
    }

    System.out.print(dp[N]);
  }
}