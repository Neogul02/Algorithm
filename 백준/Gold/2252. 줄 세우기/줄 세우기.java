import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2252.줄 세우기
 * @author neogul02
 *
 * N명의 학생들을 키 순서대로 줄 세우기
 * N, M 입력받고 M 만큼 반복해서 입력받는데 그래프 위상정렬??? 로 풀면 될거같음 그래프 부터 구현
 * 
 * A가 B보다 앞에 서야함 .. 
 */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph; // 그래프
	static int[] indegree; // 진입 차수
	
	public static void main(String[] args) throws IOException{
		input();
		wesangSort();
		System.out.print(sb);
	}

	private static void wesangSort() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		// 1. 진입차수가 0인 노드를 시작으로 추가
		for(int i=1; i<=N; i++) {
			if(indegree[i]==0) {
				queue.add(i);
			}
		}
		
		// 2. 큐가 빌 때까지 반복
		while(queue.isEmpty()!=true) {
			int temp = queue.poll();
			sb.append(temp).append(' ');
			
			for(int next : graph.get(temp)) {
				indegree[next]--; // 현재 노드와 연결된 노드들의 진입차수 1개 줄이기
				
				if (indegree[next] == 0) queue.add(next); 
				
			}
		}
	}
	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 그래프 초기화
		graph = new ArrayList<>(); // 0번째 인덱스는 안씀
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		indegree = new int[N + 1];
		
		// 간선 정보 입력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph.get(A).add(B); // 
			indegree[B]++;    // B의 진입차수 증가
		}
		
	}
}
