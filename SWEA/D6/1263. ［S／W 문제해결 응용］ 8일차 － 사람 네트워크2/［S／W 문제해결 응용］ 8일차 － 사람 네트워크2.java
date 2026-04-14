import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 1263. 사람 네트워크 2
 * 
 * @author neogul02
 *         - 그래프를 초기화 하고 각 노드에서 bfs를 진행해서 가장 작은 가중치를 출력
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
            bfs();
        }
        System.out.print(sb);
    }

    public static void bfs() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int min = INF;

        for (int i = 0; i < N; i++) {
            queue.offer(new int[] { i, 0 }); // 각 노드에서 시작해서 가중치 0으로 시작
            boolean[] visited = new boolean[N]; // 방문처리 배열도 각 노드마다 초기화
            visited[i] = true;
            int sum = 0;

            while (!queue.isEmpty()) {
                int[] temp = queue.poll();
                int node = temp[0];
                int weight = temp[1];

                sum += weight;

                for (int j = 0; j < N; j++) {
                    if (!visited[j] && graph[node][j] != INF) {
                        visited[j] = true;
                        queue.offer(new int[] { j, graph[node][j] + weight });
                    }
                }
            }
            min = Math.min(min, sum);
        }
        sb.append(min).append("\n");
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());

                if (i == j) {
                    graph[i][j] = 0;
                } else if (temp == 1) {
                    graph[i][j] = 1;
                } else {
                    graph[i][j] = INF;
                }
            }
        }

    }
}
