import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, C;
    static int[][] map;
    static int[][] profitMap; // [전처리] 각 위치별 최대 수익을 저장할 배열

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            input();
            
            // 1. [전처리] 모든 위치에 대해 슬라이딩하며 최대 수익 미리 계산
            makeProfitMap(); 
            
            // 2. [조합] 겹치지 않는 두 값을 골라 최대합 구하기
            int result = solve();

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        profitMap = new int[N][N]; // 이익 저장소 초기화

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    // 슬라이딩 윈도우처럼 이동하며 각 위치의 '최대 수익' 저장
    private static void makeProfitMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N - M; j++) {
                // (i, j)에서 시작하는 벌통 M개의 부분집합 최대 이익 계산 후 저장
                profitMap[i][j] = getMaxHoneyProfit(i, j, 0, 0, 0);
            }
        }
    }

    // profitMap에서 겹치지 않는 두 개의 최대값 선택
    private static int solve() {
        int max = 0;

        // 첫 번째 일꾼의 위치 (i, j)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N - M; j++) {
                
                // 두 번째 일꾼의 위치 (r, c)
                // 일꾼 1의 다음 위치부터 탐색 시작 (중복 방지 & 가지치기)
                for (int r = i; r < N; r++) {
                    // 같은 행이면 겹치지 않게 j + M 부터 시작, 다른 행이면 0부터
                    int startCol = (r == i) ? j + M : 0;
                    
                    for (int c = startCol; c <= N - M; c++) {
                        // 단순히 미리 구해둔 값을 더하기만 하면 됨
                        max = Math.max(max, profitMap[i][j] + profitMap[r][c]);
                    }
                }
            }
        }
        return max;
    }

    private static int getMaxHoneyProfit(int r, int c, int idx, int sum, int profit) {
        if (sum > C) return 0;
        if (idx == M) return profit;

        int honey = map[r][c + idx];
        
        // 안 담는 경우
        int case1 = getMaxHoneyProfit(r, c, idx + 1, sum, profit);
        
        // 담는 경우
        int case2 = 0;
        if (sum + honey <= C) {
            case2 = getMaxHoneyProfit(r, c, idx + 1, sum + honey, profit + (honey * honey));
        }

        return Math.max(case1, case2);
    }
}