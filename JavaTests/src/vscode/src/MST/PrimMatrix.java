package MST;
import java.io.*;
import java.util.*;

public class PrimMatrix {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine().trim()); // 정점 수
        int[][] adjMatrix = new int[V][V];
        boolean[] visited = new boolean[V];
        int[] minEdge = new int[V];

        // 인접 행렬 입력
        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < V; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[0] = 0; // 시작 정점

        long result = 0;
        for (int c = 0; c < V; c++) {
            int min = Integer.MAX_VALUE;
            int minVertex = -1;

            // step1: 선택
            for (int i = 0; i < V; i++) {
                if (!visited[i] && min > minEdge[i]) {
                    min = minEdge[i];
                    minVertex = i;
                }
            }

            // 연결 그래프가 아니면 MST 불가(또는 MSF 필요)
            if (minVertex == -1) {
                System.out.println("Graph is disconnected");
                return;
            }

            visited[minVertex] = true;
            result += min;

            // step2: 갱신
            for (int i = 0; i < V; i++) {
                int w = adjMatrix[minVertex][i];
                if (!visited[i] && w != 0 && minEdge[i] > w) {
                    minEdge[i] = w;
                }
            }
        }

        System.out.println(result);
    }
}