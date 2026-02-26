import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 11724. 연결 요소의 개수
 * @author neogul02
 * 
 * 방향없는 그래프 ? 
 * 정점의 개수 N, 간선의 개수 M
 * 
 * 1. N과 M을 입력받는다.
 * 2. 그래프를 인접 리스트로 표현하고 방문여부를 체크할 visited 배열을 초기화.
 * 3. 간선의 개수 M 만큼 u와 v를 입력받아서 그래프에 양방향 간선을 추가한다.
 * 
 * -> 연결요소의 개수란..? 서로 연결되어있지 않은 그래프 = 연결 요소의 개수,
 * 모두 연결되어있으면? 연결 요소의 개수는 1
 * 
 * 4. 그래프의 1번 부터 N번 정점까지 반복하면서 dfs 탐색을 하고 visited에 체크,
 *  5. dfs 탐색이 끝났는데 visited에 false인 정점이 있다? -> 새로운 연결리스트 
 */
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M; // 정점의 개수, 간선의 개수
    
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 그래프

    static boolean[] visited; // 방문 여부 체크
    static int graphCnt;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.print(graphCnt);
    }

    private static void solve() {
        graphCnt = 0; // 연결요소 개수
        for (int i = 1; i <= N; i++) { // 정점 1부터 N까지 반복
            if (visited[i] == false) { // 방문하지 않은 정점이 있다면
                dfs(i); // dfs 탐색
                graphCnt++; // 연결 요소 개수 + 1
            }
        }
    }

    private static void dfs(int node) {
        visited[node] = true; // 현재 노드 방문 체크
        for(int next : graph.get(node)){
            if (visited[next] == true) continue; // 이미 방문한 노드라면 pass
            dfs(next); // 다음 노드로 dfs 탐색
        }
    }
    
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken()); // 정점 개수
        M = Integer.parseInt(st.nextToken()); // 간선 개수

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>()); // 0번째 인덱스는 사용하지 않음
        }
        visited = new boolean[N + 1]; // 방문 여부 체크 배열 초기화

        for (int i = 0; i < M; i++) { // 간선의 개수 M
            st = new StringTokenizer(br.readLine().trim(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v); // u와 v는 양방향 간선
            graph.get(v).add(u);
        }
        // System.out.println(graph);
    }
}