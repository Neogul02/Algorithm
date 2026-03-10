import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1247. 최적 경로
 * @author neogul02
 * 
 * - N명의 고객을 방문하고 집에 간다
 * - 회사좌표, 집 좌표 st.nextToken 앞에 4개를 빼고 그 다음부터 N*2 토큰이 고객의 좌표
 * 
 * - 회사에서 고객들 방문하는 순서의 모든 경우의 수를 구해서
 * - 각 경우의 수마다 회사에서 첫 번째 고객까지의 거리 + 고객들 사이의 거리 + 마지막 고객에서 집까지의 거리를 계산해서
 * - 그 중에서 최소 거리를 구하면 될듯
 * 
 * 회사 => N1 => N2 => N => 집
 * 
 * - 좌표 평면상 두 위치 사이의 거리는 멘헤튼 거리로다가 계산해서 최소 거리를 계산
 * - |x1-x2| + |y1-y2|
 * 
 * - 고객의 수가 2<= N <= 10 이므로 10! => 완탐 조합해서 탈출하면 충분할듯
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N; // 고객의 수
    static int[][] customers; // 좌표 저장

    static int startX = 0; // 집의 x좌표
    static int startY = 0; // 집의 y좌표

    static int companyX = 0; // 회사의 x좌표
    static int companyY = 0; // 회사의 y좌표

    static int[] visited; // 방문 여부 체크하는 배열
    static boolean[] isSelected; // 선택 여부 체크하는 배열
    static int minDistance; // 최소 거리    

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            customers = new int[N][2];

            st = new StringTokenizer(br.readLine().trim(), " ");

            companyX = Integer.parseInt(st.nextToken());
            companyY = Integer.parseInt(st.nextToken());

            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            
            for (int i = 0; i < N; i++) {
                customers[i][0] = Integer.parseInt(st.nextToken());
                customers[i][1] = Integer.parseInt(st.nextToken());
            }

            visited = new int[N];
            isSelected = new boolean[N];
            minDistance = Integer.MAX_VALUE;

            permutation(0);

            sb.append('#').append(tc).append(' ')
                .append(minDistance).append('\n');

        }
        
        System.out.print(sb);
    }

    public static void permutation(int depth) {
        // 기저조건 => 노드의 갯수와 방문한 노드의 갯수가 같을 때
        if (depth == N) {
            int tempDistance = 0;

            // 회사에서 출발 => 첫 번째 고객까지의 거리
            tempDistance += manhattanDistance(companyX, companyY, customers[visited[0]][0], customers[visited[0]][1]);

            // N1~Nn 고객들 사이의 거리
            for (int i = 0; i < N - 1; i++) {
                tempDistance += manhattanDistance(customers[visited[i]][0], customers[visited[i]][1],
                        customers[visited[i + 1]][0], customers[visited[i + 1]][1]);
            }

            // 마지막 N에서 집까지의 거리
            tempDistance += manhattanDistance(customers[visited[N - 1]][0], customers[visited[N - 1]][1], startX,
                    startY);

            minDistance = Math.min(minDistance, tempDistance);
            return;
        }
        
        // 수행 로직 => 
        for (int i = 0; i < N; i++) {
            if (isSelected[i] == true) continue;
            
            isSelected[i] = true;
            visited[depth] = i;

            permutation(depth + 1);

            isSelected[i] = false; // 백트래킹
        }
    }

    static int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
}