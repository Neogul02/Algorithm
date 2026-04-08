import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 17070. 파이프 옮기기 1
 * @author neogul02
 * 
 * (1,1)이랑 (1,2) 에서 시작. (N,N)까지 도달하는 모든 경우의 수 구하기
 * 1. →, ↘, ↓ 방향 총 세가지
 * 2. 완전탐색 DFS로 풀이, 파이프의 방향에 따라서 다음에 갈 수 있는 방향이 달라짐
 * 3. 그럼 가로로 도달했을경우, 세로로 도달했을경우, 대각선으로 도달했을경우 총 3가지 경우를 계산해서 더하면 정답
 */
public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  public static void main(String[] args) throws IOException{
    input();
    dfs(0, 1, 0);
    System.out.print(result);
  }

  static int result;

  // dir 0: 가로, 1: 세로, 2: 대각선
  public static void dfs(int r, int c, int dir){
    // 기저 조건 (N,N)에 도달했을 때, 경우의 수 1 증가
    if(r == N-1 && c == N-1){
      result++;
      return;
    }

    // 가로
    if(dir == 0 || dir == 2){
      // outbound && 벽이 없는 경우
      if(c+1 < N && map[r][c+1] == 0){
        dfs(r, c+1, 0);
      }
    }

    // 세로
    if(dir == 1 || dir == 2){
      // outbound && 벽이 없는 경우
      if(r+1 < N && map[r+1][c] == 0){
        dfs(r+1, c, 1);
      }
    }

    // 대각선
    if(r+1 < N && c+1 < N){ // outbound
      // 대각선으로 도달하려면 (r,c+1), (r+1,c), (r+1,c+1) 모두 벽이 없어야 함
      if(map[r][c+1] == 0 && map[r+1][c] == 0 && map[r+1][c+1] == 0){
        dfs(r+1, c+1, 2);
      }
    }
    
  }

  static int N;
  static int[][] map;

  public static void input() throws IOException{
    N = Integer.parseInt(br.readLine().trim());
    map = new int[N][N];
    result = 0;

    for(int r=0; r<N; r++){
      st = new StringTokenizer(br.readLine().trim());
      for(int c=0; c<N; c++){
        map[r][c] = Integer.parseInt(st.nextToken());
      }
    }
    // System.out.println(Arrays.deepToString(map));
  }
  
}
