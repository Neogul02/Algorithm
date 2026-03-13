package MST;
import java.io.*;
import java.util.*;

// 인접 리스트 버전
public class PrimAdjList {
    
    public static class Node {
        int vertex;
        int weight;

        Node next; // 인접 리스트에서 다음 노드를 가리키는 포인터

        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }
    }
    public static void main(String[] args) throws Exception {

        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        st = new StringTokenizer(br.readLine().trim(), " ");

        int V = Integer.parseInt(st.nextToken()); // 정점 수
        int E = Integer.parseInt(st.nextToken()); // 간선 수

        // int[][] adjMatrix = new int[V][V]; // 인접 행렬

        Node[] adjList = new Node[V]; // 인접 리스트


        boolean[] visited = new boolean[V];
        int[] minEdge = new int[V];

        // 인접 행렬 입력
        for (int i = 0; i < E; i++) { // 간선 개수만큼 돈다!!! 
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 양방향 그래프를 구성, 인접 리스트로 표현
            adjList[from] = new Node(to, weight, adjList[from]);
            adjList[to] = new Node(from, weight, adjList[to]);
        }

        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[0] = 0; // 시작 정점

        long result = 0; // MST의 총 가중치 합
        for (int c = 0; c < V; c++) {
            int min = Integer.MAX_VALUE;
            int minVertex = -1;

            // step1: 비트리 정점 중 가장 작은 가중치의 정점 선택
            for (int i = 0; i < V; i++) {
                if (!visited[i] && min > minEdge[i]) {
                    min = minEdge[i];
                    minVertex = i;
                }
            }

            // 연결 그래프가 아니면 MST 불가(또는 MSF 필요)
            if (minVertex == -1) {
                System.out.println("Graph is disconnected");
                return;
            }

            visited[minVertex] = true;
            result += min;

            // step2: 트리에 새롭게 추가된 정점(minVertex)과 인접한 정점들의 가중치 갱신
            for (Node temp = adjList[minVertex]; temp != null; temp = temp.next) {
                if(visited[temp.vertex]==false && minEdge[temp.vertex] > temp.weight) {
                    minEdge[temp.vertex] = temp.weight;
                }
            }

            
        }

        System.out.println(result);
    }
}