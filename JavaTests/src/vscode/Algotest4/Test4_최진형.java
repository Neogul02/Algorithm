import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Test4_최진형{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N, M, X;
	
	static ArrayList<ArrayList<Edge>> graph;
	static int[] dist;
	
	static  final int INF = Integer.MAX_VALUE;
 		
	public static class Edge{
		int to;
		int weight;
		
		Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}
	public static void main(String[] args) throws IOException{
		input();
		
		dijkstra(X);
		System.out.println(Arrays.toString(dist));
	}
	
	private static void dijkstra(int start) {
		// 1. dist 배열 초기화
		dist = new int[N+1];
		for(int i=1; i<N+1; i++) {
			dist[i] = INF;
		}
		
		// 2. start dist는 0
		dist[start] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((A,B)-> A.weight-B.weight);
		pq.add(new Edge(start, 0));
		
		while(pq.isEmpty()!= true) {
			Edge temp = pq.poll();
			int edgeTo = temp.to;
			int edgeWeight = temp.weight;
			
			if(edgeWeight > dist[edgeTo]) continue;
			
			
			for(Edge next : graph.get(edgeTo)) {
				
				int tempWeight = next.weight + dist[edgeTo];
				
				if(tempWeight< dist[next.to]) {
					dist[next.to] = tempWeight;
					pq.add(new Edge(next.to, tempWeight));
				}
			}
		}
		
		// 
		
			
	}

	public static void input() throws IOException{
		
		st = new StringTokenizer(br.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		// 그래프 초기화
		for(int i=0; i<M+1; i++) {
			graph.add(new ArrayList<Edge>());
		}
		
		// 간선만큼 반복
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine().trim()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Edge(to, weight));
		}
		
		
	}
}