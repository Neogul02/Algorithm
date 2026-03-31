import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();

  static int[] dr = {0,1,0,-1}; // 우 하 좌 상
  static int[] dc= {1,0,-1,0};
  public static void main(String[] args) throws IOException{
    int T = Integer.parseInt(br.readLine());
    for(int tc=1; tc<=T; tc++){
      sb.append("#").append(tc).append("\n");
      int N = Integer.parseInt(br.readLine());

      // 달팽이 숫자
      // 0,0이 1 부터 시작해서 시계방향으로 숫자가 증가하는 형태
      int[][] snail = new int[N][N];
      int num = 1;
      int r = 0, c = 0, dir = 0;

      for(int i=0; i<N*N; i++){
        snail[r][c] = num;

        int nr = r + dr[dir];
        int nc = c + dc[dir];

        if(nr<0 || nc<0 || nr>=N || nc>=N || snail[nr][nc] != 0){
          dir = (dir+1) % 4; // 방향 변경하기
          nr = r + dr[dir];
          nc = c + dc[dir];
        }

        r = nr;
        c = nc;

        num++;
      }

      for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
          sb.append(snail[i][j]+" ");
        }
        sb.append("\n");
      }
    }

    System.out.print(sb);
    
  } 
}