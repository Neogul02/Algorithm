import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1949. 등산로 조성
 * @author neogul02
 * 
 * 등산로를 만들기위한 부지는 N x N 배열
 * 규칙1. 등산로는 가장 높은 봉우리에서 시작해야함
 * 규칙2. 등산로는 상하좌우로만 만들 수 있음
 * 규칙3. 높은 지형에서 낮은 지형으로만 만들어야함
 * 규칙4. 딱 한 곳을 정해서 최대 K 만큼 깎아서 낮출 수 있음
 * 
 * 가장 긴 등산로의 길이를 구하면 됨
 * 
 * 1. 가장 높은 봉우리 위치를 찾는게 첫 번째 -> 최대 5개
 * 2. 각 봉우리에서 DFS 탐색을 통해 등산로를 만들 수 있는 최대 길이를 구함
 * 3. 깎을 수 있는 위치를 고려하여 최대 길이 갱신???
 * 4. DFS 탐색 시, 방문 여부를 체크 하기 위한 visited 배열 필요
 * 
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[][] map;
    static int N, K;

    static int maxLen = 0;
    static boolean[][] visited;

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            input();
            int ans = solve();
            sb.append("#").append(tc).append(" ")
                    .append(ans).append("\n");
        }
        System.out.print(sb);
    }

    static private int solve() {
        maxLen = 0; // 최대 길이 초기화

        // 1. 가장 높은 봉우리 높이 찾기
        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        // 2. 가장 높은 봉우리 위치를 찾고 DFS 탐색 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == maxHeight) {
                    visited = new boolean[N][N];
                    dfs(i, j, 1, false);
                }
            }
        }
        // 3. 최대 길이 반환

        return maxLen;
    }

    /**
     * DFS 탐색인데 깎는경우랑 안깎는 경우 둘 다 고려
     * - 깎는 경우는 isCut 플래그로 관리, 한번 깎으면 다시 깎을 수 없음
     * - 현재 위치에서 상하좌우로 이동하면서 다음 위치가 현재 위치보다 낮으면 그냥 이동
     * - 다음 위치가 현재 위치보다 높지만, 깎아서 낮출 수 있다면 깎아서 이동?
     * - 방문 처리도 필요, DFS 탐색이 끝나면 방문 해제해서 백 트래킹
     * 
     * - 가지치기 가능?
     * - 현재 길이 + 남은 최대 이동 가능 거리 < maxLen 이면 더 이상 탐색할 필요 없음
     */
    static void dfs(int x, int y, int len, boolean isCut) {
        // 현재 길이 + 남은 최대 이동 가능 거리 < maxLen 이면 가지치기
        // if (len + (N - 1 - x) + (N - 1 - y) < maxLen) {
        //     return;
        // }

        // 현재 위치 방문 처리
        visited[x][y] = true;
        maxLen = Math.max(maxLen, len); // 최대 길이 갱신

        for (int d = 0; d < 4; d++) { // 상하좌우 탐색
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            // 아웃 바운드 체크
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            
            // 방문 여부 체크
            if (visited[nx][ny]) continue;
            
            if (map[nx][ny] < map[x][y]) { // 경우 1. 현재 위치보다 낮으면 그냥 이동하기
                dfs(nx, ny, len + 1, isCut);
            }
            // 경우 2. 현재 위치보다 높지만, 깎아서 낮출 수 있다면
            
            else if (isCut == false && map[nx][ny] >= map[x][y] && map[nx][ny] - K < map[x][y]) {
                int originalHeight = map[nx][ny]; // 원래 높이 저장
                map[nx][ny] = map[x][y] - 1; // 다 깎지 않고 현재 위치보다 1 낮게 깎기
         
                dfs(nx, ny, len + 1, true);
                map[nx][ny] = originalHeight; // 원래 높이로 복구
                
            }
        }

        visited[x][y] = false;

    }
    
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        };
    }
}