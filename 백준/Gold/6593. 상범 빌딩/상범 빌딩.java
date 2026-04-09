
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 6593. 상범빌딩
 * @author neogul02
 * 
 * - 상범빌딩은 정육면체, 대각선 이동은 불가능, L은 층 수, R은 행 수, C는 열 수
 * - 동서남북상하 6방향으로 이동 가능, 1분에 한 칸 이동, 출구로 이동하면 탈출 성공
 * - '#' 은 벽, '.'는 빈 칸, 'S'는 시작 지점, 'E'는 출구
 */
public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int L, R, C;
  static char[][][] building;
  static boolean[][][] visited;

  static int[] startIdx = new int[3];

  static int[] dl = {0, 0, 0, 0, 1, -1}; // 상하
  static int[] dr = {0, 0, 1, -1, 0, 0}; // 동서
  static int[] dc = {1, -1, 0, 0, 0, 0}; // 남북
  
  public static void main(String[] args) throws IOException{
    while (true) { 
      input();
      if(L==0 && R==0 && C==0) break;
      bfs();
    }
    System.out.print(sb);
  }

  public static void bfs() {
    ArrayDeque<int[]> queue = new ArrayDeque<>();
    queue.add(new int[] {startIdx[0], startIdx[1], startIdx[2], 0}); // 층, 행, 열, 이동 시간
    visited[startIdx[0]][startIdx[1]][startIdx[2]] = true;

    while(queue.isEmpty() != true){
      int[] temp = queue.poll();
      int l = temp[0];
      int r = temp[1];
      int c = temp[2];
      int time = temp[3];

      for(int d=0; d<6; d++) {
        int nl = l + dl[d];
        int nr = r + dr[d];
        int nc = c + dc[d];

        if(nl<0 || nr<0 || nc<0 || nl>=L || nr>=R || nc>=C) continue;
        if(building[nl][nr][nc] == '#') continue;
        if(visited[nl][nr][nc] == true) continue;

        // 탈출 성공
        if(building[nl][nr][nc] == 'E') {
          sb.append("Escaped in ").append(time+1).append(" minute(s).").append("\n");
          return;
        }

        visited[nl][nr][nc] = true;
        queue.add(new int[] {nl, nr, nc, time+1});
      }
    }
    // 탈출 실패
    sb.append("Trapped!").append("\n");

  }

  public static void input() throws IOException {
    st = new StringTokenizer(br.readLine());
    L = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    building = new char[L][R][C];
    visited = new boolean[L][R][C];

    for(int l=0; l<L; l++) {
      for(int r=0; r<R; r++) {
        String temp = br.readLine().trim();
        for(int c=0; c<C; c++) {

          building[l][r][c] = temp.charAt(c);

          if(building[l][r][c] == 'S') {
            startIdx[0] = l;
            startIdx[1] = r;
            startIdx[2] = c;
          }
        }
      }
      br.readLine(); // 구분용 빈 줄
    }
    

  }
}
