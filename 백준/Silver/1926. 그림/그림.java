import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1926. 그림
 * @author neogul02
 * 
 * 1. 도화지 세로크기 N 가로크기 M 입력받기
 * 2. 도화지 정보 입력받기 (0은 색칠 안된 부분 1은 색칠된 부분)
 * 3. 도화지 전체 탐색하면서 색칠된 부분이 나오면 bfs탐색 시작
 * 
 * ! 대각선은 연결 x, 상하좌우 연결 = 연결된 그림
 * 
 * 출력 - 첫째줄 그림의 개수, 둘째줄 가장 넓은 그림의 넓이
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static char[][] paintMap;
    static boolean[][] visited;

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int paintCnt = 0; // 그림 개수
    static int maxPaintArea = 0; // 가장 넓은 그림
    static int tempArea = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();

        System.out.print(sb); 
    }

    private static void solve() {
        // 전체 맵 보면서 1을 찾으면 bfs 시작하고 덩어리 cnt ++;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (visited[i][j] == true) // 방문한곳이면 pass
                    continue;

                if (paintMap[i][j] == '1') { // 방문 안한곳인데, 그림이면 덩어리 탐색 시작
                    tempArea = 1; // i, j 시작점 1 포함
                    paintCnt++; // 덩어리 개수 +1
                    bfs(i, j);
                    maxPaintArea = Math.max(maxPaintArea, tempArea);
                }
            }
        }
        sb.append(paintCnt).append('\n').append(maxPaintArea);

    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        
        queue.add(new int[] { x, y }); // '1' 위치, x, y 부터 탐색
        visited[x][y] = true;
        
        while (queue.isEmpty() == false) {
            int[] temp = queue.poll();
            int tempX = temp[0];
            int tempY = temp[1];

            for (int idx = 0; idx < 4; idx++) {
                int nx = tempX + dx[idx];
                int ny = tempY + dy[idx];

                // 1. out of bound
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;
                
                // 2. check visited
                if (visited[nx][ny] == true)
                    continue;

                // 3. '0' -> pass only search '1'
                if (paintMap[nx][ny] == '0')
                    continue;

                // 4. 상하좌우 탐색위치가 '1' 일때 = 넓이 +1
                tempArea++;
                visited[nx][ny] = true; // 탐색위치 방문 처리
                queue.add(new int[] { nx, ny });
            }
        }  
    }
    
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paintMap = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine().trim());
        for (int j = 0; j < M; j++) {
            paintMap[i][j] = st.nextToken().charAt(0);
        }
    }
    }
}