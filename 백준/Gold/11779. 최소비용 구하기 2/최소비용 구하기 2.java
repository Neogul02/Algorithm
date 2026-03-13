import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 11779. 최소비용 구하기 2
 * @author neogul02
 * 
 * - N개의 도시를 연결하는 M개의 버스가 있을때, A->B 도시로 가는 최소비용과 경로를 출력
 * - Dijkstra인데 dist배열이랑 prev배열을 따로 만들어서 해당 노드가 업데이트 된다? 최단경로의 부모는 그때 탐색한 노드
 * - dist배열은 최단거리, prev배열은 최단경로의 부모노드
 * - 경로는 prev배열을 이용해서 역추적
 *
 * ! 경로가 여러개인 경우에는 아무거나 출력
 */

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int minCost;
    static int N, M;
    static int startCity, endCity; // 출발 도시, 도착 도시

    static int[] dist;
    static int[] prev; // parents arr
    static ArrayList<Edge>[] graph;
    
    static final int INF = Integer.MAX_VALUE;

    public static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    
    public static void main(String[] args) throws IOException {
        // input
        input();

        // solve
        dijkstra();

        // output
        System.out.print(sb);
    }

    public static void dijkstra() {
        // 1. dist, prev Init
        dist = new int[N + 1];
        prev = new int[N + 1];
        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1); // 부모노드 초기값 -1

        // 2. Dijkstra
        dist[startCity] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((A, B) -> A.cost - B.cost); // min-heap
        pq.add(new Edge(startCity, 0));

        while (pq.isEmpty() != true) {
            // 1. minDist poll in min-heap pq
            Edge temp = pq.poll();
            int tempCity = temp.to;
            int tempCost = temp.cost;

            // 2. dist[tempCity] < tempCost then pass
            if (dist[tempCity] < tempCost)
                continue;

            // 3. for each edge in graph[tempCity]
            for (Edge next : graph[tempCity]) {
                // 해당 노드로 가는 비용
                int newCost = tempCost + next.cost;

                // update dist[next.to] if newCost < dist[next.to]
                if (newCost < dist[next.to]) {
                    dist[next.to] = newCost;

                    // update prev[next.to] = tempCity
                    prev[next.to] = tempCity;

                    pq.add(new Edge(next.to, newCost));
                }
            }
        }
        
        // 3. path tracking
        // Line 1: minCost
        sb.append(dist[endCity]).append("\n");
        
        ArrayDeque<Integer> path = new ArrayDeque<>();
        int currentCity = endCity;

        while (currentCity != -1) {
            path.add(currentCity);
            currentCity = prev[currentCity]; // 부모노드로 이동
        }
        
        // Line 2: city count in path
        sb.append(path.size()).append("\n"); // 경로에 포함된 도시의 개수

        // Line 3: path cities
        while (path.isEmpty() != true) {
            sb.append(path.pollLast()).append(" ");
        }
 
    }
    
    public static void input() throws IOException {
        // Line 1 : N 
        N = Integer.parseInt(br.readLine().trim());

        // Line 2 : M 
        M = Integer.parseInt(br.readLine().trim());

        // graph Init
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // Line 3 ~ M+2 : (from, to, cost)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, cost));
        }

        // Line M+3 : 출발 도시, 도착 도시
        st = new StringTokenizer(br.readLine().trim());
        startCity = Integer.parseInt(st.nextToken());
        endCity = Integer.parseInt(st.nextToken());
    }
}