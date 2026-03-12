
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1916. 최소비용 구하기
 * @author neogul02
 * 
 * - N개의 도시에 M개의 출발 - 도착하는 버스가 있음
 * - 도시 번호는 1부터 N까지
 * - Dijkstra로 A노드에서 B노드까지의 최단 경로 구하기
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int cityA, cityB;

    static ArrayList<Edge>[] graph;
    static int dist[]; 
    static final int INF = Integer.MAX_VALUE;

    static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        // 1. Init input
        input();

        // 2. Dijkstra Algorithm - cityA to cityB
        dijkstra();

        // 3. print result
        System.out.print(dist[cityB]);
    }

    public static void dijkstra() {

        // Init dist array
        dist = new int[N + 1]; // 1-based index
        for (int i = 1; i < N + 1; i++) {
            dist[i] = INF;
        }

        // Init start Edge, start dist = 0
        int start = cityA;
        dist[start] = 0;
        
        // Min-Heap (Comparator = (a, b) -> a.cost - b.cost)
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        
        // pa.add(new Edge(city, cost));
        pq.add(new Edge(start, 0));
        
        while (pq.isEmpty() != true) {
            Edge temp = pq.poll();
            int tempCity = temp.to; 
            int tempCost = temp.cost;

            // lazy deletion = 
            if (tempCost > dist[tempCity]) continue; 
            
            // graph[tempCity] = [Edge(to, cost), Edge(to, cost), ...]
            for (Edge next : graph[tempCity]) {
                // 
                int newCost = dist[tempCity] + next.cost;

                if (newCost < dist[next.to]) {
                    dist[next.to] = newCost;
                    pq.add(new Edge(next.to, newCost));
                }
            }
        }
        
    }

    public static void input() throws IOException {
        // Line 1: 도시 개수 N
        N = Integer.parseInt(br.readLine());

        // Line 2: 버스 개수 M
        M = Integer.parseInt(br.readLine());

        // Init graph
        graph = new ArrayList[N + 1]; // 1-based
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // Line 3 ~ M+2: 버스 정보 (출발 도시, 도착 도시, 비용)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            int from = Integer.parseInt(st.nextToken()); // 출발지
            int to = Integer.parseInt(st.nextToken()); // 도착지
            int cost = Integer.parseInt(st.nextToken()); // 버스 비용

            // graph Init
            graph[from].add(new Edge(to, cost));
        }

        // Line M+3: 출발 도시 A, 도착 도시 B
        st = new StringTokenizer(br.readLine().trim(), " ");
        cityA = Integer.parseInt(st.nextToken()); // 출발 도시
        cityB = Integer.parseInt(st.nextToken()); // 도착 도시
    }
}
