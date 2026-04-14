import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 5643. 키 순서
 * 
 * @author neogul02
 * 
 *         문제풀이 플로우
 *         0. 플로이드 워셜 알고리즘이란 모든 노드에 대해 최단경로를 구하는 알고리즘이다.
 *         0. 이 문제에서는 가중치가 있다고 생각하고 풀이하는데 가중치가 중요하다기보단
 *         0. 모든 노드와 연결되어있는지 확인하는가? true false 라고 생각하고 풀이하는것같다.
 *         0. N은 최대 500이고 시간제한이 10초니까.. N^3하면.. 1억2500만
 * 
 *         1. 입력받은 숫자로 그래프를 작은방향 a에서 큰방향 b로 그래프를 만든다.
 *         2. 플로이드 워셜 알고리즘을 통해 모든 노드와 연결되어있는지 확인한다.
 *         3. 특정노드 i 입장에서 나보다 큰 사람의 수와, 나보다 작은 사람의 수를 센다.
 *         3-1. 큰 사람수랑 작은 사람수를 더한 값이 그래프 전체 노드갯수 -1이면 특정노드 i는 순서를 알 수 있는 학생이 된다.
 * 
 */
class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[][] graph;
    final static int INF = Integer.MAX_VALUE;

    static int N, M;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            input();
            solve();
        }
        System.out.print(sb);
    }

    public static void solve() {

        // 2. 플로이드 워셜 알고리즘을 통해 모든 노드와 연결되어있는지 확인한다.
        for (int k = 1; k <= N; k++) { // k는 경로 노드
            for (int i = 1; i <= N; i++) { // i는 출발 노드
                for (int j = 1; j <= N; j++) { // j는 도착 노드

                    // i에서 k로 가고, k에서 j로 갈 수 있다면 i에서 j로 갈 수 있다
                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;
                    }
                }
            }
        }

        // 3. 키 순서를 정확히 아는 노드 개수 세기
        int count = 0;
        for (int i = 1; i <= N; i++) {
            int tallerCount = 0; // 나보다 큰 사람의 수
            int shorterCount = 0; // 나보다 작은 사람의 수

            for (int j = 1; j <= N; j++) {
                if (graph[i][j] == 1)
                    tallerCount++; // i보다 j가 크다.
                if (graph[j][i] == 1)
                    shorterCount++; // j보다 i가 크다.
            }

            // 3-1. 큰 사람수랑 작은 사람수를 더한 값이 그래프 전체 노드갯수 -1이면 특정노드 i는 순서를 알 수 있는 학생이 된다.
            if (tallerCount + shorterCount == N - 1) {
                count++;
            }
        }
        sb.append(count).append("\n");

    }

    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());

        graph = new int[N + 1][N + 1];

        // 그래프 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = INF;
                }
            }
        }

        // 간선 정보 입력
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = 1; // a가 b보다 키가 작다.
        }
    }
}