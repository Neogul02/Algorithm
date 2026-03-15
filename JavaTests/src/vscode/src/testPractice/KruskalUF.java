import java.util.*;

public class KruskalUF {
    static class Edge {
        int a, b, w;
        Edge(int a, int b, int w) { this.a = a; this.b = b; this.w = w; }
    }

    static int V, E;
    static int[] parent, rank;

    static void makeSet() {
        parent = new int[V + 1];
        rank = new int[V + 1];
        for (int i = 1; i <= V; i++) parent[i] = i;
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        a = find(a); b = find(b);
        if (a == b) return false;

        if (rank[a] < rank[b]) parent[a] = b;
        else if (rank[a] > rank[b]) parent[b] = a;
        else { parent[b] = a; rank[a]++; }
        return true;
    }

    static long kruskal(ArrayList<Edge> edges) {
        edges.sort((e1, e2) -> Integer.compare(e1.w, e2.w));
        makeSet();

        long total = 0;
        int picked = 0;

        for (Edge e : edges) {
            if (union(e.a, e.b)) {
                total += e.w;
                picked++;
                if (picked == V - 1) break;
            }
        }

        if (picked != V - 1) return -1; // 연결 안 됨
        return total;
    }
}