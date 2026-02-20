import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1227. 미로 2
 * @author neogul02
 * 
 * 0은 갈 수 있는 길, 1은 벽
 * 2는 출발점 3은 도착점
 * 
 * 2에서 출발해서 3으로 도착할 수 있으면 1 도착할 수 없으면 0
 * 100*100 행렬 고정, 시작점은 (1,1) 고정, 도착점은 랜덤
 * 
 * bfs랑 dfs 둘 다 큰 차이는 없을것같음 bfs로 먼저 해보자
 */
public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static char[][] map;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		
		for(int tc=1; tc<=10; tc++) { // tc 10번 고정
			input();
			int result = bfs();
			
			sb.append('#').append(tc).append(' ')
			.append(result).append('\n');
		}
		System.out.print(sb);
	}

	/**
	 * @bfs
	 * 1. Deque 생성해서 초기(1,1)을 시작점으로 방문처리하고 출발
	 * 2. visited를 따로 사용하지 않고 방문처리는 0을 1로 바꿔주는걸로 생각
	 * 3. 돌다가 3을 만나면 루프 종료, 3을 못만나면 도착점 방문 불가
	 */
	private static int bfs() {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {1,1}); // 1,1 시작
		map[1][1] = '1'; // 시작점 방문처리
		
		while(queue.isEmpty()!=true) { // 큐가 비어있지 않을때까지 반복
			int[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				// 도착점을 만나면 바로 종료 return
				if(map[nx][ny] == '3') return 1;
				
				// 아웃바운드 처리 = 어차피 맵 주변이 1로 둘러쌓여있기 때문에 1이 아닐때만 고려하면 될듯
				if(map[nx][ny] != '1') {
					map[nx][ny] = '1'; // 방문처리
					queue.add(new int[] {nx,ny});
				}
			}
		}	
		return 0;
	}

	/**
	 * @input
	 * 1. 테스트 케이스를 입력은 받지만 사용하지는 않음 main에서 10번 반복
	 * 2. charArray로 각각 100 * 100 행렬 입력받기 -> map
	 */
	private static void input() throws IOException{
		String T = br.readLine(); // dummy
		map = new char[100][100];
		
		for(int x=0; x<100; x++) {
			char[] temp = br.readLine().trim().toCharArray();
			map[x] = temp;
		}
	}
}