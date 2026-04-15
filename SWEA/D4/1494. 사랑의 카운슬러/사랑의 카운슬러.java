import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1494. 사랑의 카운슬러
 * @author neogul02
 * 
 *         - │V│=│(x, y)│= x * x + y * y
 *         - N은 짝수, 2부터 20까지 주어짐
 *         - 점 A(x1, y1) 에서 점 B로 이동하는 벡터는 (x2-x1, y2-y1)
 *         - 이 벡터의 크기는 │(x2-x1, y2-y1)│= (x2-x1) * (x2-x1) + (y2-y1) * (y2-y1)
 *         - 조합으로 브루트포스하면 될거같음
 * 
 * 1. 테스트케이스만큼 반복
 * 
 * @see input()
 *      1. N 입력받기
 *      2. N개의 점 좌표 입력받기
 * 
 * @see comb(int depth, int start) // 조합 dfs
 *      1. 기저조건 : N/2개를 선택했을 때 종료
 *      1-1. 선택된 점들의 x좌표 합과 y좌표 합 계산
 *      1-2. 벡터 크기의 최솟값 갱신
 *      2. 수행 로직 : 조합 visited 배열로 선택한 경우, 선택하지 않은 경우로 나누어서 재귀
 *      2-1. i부터 탐색하여 중복 없는 조합
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int[][] points;
    static boolean[] visited;
    static long result;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            input();
            comb(0, 0);
            sb.append("#").append(tc).append(" ")
                    .append(result).append("\n");
        }
        System.out.print(sb);

    }

    public static void comb(int depth, int start) {
        // 기저조건 : N/2개를 선택했을 때
        if (depth == N / 2) {
            long totalX = 0;
            long totalY = 0;

            for (int i = 0; i < N; i++) {
                if (visited[i] == true) { // 선택된 점 (도착점)
                    totalX += points[i][0];
                    totalY += points[i][1];
                } else { // 선택되지 않은 점 (출발점)
                    totalX -= points[i][0];
                    totalY -= points[i][1];
                }
            }

            // 벡터 크기의 최솟값 갱신 (|V| = x^2 + y^2)
            long res = totalX * totalX + totalY * totalY;
            result = Math.min(result, res);
            return;
        }

        // 2. 수행 로직: i부터 탐색하여 중복 없이 조합 생성
        for (int i = start; i < N; i++) {
            visited[i] = true; // 점 선택
            comb(depth + 1, i + 1); // 다음 점 선택을 위해 재귀 호출
            visited[i] = false; // 점 선택 해제 (백트래킹)
        }
    }

    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        points = new int[N][2];
        result = Long.MAX_VALUE;
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }
    }

}
