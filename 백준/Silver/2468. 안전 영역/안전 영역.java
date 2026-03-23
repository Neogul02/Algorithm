
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N; // 행과 열의 개수
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int maxHeight;

    static int safeArea;

    public static void main(String[] args) throws IOException{
        input();
        solve();
        System.out.print(safeArea);
    }

    /**
     * 안전한 영역의 갯수가 최대인 경우를 구해서 출력.
     * 최대 높이만큼 물이차면 전부 잠기므로 maxHeight -1 까지 완탐bfs해서 덩어리 수 update
     * 현재 물의 높이가 0이면 아무일도 안일어나므로 1~maxHeight -1까지 돌면서 검사
     */
    public static void solve(){
        safeArea = 1; // 아무지역도 물에 안잠기면 전체 1덩어리
        
        // 높이는 최대 100번 * (100*100)칸 탐색 = 1,000,000 번 + a
        for(int tempHeight = 1; tempHeight<maxHeight; tempHeight++){
            visited = new boolean[N][N];
            int tempSafeArea = 0;

            // 모든 칸 탐색하며 덩어리 찾기
            for(int r=0; r<N; r++){
                for(int c=0; c<N; c++){
                    if(visited[r][c] == false && map[r][c] > tempHeight){
                        bfs(r, c, tempHeight);
                        tempSafeArea++;
                    }      
                }
            }
            if(safeArea<tempSafeArea) safeArea = tempSafeArea;
        }
    }

    public static void bfs(int r, int c, int tempHeight){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;

        while(queue.isEmpty()!=true){
            int[] temp = queue.poll();
            int tempX = temp[0];
            int tempY = temp[1];

            for(int d=0; d<4; d++){
                int nx = tempX + dx[d];
                int ny = tempY + dy[d];

                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;

                if(visited[nx][ny] == true) continue;

                if(map[nx][ny] <= tempHeight) continue;

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }
    }
    
    public static void input() throws IOException{
        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];

        maxHeight = 0;

        for(int r=0; r<N; r++){
            st = new StringTokenizer(br.readLine().trim());
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(maxHeight<map[r][c]) maxHeight = map[r][c];
            }
        }
    }
}
