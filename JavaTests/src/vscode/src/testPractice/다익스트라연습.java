package testPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라 알고리즘 연습
public class 다익스트라연습{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int V, E; // 정점의 개수 V, 간선의 개수 E

    static int start; // 시작 정점 번호

    static ArrayList<ArrayList<Edge>> graph; // 그래프를 인접 리스트로 표현
    static int[] dist; // 시작 정점에서 각 정점까지의 최단 거리

    static final int INF = Integer.MAX_VALUE; // 무한대를 나타내는 값

    static class Edge {
        int to; // 도착 정점 번호
        int weight; // 간선의 가중치

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException{
        input();
        dijkstra(start);
        for(int i=1; i<=V; i++){
            if(dist[i] == INF){
                sb.append("INF\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }
        System.out.print(sb);

    }

    public static void dijkstra(int start){
        // 1. dist 배열 초기화
        dist = new int[V+1];
        for(int i=1; i<=V; i++){
            dist[i] = INF; // 최단 거리를 무한대로 초기화
        }
        dist[start] = 0; // 시작 정점의 거리는 0

        // 2. 우선순위 큐를 사용하여 최단 거리가 가장 짧은 정점을 먼저 처리
        PriorityQueue<Edge> pq = new PriorityQueue<>((A, B) -> A.weight - B.weight);
        pq.add(new Edge(start, 0)); // 시작 정점을 큐에 추가

        while(pq.isEmpty()!= true){
            Edge temp = pq.poll(); // 큐에서 가장 짧은 거리를 가진 정점을 꺼냄
            int tempTo = temp.to; // 현재 정점 번호
            int tempWeight = temp.weight; // 현재 정점까지의 최단 거리

            if(tempWeight > dist[tempTo]) continue; // 이미 처리된 정점이면 무시

            // 3. 현재 정점과 인접한 정점들을 탐색
            for(Edge edge : graph.get(tempTo)){
                int nextTo = edge.to; // 인접한 정점 번호
                int nextWeight = tempWeight + edge.weight; // 현재 정점까지의 거리 + 간선의 가중치

                if(nextWeight < dist[nextTo]){ // 더 짧은 경로를 찾은 경우
                    dist[nextTo] = nextWeight; // 최단 거리 업데이트
                    pq.add(new Edge(nextTo, nextWeight)); // 큐에 추가하여 다음에 처리하도록 함
                }
            }
        }
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine().trim());

        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) 
            graph.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Edge(to, w));
        }
    }

}