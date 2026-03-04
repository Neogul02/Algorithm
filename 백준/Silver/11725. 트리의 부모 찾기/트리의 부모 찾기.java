import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * 11725. 트리의 부모 찾기
 * @author neogul02
 * 
 * - 루트 없는 트리 = 그래프
 * 1. 양방향 그래프로 초기화 하고
 * 2. bfs 큐에 1번 노드를 넣고 탐색 시작
 *  2-1. bfs 돌면서 연결된 요소는 자식요소, 현재 노드는 부모 요소로 저장
 *  2-2. 방문처리 해주면서 bfs 탐색하고 result 배열에 부모 노드 저장
 * 3. 2번 노드부터 N번 노드까지의 부모 노드 출력 = result[2] ~ result[N]
 * 
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static ArrayList<ArrayList<Integer>> graph;

    static boolean[] visited;
    static int[] result;
    
    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.print(sb);
    }
        
    private static void solve() {
        result = new int[N + 1]; // 부모 노드를 저장할 배열, 1-based index
        visited = new boolean[N + 1]; // 방문 여부 체크 배열

        bfs();

        for (int i = 2; i < N + 1; i++){ // 2번 노드부터 N번 노드까지 부모 노드 출력
            sb.append(result[i]).append('\n');
        }
    }
    
    private static void bfs() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(1); // 루트 노드는 1부터
        visited[1] = true;
        
        while (queue.isEmpty() != true) {
            int nodeIdx = queue.poll();

            for (int child : graph.get(nodeIdx)) {
                if (visited[child] == true) continue; // 이미 방문한 노드면 패스
                                    
                visited[child] = true; // 방문처리
                result[child] = nodeIdx;

                queue.add(child); // 자식 노드 큐에 추가
            }
        }
    }
    
    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        graph = new ArrayList<>();

        for (int i = 0; i < N+1; i++) { // N+1만큼
            graph.add(new ArrayList<>());
        }
        
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B); 
            graph.get(B).add(A); // 양방향 그래프로 초기화
        }
        // System.out.println(graph);
    }
    
}
