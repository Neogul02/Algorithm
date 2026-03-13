package MST;
import java.io.*;
import java.util.*;

/**
 * Prim (PriorityQueue/Heap) - Adjacency List Version
 *
 * 핵심 아이디어:
 * - MST에 포함된 정점 집합(visited)을 유지한다.
 * - "MST 바깥으로 나가는 간선들" 중 가장 가중치가 작은 간선을 매번 선택한다.
 * - 그리디 선택을 빠르게 하기 위해 최소 힙(PriorityQueue)을 사용한다.
 *
 * 시간복잡도(일반적으로):
 * - O(E log E) 또는 O(E log V)로 설명 (구현 디테일에 따라 표현이 달라짐)
 * - 실전에서는 V^2 버전보다 훨씬 빠름 (희소 그래프에서 특히)
 */
public class PrimPQAdjList {

    // 인접 리스트 노드: (to, weight) + next(연결 리스트)
    static class Node {
        int to;
        int weight;
        Node next;

        Node(int to, int weight, Node next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }
    }

    // PQ(힙)에 넣을 항목: "어떤 정점으로 들어가는 간선의 비용"
    // (정점 v를 MST에 추가할 때 드는 비용 w)
    static class EdgeTo {
        int v;
        int w;

        EdgeTo(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력: V E (정점수, 간선수)
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        Node[] adj = new Node[V];

        // 간선 입력: from to weight
        // 무방향 그래프라 양쪽에 다 추가
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // 만약 입력이 1..V 라면 아래 두 줄을 켜야 함
            // from--; to--;

            adj[from] = new Node(to, w, adj[from]);
            adj[to] = new Node(from, w, adj[to]);
        }

        long mstCost = prim(V, adj);
        System.out.println(mstCost);
    }

    /**
     * Prim (PriorityQueue)
     *
     * visited[v] = true  -> v가 MST에 포함됨
     * PQ에는 "MST에 아직 포함되지 않은 정점으로 들어갈 후보 간선들"이 들어있다.
     */
    static long prim(int V, Node[] adj) {
        boolean[] visited = new boolean[V];

        // 가중치 기준 정렬의 최소 힙
        PriorityQueue<EdgeTo> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));

        // 1) 시작 정점을 0으로 잡고, 비용 0으로 PQ에 넣는다.
        //    -> 첫 pop에서 정점 0이 선택되고, MST 시작점이 된다.
        pq.add(new EdgeTo(0, 0));

        long result = 0;
        int picked = 0; // MST에 포함된 정점 수

        // 2) PQ에서 가장 싼 후보부터 꺼내면서 MST를 확장한다.
        while (!pq.isEmpty() && picked < V) {
            EdgeTo cur = pq.poll();

            int v = cur.v;
            int w = cur.w;

            // (중요) 이미 MST에 들어간 정점이면 이 항목은 폐기
            // 이유: PQ에는 "예전에 넣어둔 더 비싼 후보"가 남아 있을 수 있음 (lazy deletion)
            if (visited[v]) continue;

            // 3) v를 MST에 편입
            visited[v] = true;
            result += w;
            picked++;

            // 4) v에서 나가는 모든 간선을 확인해서,
            //    아직 MST에 없는 정점으로 가는 간선이면 PQ에 후보로 추가
            for (Node e = adj[v]; e != null; e = e.next) {
                if (!visited[e.to]) {
                    pq.add(new EdgeTo(e.to, e.weight));
                }
            }
        }

        // 연결 그래프가 아니면 MST가 완성되지 않음
        if (picked != V) {
            throw new IllegalStateException("Graph is disconnected (MST cannot be formed).");
        }

        return result;
    }
}