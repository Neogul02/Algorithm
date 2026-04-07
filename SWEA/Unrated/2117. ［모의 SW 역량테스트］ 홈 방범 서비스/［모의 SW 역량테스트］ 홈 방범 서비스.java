import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2117. 홈 방범 서비스
 * @author neogul02
 * 
 * 세콤이 손해를 보지 않으면서 홈방범 서비스를 가장 많은 집들에 제공하는 서비스 영역일때, 집들의 수를 출력
 * 
 * 정보
 * N*N 격자판에 1이면 집, 0이면 빈칸
 * 서비스 영역의 크기는 K 일때 , 운영비용은 서비스영역의 면적과 동일함 = K*K + (K-1)*(K-1)
 * 보안회사가 손해를 보지 않는다 = 운영비용보다 집에서 받는 돈이 많다 = 집의 수 * M >= 운영비용
 * 
 * 구현해야할 부분
 * 1. 서비스 영역의 중심과 크기를 정해야함 (중심은 격자판의 모든 칸이 될 수 있음, 크기는 1부터 최대 N+1까지 가능)
 *  1-0. 가지치기 조건 = 집의 수가 maxHome보다 작거나, 회사가 손해면 가지치기 ()
 *  1-1. 0,0 부터 중심점을 잡으면서 반복하는데
 *  1-2. 영역에서 잡히는 집의 수를 maxHome으로 갱신,
 *  1-3. 영역의 크기를 1부터 N+1까지 늘려가면서 반복
 * 
 * 2. 마름모인 영역을 어떻게 탐색하지?
 *  2-1. 누적합? dp? 같이 1칸부터 저장해두고 영역의 크기가 커질 때마다 추가되는 부분만 탐색해서 집의 수를 갱신하는 방법도 있을듯
 *  2-2. 마름모의 꼭짓점에서 k 거리를 이동하면서 테두리를 탐색하는 방법으로 탐색
 * 
 */
class Solution{
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int N, M;
  static int[][] map;
  static int totalHomes;

  public static void main(String[] args) throws IOException{
    int T = Integer.parseInt(br.readLine().trim());
    for(int tc=1; tc<=T; tc++){
      sb.append("#").append(tc).append(" ");
      input();
      solve();
    }
    System.out.print(sb);
  }

  public static void solve(){
    int maxHome = Integer.MIN_VALUE; // 손해보지 않는 최대 집의 수

    // r=0, c=0 부터 중심으로 잡고 영역의 크기를 1부터 N+1까지 늘려가면서 탐색
    for(int r=0; r<N; r++){
      for(int c=0; c<N; c++){
        int tempHomeCnt = 0; // 현재 영역에 잡히는 집의 수

        // "영역전개" N+1까지 늘려가면서 마름모 모양으로 탐색
        for(int k=1; k<=N+1; k++){
          if(k==1 && map[r][c] == 1) tempHomeCnt++; // 영역의 크기가 1일 때는 중심점만 탐색
          else{
            tempHomeCnt += countHome(r, c, k-1);
          }

          // 가지치기 1 = 현재 영역에 잡히는 집의 수가 maxHome보다 작으면 더 이상 탐색할 필요 없음
          if(tempHomeCnt <= maxHome) continue;

          int secomCost = k*k + (k-1)*(k-1); // 운영비용 계산
          int pay = tempHomeCnt * M; // 집에서 받는 돈 계산

          // 가지치기 2 = 회사가 손해보는 경우도 더 이상 탐색할 필요 없음
          if(pay< secomCost) continue;

          // 현재 영역에 잡히는 집의 수가 maxHome보다 크고, 회사가 손해보지 않는 경우에만 maxHome 갱신
          maxHome = Math.max(maxHome, tempHomeCnt); // 최대 집의 수 갱신
        }
      }
    }
    sb.append(maxHome).append("\n");
  }

  // 마름모의 꼭짓점에서 k 거리를 이동하면서 테두리를 탐색
  final static int[] moveR = {1, 1, -1, -1};
  final static int[] moveC = {1, -1, -1, 1};

  // 중심에서부터 k 거리를 마름모 모양으로 테두리 탐색하기
  public static int countHome(int r, int c, int k){
    int homeCnt = 0;

    int[] dr = {-k, 0, k, 0}; // 상, 우, 하, 좌
    int[] dc = {0, k, 0, -k};

    for (int i = 0; i < 4; i++) {
      int currR = r + dr[i];
      int currC = c + dc[i];

      for (int j = 0; j < k; j++) {
        if (currR >= 0 && currR < N && currC >= 0 && currC < N) {
          if (map[currR][currC] == 1) homeCnt++;
        }
        currR += moveR[i];
        currC += moveC[i];
      }
    }
 
    return homeCnt;
  }

  public static void input() throws IOException{
    st = new StringTokenizer(br.readLine().trim());
    N = Integer.parseInt(st.nextToken()); // 격자 크기 N
    M = Integer.parseInt(st.nextToken()); // 집에서 받는 돈 M

    map = new int[N][N];
    totalHomes = 0;

    for(int i=0; i<N; i++){
      st = new StringTokenizer(br.readLine().trim());
      for(int j=0; j<N; j++){
        map[i][j] = Integer.parseInt(st.nextToken());
        if(map[i][j] == 1) totalHomes++;
      }
    }
  }
}