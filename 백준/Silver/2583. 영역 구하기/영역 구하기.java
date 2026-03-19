import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int M, N; // 가로 세로
    static int K; // 직사각형의 개수

    static int[][] map; // 모눈종이 정보
    static boolean[][] visited; // 방문 여부

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Integer> results = new ArrayList<>(); 

    public static void main(String[] args) throws Exception{
        input();
        bfs();
        System.out.print(sb);
    }

    public static void bfs(){
        // 모든 칸에 대해서 0인 덩어리 찾기
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] == 0 && visited[i][j] == false){
                    ArrayDeque<int[]> queue = new ArrayDeque<>();
                    queue.add(new int[]{i,j});
                    visited[i][j] = true;

                    int boundCnt = 1;

                    while(queue.isEmpty()!= true) { 
                        int[] temp = queue.poll();
                        int tempX = temp[0];
                        int tempY = temp[1];
                        for(int d=0; d<4; d++){
                            int nx = tempX + dx[d];
                            int ny = tempY + dy[d];

                            if(nx<0 || ny<0 || nx>=M || ny>=N) continue;

                            if(map[nx][ny] == 0 && visited[nx][ny]==false){
                                visited[nx][ny] = true;
                                queue.add(new int[] {nx, ny});
                                boundCnt++;
                            }
                        }
                    }
                    results.add(boundCnt);
                }
            }
           
        }
        results.sort((a,b)-> a-b);

        sb.append(results.size()).append('\n');

        for(int i=0; i<results.size(); i++){
            sb.append(results.get(i)).append(' ');
        }
 
    }

    public static void input() throws Exception{
        st = new StringTokenizer(br.readLine().trim()," ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];

        // K개 만큼 직사각형의 좌표 입력받기
        for(int k=0; k<K; k++){
            st = new StringTokenizer(br.readLine().trim()," ");
            int x1 = Integer.parseInt(st.nextToken()); // 왼쪽 아래
            int y1 = Integer.parseInt(st.nextToken());

            int x2 = Integer.parseInt(st.nextToken()); // 오른쪽 위
            int y2 = Integer.parseInt(st.nextToken());

            // (x1, y1)에서 (x2, y2)까지 직사각형 영역 표시하기
            // 0,0 이 map 왼쪽아래 기준임
            for(int i=y1; i<y2; i++){
                for(int j=x1; j<x2; j++){
                    map[i][j] = 1; // 직사각형 영역 표시
                }
            }
 
        }
    }
}