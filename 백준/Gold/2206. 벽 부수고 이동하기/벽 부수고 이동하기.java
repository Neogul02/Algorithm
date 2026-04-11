import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 2206. 벽 부수고 이동하기
 * @author neogul02
 * 1. N*M 크기의 맵이 주어진다.
 * 2. 0은 이동할 수 있는 칸, 1은 이동할 수 없는 칸이다.
 * 3. (1,1)에서 (N,M)으로 이동하는 최단 경로를 구하되, 벽을 최대 한 번 부술 수 있다.
 * 
 * - 벽을 부순다? -> 한 개 까지 부술 수 있으니까 벽을 부수는 경우와 부수지 않는 경우로 나눠서 탐색
 * - visited를 부순경우랑 안부순 경우로 나눠서 3차원 배열로 만들어서 방문체크
 * 
 */
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        input();
        bfs();
        System.out.print(sb);
    }

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    // bfs돌다가 벽을 한 번 부수는 경우를 추가해서 탐색
    public static void bfs(){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0,0,0,1}); // x좌표, y좌표, 벽을 부순 횟수, 거리
        visited[0][0][0] = true;

        while(queue.isEmpty()!=true){
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];
            int breakCnt = temp[2];
            int dist = temp[3];

            // 목적지 도달
            if(x == N-1 && y == M-1){
                sb.append(dist);
                return;
            }

            for(int d=0; d<4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];

                // outbound
                if(nx<0 || nx>=N || ny<0 || ny>=M) continue;

                // 방문한곳은 패스
                if(visited[nx][ny][breakCnt]) continue;

                // 벽이 없는 경우
                if (map[nx][ny] == '0') {
                    // 현재 상태(breakCnt)에서 방문한 적이 없을 때만
                    if (visited[nx][ny][breakCnt]==false) {
                        visited[nx][ny][breakCnt] = true;
                        queue.add(new int[]{nx, ny, breakCnt, dist + 1});
                    }
                }

                // 벽이 있는 경우
                if(map[nx][ny] == '1' && breakCnt == 0){
                    // 아직 벽을 안 부쉈고, 부순 상태로 방문한 적이 없을 때
                    if (visited[nx][ny][1]==false) {
                        visited[nx][ny][1] = true;
                        queue.add(new int[]{nx, ny, 1, dist + 1});
                    }
                }

            }

        }
        // 도달 못하는 경우
        sb.append(-1);
        
    }

    static int N, M;
    static char[][] map;
    static boolean[][][] visited;
    
    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M][2];

        for(int i=0; i<N; i++){
            String temp = br.readLine().trim();
            for(int j=0; j<M; j++){
                map[i][j] = temp.charAt(j);
            }
        }

    }   

}