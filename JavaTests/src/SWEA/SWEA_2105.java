package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2105. 디저트 카페
 * @author neogul02
 *
 * N*N 크기의 맵이 주어지고 각 칸에는 디저트의 종류(숫자)가 적혀있다.
 * 제약 조건
 * 1. 하나의 카페에서 디저트를 먹는건 안된다.
 * 2. 대각선 + 사각형 모양으로 움직여야한다.
 * 3. 출발한 카페로 돌아와야한다.
 * 4. 왔던 길 또는 카페로 돌아오는 길은 안된다.
 *
 * 가장 디저트를 많이 먹을 수 있는 경로를 찾고 max로 먹을 수 있는 디저트의 개수를 출력하면 됨
 * 디저트카페 투어가 불가능? -1 출력
 */
public class SWEA_2105 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N; // 맵 한 변의 길이
    static int[][] dessertMap;
    static int maxDessertCnt;

    static boolean[][] visited; // 방문 체크 배열
    static boolean[] eatenDessert; // 먹은 디저트 체크 배열

    // 방향벡터 : 대각선 + 사각형 모양으로 움직여야한다. 4방향
    static int[] dr = {1, 1, -1, -1}; // 우하, 좌하, 좌상, 우상
    static int[] dc = {1, -1, -1, 1};
    static int startR;
    static int startC;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            input();
            solve();

            sb.append('#').append(tc).append(' ')
              .append(maxDessertCnt).append('\n');
        }
        System.out.print(sb);
    }

    /**
     * @solve
     * 1. 맵의 모든 위치를 시작점으로 시도해본다
     * 2. 각 시작점에서 DFS로 마름모 경로를 탐색한다
     */
    private static void solve() {
        // 최대 디저트 개수 -1로 초기화
        maxDessertCnt = -1;

        // 1. 맵의 모든 위치를 시작점으로 시도해본다
        // 가장 자리 탐색 제거 버전
        for (int i = 0; i < N-2; i++) {
            for (int j = 1; j < N-1; j++) {
                // 시뮬레이션마다 초기화
                eatenDessert = new boolean[101]; // 디저트 종류 1~100 초기화
                visited = new boolean[N][N]; // 방문 체크

                startR = i; // 시작 위치 저장 R
                startC = j; // 시작 위치 저장 C

                // dfs(현재행, 현재열, 시작행, 시작열, 현재방향, 먹은디저트수)
                // - 현재 위치: (i, j)
                // - 방향: 0 (우하 방향부터 시작)
                // - 먹은 디저트: 0개 (시작점은 아직 안 먹음)
                dfs(i, j, 0, 0);
            }
        }
    }

    private static void dfs(int r, int c, int dir, int dessertCnt) {
        // 기저 조건: 시작점으로 돌아왔는가?
        // 1. 현재 위치가 시작점과 같고
        // 2. 디저트를 먹은 상태여야 함 (dessertCnt > 0)
        // 3. 최소 4개 이상의 디저트를 먹어야 마름모가 성립
        //    (우하 1칸, 좌하 1칸, 좌상 1칸, 우상 1칸 = 최소 4칸)
        if (r == startR && c == startC && dessertCnt >= 4) {
            maxDessertCnt = Math.max(maxDessertCnt, dessertCnt); // 최댓값 갱신
            return; // 탐색 종료
        }

        for (int i = dir; i < 4; i++) {
            // 현재 방향에서 더 갈 수 있는가? (큰 마름모)
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 조건1. 맵 아웃 바운드 체크
            if (!(nr >= 0 && nr < N && nc >= 0 && nc < N)) continue;

            // 조건2. 시작점이 아닌데 이미 방문한 곳이면 패스
            if (visited[nr][nc] && !(nr == startR && nc == startC)) continue;

            // 조건3. 이미 먹은 디저트면 패스
            if (eatenDessert[dessertMap[nr][nc]]) continue;

            // 위 조건들을 모두 통과하면 다음 디저트 카페로 이동
            visited[nr][nc] = true; // 방문 체크
            eatenDessert[dessertMap[nr][nc]] = true; // 먹은 디저트 번호 체크

            // 다음 탐색
            dfs(nr, nc, i, dessertCnt + 1);

            // 백트래킹 - 방문 처리 해제, 먹은 디저트 표시 해제
            // 이전 디저트가게로 가서 다른 방향으로 탐색할 수 있도록
            visited[nr][nc] = false;
            eatenDessert[dessertMap[nr][nc]] = false;
        }
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        dessertMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dessertMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}