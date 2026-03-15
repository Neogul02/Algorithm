package testPractice;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 그래프 BFS/DFS 템플릿 + ArrayList<ArrayList<Integer>> 버전 + 간단 시뮬레이션
 *
 * - 정점 번호: 1..V (1-based)
 * - 그래프: 무방향 예시(양쪽에 간선 추가)
 * - BFS: ��(FIFO)로 레벨 순서 방문
 * - DFS: 재귀로 깊게 방문
 */
public class GraphBfsDfsSimulation {

    static int V; // 정점 수
    static ArrayList<ArrayList<Integer>> graph; // 인접 리스트
    static boolean[] visited;

    public static void main(String[] args) {

        // 1) 예시 그래프 만들기 (정점 1..6)
        V = 6;
        graph = new ArrayList<>();

        // graph.get(i) 를 i번 정점의 인접 리스트로 쓰기 위해
        // 0번은 안 쓰지만 인덱스 맞추려고 하나 더 만들어 둔다.
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        // 2) 간선 추가 (무방향 그래프)
        addUndirectedEdge(1, 2);
        addUndirectedEdge(1, 3);
        addUndirectedEdge(2, 4);
        addUndirectedEdge(2, 5);
        addUndirectedEdge(3, 5);
        addUndirectedEdge(4, 6);
        addUndirectedEdge(5, 6);

        // (선택) 방문 순서를 일정하게 만들려면 인접 리스트를 정렬하기도 함
        // for (int i = 1; i <= V; i++) Collections.sort(graph.get(i));

        // 3) 그래프 출력(확인용)
        printGraph();

        // 4) BFS 시뮬레이션
        visited = new boolean[V + 1];
        System.out.println("\n[BFS] start=1");
        bfs(1);

        // 5) DFS 시뮬레이션
        visited = new boolean[V + 1];
        System.out.println("\n[DFS] start=1");
        dfs(1);
        System.out.println(); // 줄바꿈
    }

    /** 무방향 간선 추가 */
    static void addUndirectedEdge(int a, int b) {
        graph.get(a).add(b);
        graph.get(b).add(a);
    }

    /** 그래프 인접 리스트 출력 */
    static void printGraph() {
        System.out.println("[Graph adjacency list]");
        for (int i = 1; i <= V; i++) {
            System.out.println(i + " -> " + graph.get(i));
        }
    }

    /**
     * BFS(너비 우선 탐색)
     * - 큐에 시작 정점을 넣고
     * - 하나씩 꺼내면서 인접 정점들을 방문하지 않았다면 큐에 넣는다.
     *
     * 여기서는 "방문 순서"를 출력해서 시뮬레이션처럼 볼 수 있게 함.
     */
    static void bfs(int start) {
        ArrayDeque<Integer> q = new ArrayDeque<>();

        visited[start] = true;  // 큐에 넣는 순간 방문처리(중복 삽입 방지)
        q.add(start);

        int step = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();

            // 방문(처리) 시점
            System.out.println("step " + (step++) + ": pop " + cur + " | queue=" + q);

            // 인접 정점 확인
            for (int nxt : graph.get(cur)) {
                if (visited[nxt]) continue;

                visited[nxt] = true;
                q.add(nxt);

                System.out.println("    push " + nxt + " | queue=" + q);
            }
        }
    }

    /**
     * DFS(깊이 우선 탐색) - 재귀 버전
     * - 현재 정점을 방문처리 후
     * - 인접 정점 중 방문 안 한 정점을 재귀로 방문
     *
     * 여기서는 방문 순서를 한 줄로 출력.
     */
    static void dfs(int cur) {
        visited[cur] = true;
        System.out.print(cur + " ");

        for (int nxt : graph.get(cur)) {
            if (visited[nxt]) continue;
            dfs(nxt);
        }
    }
}