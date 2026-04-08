import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1. N, 회사(0), 집(1), 고객(2~N+1) 좌표를 입력받아 저장한다.
 * 2. dp[mask][cur] 배열을 초기화한다. (mask: 방문 상태, cur: 현재 위치한 고객 인덱스)
 * 3. 초기 상태: 회사에서 각 고객으로 직접 가는 거리를 dp[1 << i][i]에 저장한다.
 * 4. 모든 mask를 순회하며, 현재 고객(last)에서 다음 고객(next)으로 가는 최단 거리를 갱신한다.
 * 5. 모든 고객을 방문한 상태(fullMask)에서 마지막 고객 위치와 집 사이의 거리를 더해 최솟값을 구한다.
 */
public class Solution {
    static int N, fullMask;
    static int[][] pos; // 0: 회사, 1: 집, 2~N+1: 고객
    static int[][] dp;
    static final int INF = 1000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            fullMask = (1 << N) - 1;
            pos = new int[N + 2][2];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 회사(0), 집(1) 좌표 저장
            for (int i = 0; i < 2; i++) {
                pos[i][0] = Integer.parseInt(st.nextToken());
                pos[i][1] = Integer.parseInt(st.nextToken());
            }
            // 고객(2 ~ N+1) 좌표 저장
            for (int i = 2; i < N + 2; i++) {
                pos[i][0] = Integer.parseInt(st.nextToken());
                pos[i][1] = Integer.parseInt(st.nextToken());
            }

            sb.append("#").append(tc).append(" ").append(solve()).append("\n");
        }
        System.out.print(sb);
    }

    static int solve() {
        dp = new int[1 << N][N];
        for (int i = 0; i < (1 << N); i++) Arrays.fill(dp[i], INF);

        // 1. 초기값: 회사 -> 각 고객
        for (int i = 0; i < N; i++) {
            dp[1 << i][i] = getDist(0, i + 2);
        }

        // 2. DP 전개: 작은 문제(적은 방문)에서 큰 문제(많은 방문)로
        for (int mask = 1; mask <= fullMask; mask++) {
            for (int last = 0; last < N; last++) {
                if ((mask & (1 << last)) == 0) continue; // last 고객을 방문하지 않은 상태면 패스
                if (dp[mask][last] == INF) continue;

                for (int next = 0; next < N; next++) {
                    if ((mask & (1 << next)) != 0) continue; // 이미 방문한 고객이면 패스

                    int nextMask = mask | (1 << next);
                    int dist = dp[mask][last] + getDist(last + 2, next + 2);
                    
                    if (dp[nextMask][next] > dist) {
                        dp[nextMask][next] = dist;
                    }
                }
            }
        }

        // 3. 최종 결과: 마지막 고객 -> 집
        int minResult = INF;
        for (int i = 0; i < N; i++) {
            minResult = Math.min(minResult, dp[fullMask][i] + getDist(i + 2, 1));
        }

        return minResult;
    }

    static int getDist(int p1, int p2) {
        return Math.abs(pos[p1][0] - pos[p2][0]) + Math.abs(pos[p1][1] - pos[p2][1]);
    }
}