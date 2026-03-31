import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();

  // 하 우 상 좌
  static int[] dr = {0, 1, 0, -1};
  static int[] dc = {-1, 0, 1, 0};

  public static void main(String[] args) throws IOException{

    int N = Integer.parseInt(br.readLine());
    int target = Integer.parseInt(br.readLine());

    int[][] snail = new int[N][N];
    int num = N*N;
    int r = 0, c = 0, dir = 0;

    int targetR = 0, targetC = 0;

    for(int i=0; i<N*N; i++){

      snail[r][c] = num;

      if(num == target){
        targetR = r+1;
        targetC = c+1;
      }

      int nr = r + dr[dir];
      int nc = c + dc[dir];

      if(nr<0 || nc<0 || nr>=N || nc>=N || snail[nr][nc] != 0){
        dir = (dir+1) % 4; // 방향 변경하기
        nr = r + dr[dir];
        nc = c + dc[dir];
      }

      r = nr;
      c = nc;

      num--;
    }

    for(int i=0; i<N; i++){
      for(int j=0; j<N; j++){
        sb.append(snail[i][j]+" ");
      }
      sb.append("\n");
    }

    sb.append(targetR).append(" ").append(targetC);
    System.out.print(sb);
  }
}