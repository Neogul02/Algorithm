import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * BOJ_5567. 결혼식
 * [IDEA]
 * BFS start = 1부터 시작해서 depth 3까지의 노드 총개수를 구하면 될것같음
 * 친구 = depth 2, 친구의 친구 = depth 3, depth 3 초과는 친구친구친구라 남임
 * 
 *  1. N 동기의 수를 입력받는데
 *  2. 리스트의 길이 M을 입력받는다.
 *  3. M 만큼 반복하며 각 친구들의 관계를 입력받는다.
 *   3-1. graph 리스트 리스트 N개 만들고 
 *   3-2. 친구 관계를 1번 인덱스부터 채운다 1번노드 [2,3] ..
 *   3-3. bfs(1번 깊이부터) 탐색시작
 *  
 *  bfs
 *  1. deque 하나를 만들어서 범위 관리
 *  2. 큐에 시작 루트노드 인덱스 (1)과 depth 1부터 시작 배열을 넣는다
 *  3. 큐가 비어있지 않을때까지 반복
 *   3-1. 큐 앞에서 하나를 가져온다.
 *   3-2. 3-1 에서 가져온 요소에서 친구번호와 깊이값을 분리한다.
 *   3-3. 깊이가 3이 되면 탐색을 종료한다. 친구와 친구의 친구는 depth 3까지!
 *   3-4. 그래프 배열에서 요소를 꺼내온다
 *    3-4-1. 방문한 노드가 아니면 방문처리하고 친구 cnt를 증가시키고 깊이 1을 증가시켜 큐에 넣는다.
 *  4. 최종 cnt 출력 = 친구 수
 * 
 */
public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int N, M;
	
	public static void bfs(int start) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.add(new int[] {start,1}); // 시작 큐, 깊이
		visited[start] = true;
		
		int cnt=0;
		
		while(!q.isEmpty()) {
			int [] current = q.poll();
			int friend = current[0], depth = current[1];
			
			if(depth==3) break;
			
			for(int nextFriend : graph[friend]) {
				if(visited[nextFriend]!=true) {
					visited[nextFriend] = true;
					cnt++;
					q.add(new int[] {nextFriend, depth+1});
				}
			}
		}
		System.out.println(cnt);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine().trim());	
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine().trim());
		int M = Integer.parseInt(st.nextToken());
		
		// 그래프 그리기
		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<Integer>();
			
		}
		
		for(int idx=0; idx<M; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph[A].add(B);
			graph[B].add(A);
		}
		bfs(1);
	}
}
