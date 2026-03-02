import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1389. 케빈 베이컨의 6단계 법칙
 * @author neogul02
 * 
 * 1. 유저의 수 N, 친구 관계의 수 M -> 노드 N개, 간선 M개인 그래프
 * 2. A랑 B가 친구면 B랑 A도 친구임 == 양방향 그래프
 * 3. 케빈 베이컨의 수가 가장 작은 사람을 구해라, 각 노드에 대해 다른 노드들의 최단거리를 모두 합해서 저장해두고 비교하면 될듯
 * 
 * 모든 사람은 연결되어있으며, 친구가 한 명도 없는 유저는 없다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M; // 노드 수 N, 간선 수 M
    static ArrayList<ArrayList<Integer>> graph;; // 그래프

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    private static void solve() {
        int minSum = Integer.MAX_VALUE; // 최솟값
        int minNode = 0; // 최솟값 노드 번호

        // 1. 각 노드마다 다른 노드들을 순회하면서 깊이가 몇인지 kevinBacon에 저장해두기
        for (int startNode = 1; startNode <= N; startNode++) {
            int sum = bfs(startNode); // 1-2-3..N 노드 

            if (sum < minSum) {
                minSum = sum;
                minNode = startNode;
            }
        }
        System.out.print(minNode);
    }
    
    private static int bfs(int startNode) {

        int[] distance = new int[N + 1]; // 각 거리 저장
        boolean[] visited = new boolean[N + 1]; // 방문처리

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startNode); // 시작위치 큐에 넣고
        visited[startNode] = true; // 방문처리 한 다음에

        // bfs 시작
        while (queue.isEmpty() == false) {
            int temp = queue.poll();

            for (int next : graph.get(temp)) {
                if(visited[next]==true) continue; // 방문한 노드면 skip

                visited[next] = true; // 다음 노드 미리 방문처리
                distance[next] = distance[temp] + 1; 
                queue.add(next); // 다음 노드 큐에 넣기
            }
        }
        
        int sum = 0;
        for(int i =1; i<=N; i++){
            sum += distance[i];
        }
        return sum;
    }

    private static void input() throws IOException{
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) { // N+1 만큼 초기화
            graph.add(new ArrayList<>());
        }

        // 양방향 그래프 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            graph.get(nodeA).add(nodeB);
            graph.get(nodeB).add(nodeA);
        }
    } 
}