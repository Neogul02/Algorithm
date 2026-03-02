import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * 2667. 단지번호붙이기
 * @author neogul02
 * 
 * 1. 정사각형 모양 지도, N x N 크기, 1은 집이 있는 곳, 0은 집이 없는 곳
 * 2. 상하좌우로 연결된 집들은 같은 단지, 대각선은 연결 x
 * 출력. 첫째줄은 단지(덩어리) 개수 둘째줄부터는 각 단지에 속하는 집의 수를 오름차순으로 출력
 */
public class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int danziCnt = 0; // 단지 수
    static int tempArea;
    static ArrayList<Integer> danziArea = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();
        solve();

        System.out.print(sb);
    }
    
    private static void solve() {
        for (int i = 0; i < N; i++) { // 맵을 돌면서 1을 찾으면 bfs 탐색 시작 
            for (int j = 0; j < N; j++) {

                if (visited[i][j] == true) // 방문한곳이면 pass
                    continue;

                if (map[i][j] == '1') { // 방문 안했는데 1이면 새로운 단지
                    danziCnt++; // 단지 수 +1
                    tempArea = 1; // 현재 위치부터 시작, 1부터
                    bfs(i, j);
                    danziArea.add(tempArea);
                }

            }
        }
        
        sb.append(danziCnt).append('\n');
        danziArea.sort(null); // 오름차순 정렬로 출력해줘야함
        for (int danziAreaCnt : danziArea) {
            sb.append(danziAreaCnt).append('\n');
        }
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited[x][y] = true;
        queue.add(new int[] { x, y }); // 시작 위치 큐에 넣기

        while (queue.isEmpty() == false) { // 덩어리 찾기
            int[] temp = queue.poll();
            int tempX = temp[0];
            int tempY = temp[1];

            for (int idx = 0; idx < 4; idx++) {
                int nx = tempX + dx[idx];
                int ny = tempY + dy[idx];

                // 1. out of bound
                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                // 2. visited check
                if (visited[nx][ny] == true)
                    continue;
                 
                // 3. if [nx][ny] == '0' -> pass
                if (map[nx][ny] == '0')
                    continue;
                
                // 4. '1' 이면 다음 탐색 단지
                    tempArea++;
                    visited[nx][ny] = true; // 방문처리
                    queue.add(new int[] {nx, ny});
            }
        }
    }
    
    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        map = new char[N][N]; // N x N 크기 정사각형
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            map[i] = line.toCharArray();
        }
        
    }
}
