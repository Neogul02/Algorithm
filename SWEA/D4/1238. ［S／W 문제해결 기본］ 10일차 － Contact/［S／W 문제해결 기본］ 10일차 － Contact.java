import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1238. Contact
 * @author neogul02
 * 
 * from->to (단방향 그래프)
 * depth가 마지막 일때 최종 depth 레벨의 노드들 중 가장 큰 값을 출력
 *  
 * @main
 * 1. 10번 반복, 테스트케이스 10번 고정처리
 * 2. 동적 배열 100칸, 방문처리 배열 100칸 초기화 
 * 3. input() 입력처리
 * 4. bfs() 처리
 * 5. sb 출력 
 *
 */
public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static ArrayList<ArrayList<Integer>> graph;
	
	static int maxPeople;
	
	static int length, startNode;
	
	static boolean[] visited;  // 노드 1~100 제한
	
	public static void main(String[] args) throws IOException{
		
		for(int tc=1; tc<=10; tc++) {
			
			graph = new ArrayList<>();
			// 1~100
			for(int i=0; i<101; i++) {
				graph.add(new ArrayList<>());
			}
			visited = new boolean[101];
			
			maxPeople = Integer.MIN_VALUE;
			
			input();
			
			graphBFS();
			
			sb.append('#').append(tc).append(' ')
			.append(maxPeople).append('\n');
		}
		System.out.print(sb);
	}

	/**
	 * @graphBFS
	 * 1. bfs 큐 생성
	 * 2. 시작위치와 depth를 큐에 저장하고 방문처리
	 * 3. 큐가 비어있지않을때까지 반복, while ->
	 *  3-1. 큐에서 가져와서 가져온 노드랑 깊이를 임시 저장
	 *  3-2. 현재 depth랑 노드 최댓값 최신화
	 *  3-3. visited 배열에 방문처리를 해두고 다음 노드로 depth +1 해서 다음 큐에 저장 
	 */
	private static void graphBFS() {
		Queue<int[]> queue = new ArrayDeque<>();
	    
	   
	    queue.add(new int[]{startNode, 0});  // {시작 노드 번호, depth} 큐에 넣고 시작
	    visited[startNode] = true; // 시작 위치 방문처리
	    
	    int maxDepth = 0;
	    int maxNode = startNode;
	    
	    while (!queue.isEmpty()) {
	        int[] cur = queue.poll();
	        int node = cur[0];
	        int depth = cur[1];
	        
	        // depth 갱신
	        if (depth > maxDepth) {
	            maxDepth = depth;
	            maxNode = node;
	        } else if (depth == maxDepth) {
	            maxNode = Math.max(maxNode, node);
	        }
	        
	        // 다음 노드 탐색
	        for (int next : graph.get(node)) {
	            if (visited[next] != true) {
	                visited[next] = true;
	                queue.add(new int[]{next, depth + 1});
	            }
	        }
	    }
	    maxPeople = maxNode;	
	}

	private static void input() throws IOException {
		
		st = new StringTokenizer(br.readLine().trim(), " ");
		length = Integer.parseInt(st.nextToken());
		startNode = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine().trim()," ");
		for(int i=0; i<length/2; i++) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to); // 단방향 그래프 세팅
		}
	}
}