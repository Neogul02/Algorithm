import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 3124. 최소 스패닝 트리
 * @author neogul02
 * 
 * - 그래프의 모든 정점을 연결하는 부분 그래프 중
 * - 간선의 가중치 합이 최소인 트리를 구하자
 * - Prim 알고리즘 -> PriorityQueue(최소 힙)로 풀이
 * 
 * 1. parents 배열 초기화
 * 2. @see set() 간선 리스트 초기화 
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int V, E; // 정점의 수, 간선의 수

    // 간선 리스트
    public static class Node {
        int to;
        int weight;
        Node next;

        Node(int to, int weight, Node next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }
    }

    public static class EdgeTo {
        int v;
        int w;

        EdgeTo(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static Node[] adj; // 인접 리스트
    static EdgeTo[] edges; // 간선 리스트
    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            input();
            solve(tc);
        }
        System.out.print(sb);
    }
    
    public static void solve(int tc) {
        long result = prim(V, adj);

        sb.append('#').append(tc).append(' ')
                .append(result).append('\n');
    }
    
    public static long prim(int V, Node[] adj) {
        boolean[] visited = new boolean[V + 1]; // 1-based 인덱스

        // 가중치 기준 정렬의 최소 힙
        PriorityQueue<EdgeTo> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));

        // 1) 시작 정점을 1로 잡고, 비용 0으로 PQ에 넣는다.
        //    -> 첫 pop에서 정점 1이 선택되고, MST 시작점이 된다.
        pq.add(new EdgeTo(1, 0));

        long mstCost = 0; // MST 비용 누적

        while (!pq.isEmpty()) {
            EdgeTo edge = pq.poll();
            int v = edge.v;
            int w = edge.w;

            if (visited[v]) continue; // 이미 MST에 포함된 정점이면 스킵
            visited[v] = true; // MST에 추가
            mstCost += w; // 비용 누적

            // v에서 갈 수 있는 간선들을 PQ에 추가
            for (Node e = adj[v]; e != null; e = e.next) {
                if (!visited[e.to]) {
                    pq.add(new EdgeTo(e.to, e.weight)); // 간선의 실제 가중치 사용!
                }
            }
        }

        return mstCost;
    }
        
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); // 정점의 수
        E = Integer.parseInt(st.nextToken()); // 간선의 수

        adj = new Node[V + 1]; // 1-based 인덱스를 위해 V+1 크기로

        for (int i = 0; i < E; i++) { // 간선 수 만큼 반복
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 무방향 그래프라 양쪽에 다 추가
            adj[from] = new Node(to, weight, adj[from]);
            adj[to] = new Node(from, weight, adj[to]);

        }

    }
}