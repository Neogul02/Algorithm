import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 2117. 홈 방범 서비스
 * @author neogul02
 * 
 */
class Solution{
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int N, M;
  static int[][] map;
  static ArrayList<int[]> homes; // 집 위치 저장
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
      
        int[] dists = new int[totalHomes]; // 중심점에서 각 집까지의 거리 저장

        for(int i=0; i<totalHomes; i++){
          int[] home = homes.get(i);
          dists[i] = Math.abs(r - home[0]) + Math.abs(c - home[1]);
        }
        Arrays.sort(dists); // 거리를 오름차순으로 정렬

        for(int k=1; k<=N+1; k++){
          int tempHomeCnt = 0; // 현재 영역에 잡히는 집의 수

          // 영역의 크기가 k일 때, 잡히는 집의 수는 거리 배열에서 k-1 이하인 집의 수
          for(int dist : dists){
            if(dist < k) tempHomeCnt++;
            else break; // 거리가 k 이상인 집은 더 이상 잡히지 않으므로 반복 종료
          }

          if(tempHomeCnt == 0) continue; // 잡히는 집이 없는 경우 가지치기
          if(tempHomeCnt < maxHome) continue; // 이미 최대 집의 수보다 작은 경우 가지치기
        

          int cost = k*k + (k-1)*(k-1); // 운영비용
          int pay = tempHomeCnt * M; // 집에서 받는 돈

          if(pay >= cost){ // 손해보지 않는 경우
            maxHome = Math.max(maxHome, tempHomeCnt);
          }
          
        }
      }
    }
    sb.append(maxHome).append("\n");
  }


  public static void input() throws IOException{
    st = new StringTokenizer(br.readLine().trim());
    N = Integer.parseInt(st.nextToken()); // 격자 크기 N
    M = Integer.parseInt(st.nextToken()); // 집에서 받는 돈 M

    map = new int[N][N];
    totalHomes = 0;
    homes = new ArrayList<>();

    for(int i=0; i<N; i++){
      st = new StringTokenizer(br.readLine().trim());
      for(int j=0; j<N; j++){
        map[i][j] = Integer.parseInt(st.nextToken());
        if(map[i][j] == 1){
          totalHomes++;
          homes.add(new int[]{i, j}); // 집 위치 저장
        }
      }
    }
  }
}