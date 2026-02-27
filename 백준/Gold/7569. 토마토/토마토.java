import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 7569.토마토
 * @author neogul02
 * 
 * 1. 격자모양 박스를 쌓아 올린 토마토 박스
 * 2. 잘 익은 토마토(1)이랑 안익은 토마토(0)가 섞여있음 -> 잘 익은 토마토 옆에 있으면 맛있게 익어버린다..!
 * 3. 대각선은 안되고 상하좌우 + 위 + 아래 박스 까지 인접해잇다는 설정
 * 4. 모든 토마토가 익을때까지 며칠이 걸리는가? 
 *
 * 3차원 배열 박스에서 bfs 하면 될듯?
 * 
 * 맨 아래층 - 맨 위층 시뮬레이션을 동시에 진행해서 모든 토마토 0이 1이 될때가 며칠인지?
 * 
 * 1. M, N, H 가로 세로 높이 정보를 입력받고 맨 위층과 맨 아래층을 초기화 한다 = 높이가 1이라면? 
 * 최소 일수 / 처음부터 다 익음 = 0 / 못익으면 -1
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int M, N, H;
    static int[][][] box;
    static int[][][] visited;
    static Queue<int[]> queue = new ArrayDeque<>();

    static int[] dx = { -1, 1, 0, 0, 0, 0 };
    static int[] dy = {  0, 0,-1, 1, 0, 0 };
    static int[] dz = {  0, 0, 0, 0,-1, 1 };

    public static void main(String[] args) throws IOException {
        input(); // 배열 초기화 + 큐 세팅
        System.out.print(bfs()); // 탐색 후 결과 출력
    }

    private static int bfs() {
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0]; // 행
            int y = cur[1]; // 열
            int z = cur[2]; // 높이 - 층

            for (int d = 0; d < 6; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                int nz = z + dz[d];

                // out of bound
                if (nx < 0 || nx >= N)
                    continue;
                if (ny < 0 || ny >= M)
                    continue;
                if (nz < 0 || nz >= H)
                    continue;

                // 빈칸(-1)이나 이미 익은 토마토(1)인 경우는 skip
                if (visited[nz][nx][ny] != 0)
                    continue; // 빈칸or이미익음 skip

                // 익지 않은 토마토(0)인 경우
                visited[nz][nx][ny] = visited[z][x][y] + 1; // 하루 추가
                queue.add(new int[] { nx, ny, nz }); // 다음 탐색 지점 추가
            }
        }

        int result = 0;
        for (int z = 0; z < H; z++) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    if (visited[z][x][y] == 0)
                        return -1; // 못 익은 토마토 존재
                    result = Math.max(result, visited[z][x][y]);
                }
            }
        }

        return result - 1; // 
    }
    
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        M = Integer.parseInt(st.nextToken()); // 가로 (열)
        N = Integer.parseInt(st.nextToken()); // 세로 (행)
        H = Integer.parseInt(st.nextToken()); // 높이 (층)

        box     = new int[H][N][M];
        visited = new int[H][N][M];

        for (int z = 0; z < H; z++) {
            for (int x = 0; x < N; x++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int y = 0; y < M; y++) {
                    box[z][x][y] = Integer.parseInt(st.nextToken());

                    if (box[z][x][y] == 1) {
                        queue.add(new int[]{x, y, z}); // 익은 토마토 시작점 추가
                        visited[z][x][y] = 1; // 0 일차 
                    }
                    if (box[z][x][y] == -1) {
                        visited[z][x][y] = -1;
                    }
                }
            }
        }
    }
}