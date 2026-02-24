import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2805. 농작물 수확하기
 * @author neogul02
 * 
 * 1. 배열 크기 의 절반 mid 를 구한다. 
 * 구해야하는 규칙 => 마름모 형태..? 피라미드 구하듯이 
 *               [0][2]
 *        [1][1] [1][2] [1][3]
 * [2][0] [2][1] [2][2] [2][3] [2][4] // [2][2] 를 중심으로 각 거리를 계산
 *        [3][1] [3][2] [3][3] 
 *               [4][2]
 * - 각 행 i에 대해, 행마다 마름모의 중심에서의 거리를 계산하여 시작 열과 끝 열을 결정한다.
 * - 중심에서의 거리가 d인 경우, 시작 열은 d, 끝 열은 N-1-d가 된다.
 * - 반복해서 map[i][j]의 값을 더해주면 끝
 * 
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int[][] map;
    static int N;

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
    // 마름모 영역 합 계산
    public static int solve() {
        int mid = N / 2; // 마름모의 중심 행
        int sum = 0;

        for (int i = 0; i < N; i++) {
            int d = Math.abs(mid - i); // 중심에서의 거리
            int start = d; // 시작 열
            int end = N - 1 - d; // 끝 열

            for (int j = start; j <= end; j++) {
                sum += map[i][j];
            }
        }
        return sum;
    }
    
    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String temp = br.readLine().trim();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp.charAt(j) - '0';
            }
        }
    }
}