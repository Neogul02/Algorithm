
import java.util.*;

public class DijkstraBasic {

    public static class Edge {
        int to; // 도착 정점
        int weight; // 가중치

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int V; // 정점 수
    static List<Edge>[] graph; // 인접 리스트
    static int[] dist; // 최단 거리 배열
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        V = 5; // 정점 수 (0번 ~ 4번)

        // 그래프 초기화
        graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 추가
        graph[0].add(new Edge(1, 2));
        graph[0].add(new Edge(2, 5));
        graph[1].add(new Edge(2, 2));
        graph[1].add(new Edge(3, 1));
        graph[2].add(new Edge(4, 3));
        graph[3].add(new Edge(4, 2));

        dijkstra(0); // 0번 정점에서 시작하기

        for (int i = 0; i < V; i++) {
            if (dist[i] == INF) {
                System.out.println("0 -> " + i + " : 도달 불가");
            } else {
                System.out.println("0 -> " + i + " : " + dist[i]);
            }
        }
    }
    public static void dijkstra(int start) {
        // ✅ 1. dist[] 배열 초기화 (모든 정점 INF)
        dist = new int[V];
        for (int i = 0; i < V; i++) {
            dist[i] = INF;
        }

        // ✅ 2. 시작 정점의 최단 거리 0으로 설정
        dist[start] = 0;

        // ✅ 3. 방문 여부 체크 배열
        boolean[] visited = new boolean[V];

        // ✅ 4. V번 반복 (모든 정점에 대해)
        while (
            // ✅ 5. 방문하지 않은 정점 중 최단 거리가 가장 짧은 정점 선택
            int minDist = INF;
            int minIndex = -1;
            for (int j = 0; j < V; j++) {
                if (!visited[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    minIndex = j;
                }
            }

            // ✅ 6. 선택된 정점 방문 처리
            visited[minIndex] = true;

            // ✅ 7. 선택된 정점과 인접한 정점들의 최단 거리 갱신
            for (Edge edge : graph[minIndex]) {
                if (!visited[edge.to] && dist[minIndex] != INF && dist[minIndex] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[minIndex] + edge.weight;
                }
            }
        }
    }
    
}
