import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1263. 사람 네트워크 2
 * 
 * @author neogul02
 *
 *         - 간선의 가중치를 1로 생각하고 플로이드 워셜 알고리즘으로 각 노드마다 가중치를 계산함.
 *         - 가장 작은 가중치를 가진 노드가 가장 가까운 사람이 되고
 *         - 가장 작은 가중치를 출력함
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;

    static int[][] graph;
    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            input();
            floydWarshall();
        }
        System.out.print(sb);
    }

    public static void floydWarshall() {
        // 경출도
        for (int k = 0; k < N; k++) { // k는 경로 노드
            for (int i = 0; i < N; i++) { // i는 출발 노드
                for (int j = 0; j < N; j++) { // j는 도착 노드

                    // i에서 k로 가고, k에서 j로 갈 수 있다면 i에서 j로 갈 수 있다
                    if (graph[i][k] != INF && graph[k][j] != INF) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }

        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += graph[i][j];
            }
            minSum = Math.min(minSum, sum);
        }

        sb.append(minSum).append("\n");
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());

        graph = new int[N][N];

        // row-by-row 인접행렬
        for (int i = 0; i < N; i++) {
            graph[i] = new int[N];
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (i == j) {
                    graph[i][j] = 0; // 자기 자신으로의 거리는 0
                } else if (value == 1) {
                    graph[i][j] = 1; // 연결된 간선의 가중치는 1
                } else {
                    graph[i][j] = INF; // 연결되지 않은 간선은 INF
                }
            }
        }

    }
}
