package template;

import java.util.*;

public class TopologySort {
    static int N = 7; // 노드 개수
    static ArrayList<Integer>[] graph;
    static int[] indegree;
    
    public static void topologySort() {
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        
        // 진입 차수가 0인 노드 큐에 삽입
        for(int i = 1; i <= N; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            result.add(cur);
            
            for(int next : graph[cur]) {
                indegree[next]--;
                
                if(indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        // 결과 출력
        if(result.size() != N) {
            System.out.println("사이클 존재!");
        } else {
            System.out.print("위상 정렬 결과: ");
            for(int node : result) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        // 그래프 초기화
        graph = new ArrayList[N + 1];
        indegree = new int[N + 1];
        
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 간선 추가 (방향 그래프)
        // 1 → 2
        graph[1].add(2);
        indegree[2]++;
        
        // 1 → 5
        graph[1].add(5);
        indegree[5]++;
        
        // 2 → 3
        graph[2].add(3);
        indegree[3]++;
        
        // 2 → 6
        graph[2].add(6);
        indegree[6]++;
        
        // 3 → 4
        graph[3].add(4);
        indegree[4]++;
        
        // 4 → 7
        graph[4].add(7);
        indegree[7]++;
        
        // 5 → 6
        graph[5].add(6);
        indegree[6]++;
        
        // 6 → 4
        graph[6].add(4);
        indegree[4]++;
        
        topologySort();
    }
}