package backup.PS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 13023. ABCDE
 * @author neogul02
 * 
 * - ABCDE 같은 관계가 존재하는가? 이게 무슨소리지 다섯번까지 친구의 친구의 -- 친구인 관계를 찾으라는건가
 * - 양방향 그래프로 초기화하고 깊이탐색해서 깊이가 5까지 가면 return?
 * 
 * 1. @input
 *  1-1. N, M 입력받기
 *  1-2. 그래프 초기화
 *  1-3. 친구 관계 입력받아서 그래프에 저장하기
 * ----------------------
 * 2. @dfs
 *  2-1. 그래프의 각 요소마다 깊이+1 해가며 탐색 시작
 *  2-2. 깊이가 5가 되면 found = true, return
 * 
 * 5 <= N, M <= 2000
 */
public class ABCDE {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static ArrayList<ArrayList<Integer>> graph;

    static boolean[] visited;
    static boolean found = false;

    public static void main(String[] args) throws IOException {
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
            if (visited[next] == true){
                continue;                
            }
            else {
                dfs(next, depth + 1);
                if (found== true) return;
            }
        }
        visited[node] = false; // 백트래킹
    }
    
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
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