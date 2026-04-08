import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 17070. 파이프 옮기기 1
 * @author neogul02
 * 
 * (1,1)이랑 (1,2) 에서 시작. (N,N)까지 도달하는 모든 경우의 수 구하기
 * 
 * 1. →, ↘, ↓ 방향 총 세가지
 * 2. DP 적으로 생각해보자.. 파이프가 N,N에 도달했을때의 모양은 3가지, 가로, 세로, 대각선 모양임
 * 3. 그럼 이전 상태를 추측해보면 가로로 도달했을 경우에는 이전상태가 가로거나 대각선이여야하며 벽이 없어야함
 * 4. 세로의 이전상태는 세로거나 대각선, 대각선으로 도달한 경우는 가로 세로 대각선 모두 가능함
 * 5. 그럼 가로로 도달했을경우, 세로로 도달했을경우, 대각선으로 도달했을경우 총 3가지 경우를 계산해서 더하면 답인가?
 * 
 * # 만약 (N,N-1), (N,N)으로 파이프가 가로로 도달했으면 이전 상태는 (N,N-2) (N,N-1) 이거나 (N-1,N-2) (N,N-1) 대각선 이여야함
 * # 만약 (N-1,N), (N,N)으로 파이프가 세로로 도달했으면 이전 상태는 (N-2,N),(N-1,N) 이거나 (N-2,N-1) (N-1,N)
 * # 만약 (N-1,N-1), (N,N)으로 파이프가 대각선으로 도달했으면 이전 상태는 3가지 
 * # (N-2,N-2), (N-1,N-1) 이거나 (N-1,N-2) (N-1,N-1) 이거나 (N-2,N-1) (N-1,N-1)
 * 
 * + 여기에 추가로 이전 위치에 벽이 있다면 그 부분은 제외하고 세기
 * + 이게 파이프를 딱 놓는게 아니라 움직이면서 연결하는걸로, 경로상에 벽이 있으면 안된다는 점으로 생각하고 풀이해야함
 */
public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  public static void main(String[] args) throws IOException{
    input();
    solve();
    System.out.print(result);
  }

  static int result;

  public static void solve(){
    int[][][] dp = new int[3][N][N];
    dp[0][0][1] = 1;

    for(int r=0; r<N; r++){
      for(int c=1; c<N; c++){
        // 초기값 skip
        if(r == 0 && c == 1) continue;

        // 벽이면 skip
        if(map[r][c] == 1) continue;

        // 가로로 도달한 경우
        dp[0][r][c] = dp[0][r][c-1] + dp[2][r][c-1];

        if(r<1) continue; // 세로랑 대각선으로 도달한 경우는 r이 1이상이여야 함

        // 세로로 도달한 경우 
        dp[1][r][c] = dp[1][r-1][c] + dp[2][r-1][c];

        // 대각선으로 도달한 경우 (r-1,c-1) (r-1,c) (r,c-1) 모두 벽이 없어야 함
        if (map[r-1][c-1] == 0 && map[r-1][c] == 0 && map[r][c-1] == 0) {
          dp[2][r][c] = dp[0][r-1][c-1] + dp[1][r-1][c-1] + dp[2][r-1][c-1];
        } 
      }
    }

    result = dp[0][N-1][N-1] + dp[1][N-1][N-1] + dp[2][N-1][N-1];
  }

  static int N;
  static int[][] map;

  public static void input() throws IOException{
    N = Integer.parseInt(br.readLine().trim());
    map = new int[N][N];

    for(int r=0; r<N; r++){
      st = new StringTokenizer(br.readLine().trim());
      for(int c=0; c<N; c++){
        map[r][c] = Integer.parseInt(st.nextToken());
      }
    }
    // System.out.println(Arrays.deepToString(map));
  }
  
}
