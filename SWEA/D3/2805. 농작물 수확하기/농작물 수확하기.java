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
 * - 원이라고 생각하면. 멘헤튼거리로 반지름보다 현 위치가 작으면 마름모라고 생각
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
        // 마름모의 중심 좌표 구하기
        int cx = N / 2; // 중심 행
        int cy = N / 2; // 중심 열

        int r = N / 2; // 반지름 길이

        int sum = 0;
        // 모든 배열의 칸을 돌면서, 중심점에서 부터 현재 위치까지 거리가 반지름보다 작으면? -> 마름모 영역
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int distance = Math.abs(i - cx) + Math.abs(j - cy);
                if (distance <= r) {
                    sum += map[i][j];
                    // System.out.printf("거리-> %f , [%d][%d] = %d\n",distance,i,j,map[i][j]);
                }
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