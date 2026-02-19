package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1325. 효율적인 해킹
 * @author neogul02
 * [IDEA]
 * A가 B를 신뢰하는 경우에는 B를 해킹하면 A도 해킹할 수 있다.. 단방향 그래프?
 * B->A 형태로 그래프를 그리고 BFS 를 각 노드마다 진행해서 가장 깊이가 깊은 애들이 많은 컴퓨터를 해킹 가능하다고 가정
 * 깊이가 큰 애들을 배열에 저장하고 오름차순으로 출력?
 * 
 * 1. N번까지의 번호, M번의 입력을 받는다
 * 2. ArrayList 그래프를 N+1만큼 선언하고 1번째 인덱스부터 1노드라고 가정하고 초기화
 * 3. 1.에서 입력받은 M 만큼 반복
 *  3-1. 공백기준으로 A B 를 입력받고 graph[B]에 A를 담는 단방향 그래프를 만든다.
 * 4. 각 노드 마다 bfs를 돌기 cnt가 곧 깊이->많은 컴퓨터를 신뢰함 -> 해킹 많이된다.
 * 5. 각 깊이를 저장해두는 배열 hackingCnt를 만들어두고 최댓값을 찾음
 * 6. 최댓값이면 sb에 공백으로 append 해서 출력
 *
 */
public class 효율적인해킹1325 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N,M;
	
	static boolean[] visited;
	
	static ArrayDeque<Integer>[] graph;
	
	public static int bfs(int startNode) {
		int cnt=0;
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		visited = new boolean[N+1];
		
		visited[startNode] = true;
		q.add(startNode);
		
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: graph[current]) {
				if(visited[next]==false) {
					visited[next] = true; // 방문처리
					q.add(next);
					cnt++;
				}
				
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayDeque[N+1];
		// 그래프 만들기
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayDeque<>();
		}
		visited = new boolean[N+1];
		
		// 그래프 관계 설정 (단방향)
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph[B].add(A);
		}
		
		int[] hackingCnt = new int[N+1];
		for(int i=1; i<=N; i++) {
			int temp = bfs(i);
			hackingCnt[i] = temp;
		}
		
		int maxValue =Arrays.stream(hackingCnt).max().getAsInt();

		
		for(int i=1; i<=N; i++) {
			if(hackingCnt[i]==maxValue) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb);
		
		//testPrints
//		System.out.println(maxValue);
//		System.out.println(Arrays.toString(hackingCnt));
//		for(int i=1; i<=N; i++) {
//			System.out.println(graph[i]);
//			System.out.println(Arrays.toString(hackingCnt[i]));
//		}
		
	}
}
