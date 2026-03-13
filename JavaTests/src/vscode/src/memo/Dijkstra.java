import java.util.*;

public class Dijkstra {

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int V;
    static List<Edge>[] graph;
    static int[] dist;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        V = 5;

        graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(1, 2));
        graph[0].add(new Edge(2, 5));
        graph[1].add(new Edge(2, 2));
        graph[1].add(new Edge(3, 1));
        graph[2].add(new Edge(4, 3));
        graph[3].add(new Edge(4, 2));

        int start = 0;
        dijkstra(start);

        for (int i = 0; i < V; i++) {
            if (dist[i] == INF) {
                System.out.println(start + " -> " + i + " : 도달 불가");
            } else {
                System.out.println(start + " -> " + i + " : " + dist[i]);
            }
        }
    }

    static void dijkstra(int start) {

        dist = new int[V];
        for (int i = 0; i < V; i++) {
            dist[i] = INF;
        }

        dist[start] = 0;

        // ✅ Comparator를 외부에서 람다로 선언
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(start, 0));

        // 시뮬레이션
        // 초기 상태
        // dist  = [ 0, INF, INF, INF, INF ]
        // pq    = [ (to=0, w=0) ]
        //
        // ── while 1회 ──────────────────────────────
        // poll  → tempVertex(to=0, w=0)
        // now=0, nowDist=0
        // nowDist(0) > dist[0](0) ? NO → 진행
        //
        // 인접 정점 탐색 graph[0] = [(1,2), (2,5)]
        //   next(1,2) : newDist = dist[0](0) + 2 = 2  <  dist[1](INF) → 갱신! dist[1]=2, pq에 (to=1, w=2) 추가
        //   next(2,5) : newDist = dist[0](0) + 5 = 5  <  dist[2](INF) → 갱신! dist[2]=5, pq에 (to=2, w=5) 추가
        //
        // dist  = [ 0,   2, 5,   INF, INF ]
        // pq    = [ (to=1, w=2), (to=2, w=5) ]
        //
        // ── while 2회 ──────────────────────────────
        // poll  → tempVertex(to=1, w=2)
        // now=1, nowDist=2
        // nowDist(2) > dist[1](2) ? NO → 진행
        //
        // 인접 정점 탐색 graph[1] = [(2,2), (3,1)]
        //   next(2,2) : newDist = dist[1](2) + 2 = 4  <  dist[2](5)   → 갱신! dist[2]=4, pq에 (to=2, w=4) 추가
        //   next(3,1) : newDist = dist[1](2) + 1 = 3  <  dist[3](INF) → 갱신! dist[3]=3, pq에 (to=3, w=3) 추가
        //
        // dist  = [ 0, 2, 4, 3,   INF ]
        // pq    = [ (to=2, w=4), (to=3, w=3), (to=2, w=5) ]
        //
        // ── while 3회 ──────────────────────────────
        // poll  → tempVertex(to=3, w=3)   ← w가 가장 작은것 먼저!
        // now=3, nowDist=3
        // nowDist(3) > dist[3](3) ? NO → 진행
        //
        // 인접 정점 탐색 graph[3] = [(4,2)]
        //   next(4,2) : newDist = dist[3](3) + 2 = 5  <  dist[4](INF) → 갱신! dist[4]=5, pq에 (to=4, w=5) 추가
        //
        // dist  = [ 0, 2, 4, 3, 5 ]
        // pq    = [ (to=2, w=4), (to=2, w=5), (to=4, w=5) ]
        //
        // ── while 4회 ──────────────────────────────
        // poll  → tempVertex(to=2, w=4)
        // now=2, nowDist=4
        // nowDist(4) > dist[2](4) ? NO → 진행
        //
        // 인접 정점 탐색 graph[2] = [(4,3)]
        //   next(4,3) : newDist = dist[2](4) + 3 = 7  <  dist[4](5) ? NO → 스킵
        //
        // dist  = [ 0, 2, 4, 3, 5 ]
        // pq    = [ (to=2, w=5), (to=4, w=5) ]
        //
        // ── while 5회 ──────────────────────────────
        // poll  → tempVertex(to=2, w=5)
        // now=2, nowDist=5
        // nowDist(5) > dist[2](4) ? YES → ⚡ 스킵!! (이미 4로 갱신됨)
        //
        // pq    = [ (to=4, w=5) ]
        //
        // ── while 6회 ──────────────────────────────
        // poll  → tempVertex(to=4, w=5)
        // now=4, nowDist=5
        // nowDist(5) > dist[4](5) ? NO → 진행
        //
        // 인접 정점 탐색 graph[4] = [] → 인접 정점 없음
        //
        // pq    = []  →  종료!
        //
        // ✅ 최종 결과
        // dist  = [ 0, 2, 4, 3, 5 ]

        while (!pq.isEmpty()) {
            Edge tempVertex = pq.poll();
            int now = tempVertex.to;
            int nowDist = tempVertex.weight;

            if (nowDist > dist[now]) continue;

            for (Edge next : graph[now]) {
                int newDist = dist[now] + next.weight;

                if (newDist < dist[next.to]) {
                    dist[next.to] = newDist;
                    pq.add(new Edge(next.to, newDist));
                }
            }
        }
    }
}