import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1753. 최단경로
 * @author neogul02
 * 
 * - 방향그래프에의 시작점 -> 다른 모든 정점의 최단경로 -> Dijkstra
 * - V: 정점의 개수, E: 간선의 개수
 * 
 * - 간선이 최대 30만개, 간선 가중치 최대가 10 이하니까 300만 = int범위로 가능 -21억< int <21억
 * 
 */
public class Main {
    // 간선 정보 저장 클래스
    public static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int V, E; // 정점의 개수 V, 간선의 개수 E
    static int start; // 시작 정점 번호
    static ArrayList<Edge>[] graph;
    static int[] dist;

    static final int INF = Integer.MAX_VALUE;



    public static void main(String[] args) throws IOException {
        input();
        dijkstra(start);

        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) {
                sb.append("INF\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static void dijkstra(int start) {
        // start 정점부터 탐색 시작

        // 1. Init all dist -> INF
        dist = new int[V+1]; // 1-based indexing
        for (int i = 0; i < V+1; i++) {
            dist[i] = INF;
        }

        // 2. startVertex dist = 0
        dist[start] = 0; 

        // 3. Init Priority Queue (Min heap - Edge.weight)
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(start, 0));

        // 4. loop - queue.isEmpty != true 
        while (pq.isEmpty() != true) {
            Edge temp = pq.poll();
            int now = temp.to; // 방향
            int nowDist = temp.weight; // 가중치

            // 5. 이미 최단거리? 
            if (nowDist > dist[now]) continue;

            // 6. 인접 정점 탐색
            for (Edge next : graph[now]) {
                int newDist = dist[now] + next.weight; // 거리계산

                // 7. 더 짧은 경로를 발견하면 dist[next.to]의 거리 갱신
                if (newDist < dist[next.to]) {
                    dist[next.to] = newDist;
                    pq.add(new Edge(next.to, newDist));
                }   
            }   
        }      
    }
    @SuppressWarnings("unchecked")
    public static void input() throws IOException {
        // Line 1 - 정점 V, 간선 E
        st= new StringTokenizer(br.readLine().trim()," ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // Line 2 - start Vertex number
        start = Integer.parseInt(br.readLine().trim());

        // init graph
        graph = new ArrayList[V+1]; // 1~V, 1-based indexing
        for (int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        // Line 3 ~ E (간선 개수만큼 읽기)
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // graph[from].add(new Edge(to, weight))
            graph[from].add(new Edge(to, weight));
        }
    }

}