package backup.PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class test2 {

	/**
	 * 1. 테스트 케이스 개수를 입력 받는다.
	 * 2. 각 테스트 케이스 마다.
	 * 	2-1. 노드 개수, X,Y 좌표, E를 입력 받아 저장한다.
	 * 	2-2. 모든 섬들을 순회하면서 거리를 계산하여 edges에 저장한다.
	 * 	2-3. edge를 distance기준 오름차순 정렬한다.
	 * 	2-4. 크루스칼 알고리즘을 수행한다.
	 * 	2-5. weight를 반올림 하여 정수의 형태로 출력한다.	
	 * 3. sb를 출력한다.
	 */
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
	
		// 1. 테스트 케이스 개수를 입력 받는다.
		int testCount = Integer.parseInt(br.readLine().trim());
		// 2. 각 테스트 케이스 마다.
		for (int tc = 1 ; tc <= testCount ; tc++) {
			inputTestCase();
			
			// 2-3. edge를 distance기준 오름차순 정렬한다.
			Arrays.sort(edges, (a,b) -> Double.compare(a.distance, b.distance));

			// 2-4. 크루스칼 알고리즘을 수행한다.
			int count = 0;
			double weight = 0;
			for (Edge edge : edges) {
				if (union(edge.row, edge.col)) {
					count++;
					weight += edge.distance;
					if (count == nodeCount - 1) break;
				}
			}
			
			// 2-5. weight를 반올림 하여 정수의 형태로 출력한다.	
			sb.append("#").append(tc).append(" ").append(Math.round(weight)).append("\n");
		}
		// 3. sb를 출력한다.
		System.out.print(sb);
	}
	
	static int findSet(int a) {
		if (parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a , int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}

	static double getDistancePow(int x1, int y1, int x2, int y2) {
		return Math.pow(Math.abs(x1-x2),2)+Math.pow(Math.abs(y1-y2), 2);
	}
	
	static class Edge {
		int row;
		int col;
		double distance;
		
		public Edge(int row, int col, double distance) {
			this.row = row;
			this.col = col;
			this.distance = distance;
		}
	}
	
	static Edge[] edges;
	static int[] parents;
	static int[] X,Y;
	static double E;
	static int nodeCount;
	private static void inputTestCase() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		// 2-1. 노드 개수, X,Y 좌표, E를 입력 받아 저장한다.
		nodeCount = Integer.parseInt(st.nextToken());
		
		X = new int[nodeCount];
		Y = new int[nodeCount];
		
		st = new StringTokenizer(br.readLine().trim());
		for (int index = 0 ; index < nodeCount ; index++) {
			X[index] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine().trim());
		for (int index = 0 ; index < nodeCount ; index++) {
			Y[index] = Integer.parseInt(st.nextToken());
		}
		
		E = Double.parseDouble(br.readLine().trim());
		
		// 2-2. 모든 섬들을 순회하면서 거리를 계산하여 edges에 저장한다.
		parents = new int[nodeCount + 1];
		edges = new Edge[(nodeCount * (nodeCount - 1)) / 2];
		int edgeIdx = 0;
		for (int row = 0 ; row < nodeCount ; row++) {
			for (int col = row + 1 ; col < nodeCount ; col++) {	
				edges[edgeIdx] = new Edge(row,col,E * getDistancePow(X[row], Y[row], X[col], Y[col]));
				edgeIdx++;
			}
		}
		
		for (int index = 1 ; index <= nodeCount ; index++) {
			parents[index] = index;
		}
	}
}
