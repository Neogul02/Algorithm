package monthTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 인디 탈출
 * 0 빈공간, 1 벽, 2 바이러스, 3 삼성이 위치
 *
 * 시뮬레이션 순서 (매 턴):
 *   1. 탈출 가능한지 체크  (현재 위치에서 맵 밖으로 이동 가능하면 탈출)
 *   2. 삼성이 이동 확장   (이동 또는 제자리 대기 → 가능한 모든 위치 집합)
 *   3. 바이러스 1칸 확산
 *   4. 새로 감염된 위치 제거
 *   → 집합이 비면 ZOMBIE, N*M 턴 후에도 살아있으면 CANNOT ESCAPE
 */
public class IndeEscape {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            boolean[][] wall     = new boolean[N][M];
            boolean[][] virus    = new boolean[N][M];
            boolean[][] reachable = new boolean[N][M]; // 삼성이가 있을 수 있는 칸

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < M; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    if (v == 1) wall[i][j]      = true;
                    else if (v == 2) virus[i][j] = true;
                    else if (v == 3) reachable[i][j] = true;
                }
            }

            sb.append('#').append(tc).append(' ')
              .append(solve(wall, virus, reachable)).append('\n');
        }
        System.out.print(sb);
    }

    static String solve(boolean[][] wall, boolean[][] virus, boolean[][] reachable) {
        // 바이러스가 최대 N*M 칸을 채우는 데 N*M 턴이면 충분
        for (int time = 0; time <= N * M; time++) {

            // ① 탈출 체크: reachable 중 맵 밖으로 나갈 수 있는 칸이 있으면 탈출
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (!reachable[r][c]) continue;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d], nc = c + dc[d];
                        if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                            return String.valueOf(time + 1);
                        }
                    }
                }
            }

            // ② 삼성이 확장: 현재 위치에서 이동 or 대기
            boolean[][] next = new boolean[N][M];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (!reachable[r][c]) continue;
                    next[r][c] = true; // 제자리 대기
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d], nc = c + dc[d];
                        if (nr >= 0 && nr < N && nc >= 0 && nc < M
                                && !wall[nr][nc] && !virus[nr][nc]) {
                            next[nr][nc] = true;
                        }
                    }
                }
            }

            // ③ 바이러스 1칸 확산 (이전 상태 기준으로만 확산)
            boolean[][] newVirus = new boolean[N][M];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    newVirus[r][c] = virus[r][c];
                    if (!virus[r][c]) continue;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d], nc = c + dc[d];
                        if (nr >= 0 && nr < N && nc >= 0 && nc < M && !wall[nr][nc]) {
                            newVirus[nr][nc] = true;
                        }
                    }
                }
            }
            virus = newVirus;

            // ④ 새로 감염된 위치 제거
            boolean hasAny = false;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (next[r][c] && virus[r][c]) next[r][c] = false;
                    if (next[r][c]) hasAny = true;
                }
            }

            if (!hasAny) return "ZOMBIE";
            reachable = next;
        }
        return "CANNOT ESCAPE";
    }
}
