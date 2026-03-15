import java.util.*;

public class PrimPQ {
    static class Edge {
        int to, w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }
    static class State {
        int v, w; // v로 들어가는 비용 w
        State(int v, int w) { this.v = v; this.w = w; }
    }

    static int V;
    static ArrayList<Edge>[] g;

    static long prim(int start) {
        boolean[] visited = new boolean[V + 1];
        PriorityQueue<State> pq = new PriorityQueue<>((a,b) -> a.w - b.w);

        pq.add(new State(start, 0));
        long total = 0;
        int picked = 0;

        while (!pq.isEmpty() && picked < V) {
            State cur = pq.poll();
            if (visited[cur.v]) continue;

            visited[cur.v] = true;
            total += cur.w;
            picked++;

            for (Edge e : g[cur.v]) {
                if (!visited[e.to]) pq.add(new State(e.to, e.w));
            }
        }

        // 연결 그래프가 아니면 MST 불가
        if (picked != V) return -1;
        return total;
    }
}