import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 * 13023. ABCDE
 * @author neogul02
 * 
 * - ABCDE 같은 관계가 존재하는가? 이게 무슨소리지 다섯번까지 친구의 친구의 -- 친구인 관계를 찾으라는건가
 * - 양방향 그래프로 초기화하고 깊이탐색해서 깊이가 5까지 가면 return?
 * 5 <= N, M <= 2000
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static ArrayList<ArrayList<Integer>> graph;

    static boolean[] visited;
    static boolean found = false;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        input();

        // 모든 노드에서 DFS 시작
        for (int i = 0; i < N; i++) {
            if (found==true) break; // 이미 찾았으면 종료

            dfs(i, 1); // 깊이 1부터 시작
        }

        System.out.print(found ? 1 : 0);
    }

    // depth = 현재 깊이 (0~4)
    public static void dfs(int node, int depth) {
        if (depth == 5) { // A-B-C-D-E depth ==5
            found = true;
            return;
        }

        visited[node] = true;
        for (int next : graph.get(node)) {
            if (!visited[next]) {
                dfs(next, depth + 1);
                if (found== true) return;
            }
        }
        visited[node] = false; // 백트래킹
    }
    
    public static void input() throws IOException {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
      
        visited = new boolean[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a); // 친구는 양방향
        }
    }
}