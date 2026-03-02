import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1267. 작업순서
 * @author neogul02
 *
 * 위상정렬로 각 순서 출력하기
 */
public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int V, E; // V: 노드 개수, E: 간선 개수
	static ArrayList<ArrayList<Integer>> graph;
	static int[] indegree; // 진입 차수
	
	public static void main(String[] args) throws IOException{
		for(int tc=1; tc<=10; tc++) {
			input();
			sb.append('#').append(tc).append(' ');
			wesangSort();
			sb.append('\n');
		}
		System.out.print(sb);
	}

	private static void wesangSort() {
		Queue<Integer> queue = new ArrayDeque<>();
		
        // 1. 진입차수가 0인 노드를 시작으로 추가
        // 2. 진입차수가 0 이다 = 어떤 노드도 화살표가 꽂히지 않았다 = 가장 먼저 처리해야할 노드이다
        for(int i=1; i<=V; i++){
            if(indegree[i] == 0) {
                queue.add(i); // 진입차수가 0인 노드 큐에 추가
            }
        }
		
        // 2. 큐가 빌 때까지 반복
        while (queue.isEmpty() != true) {
            int temp = queue.poll();
            sb.append(temp).append(' '); // 처리한 노드 출력

            // 방문 처리를 한 노드의 진입차수를 -1 해주고, 진입차수가 0이 된 노드를 큐에 추가
            for (int next : graph.get(temp)) {
                indegree[next]--; // 현재 노드와 연결된 노드들의 진입차수 1개 줄이기
                if(indegree[next] == 0) {
                    queue.add(next); // 진입차수가 0이 된 노드 큐에 추가
                }
            }
        }
		
	}

	private static void input() throws IOException{
		st = new StringTokenizer(br.readLine().trim()," ");
		V = Integer.parseInt(st.nextToken()); 
		E = Integer.parseInt(st.nextToken());
		
        graph = new ArrayList<>();
        
		// 0번부터 V번까지 V+1개의 리스트 초기화
		for(int i=0; i<=V; i++) {
			graph.add(new ArrayList<>()); // 0번째 인덱스는 0번노드
		}
		// 진입 차수 배열 초기화
		indegree = new int[V+1];
		
		st = new StringTokenizer(br.readLine().trim()," ");
        for (int i = 1; i <= E; i++) {
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to); // 그래프 -> 저장
            indegree[to]++; // -> 화살표 꽂히는 쪽에 간선 1개 추가 = 진입 차수 +1
        }
        
        // System.out.println(graph);
        // System.out.println("indegree: " + java.util.Arrays.toString(indegree));
	}
}