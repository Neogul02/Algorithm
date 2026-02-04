package template;
import java.util.*;

public class Dijkstra {
    static class Node implements Comparable<Node> {
        int to, cost;
        
        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    
    static int N = 6; // 노드 개수
    static ArrayList<Node>[] graph;
    
    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            // 이미 처리된 노드면 스킵
            if(cur.cost > dist[cur.to]) continue;
            
            for(Node next : graph[cur.to]) {
                int newCost = dist[cur.to] + next.cost;
                
                if(newCost < dist[next.to]) {
                    dist[next.to] = newCost;
                    pq.offer(new Node(next.to, newCost));
                }
            }
        }
        
        return dist[end];
    }
    
    public static void main(String[] args) {
        // 그래프 초기화
        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 간선 추가 (양방향)
        graph[1].add(new Node(2, 2));
        graph[1].add(new Node(3, 5));
        graph[2].add(new Node(1, 2));
        graph[2].add(new Node(3, 3));
        graph[2].add(new Node(4, 1));
        graph[3].add(new Node(1, 5));
        graph[3].add(new Node(2, 3));
        graph[3].add(new Node(4, 3));
        graph[3].add(new Node(5, 1));
        graph[4].add(new Node(2, 1));
        graph[4].add(new Node(3, 3));
        graph[4].add(new Node(5, 1));
        graph[5].add(new Node(3, 1));
        graph[5].add(new Node(4, 1));
        graph[5].add(new Node(6, 2));
        graph[6].add(new Node(5, 2));
        
        int result = dijkstra(1, 6);
        System.out.println("1번 노드에서 6번 노드까지 최단 거리: " + result);
    }
}