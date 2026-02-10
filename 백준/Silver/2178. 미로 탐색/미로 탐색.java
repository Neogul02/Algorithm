import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static int M;
	
	public static char[][] map;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for(int i=0; i<N; i++) {
			char[] tempArr = br.readLine().trim().toCharArray();
			map[i] = tempArr;
		}
		
		bfs();
	}

	public static void bfs() {
		int[] dx = {-1,1,0,0}; // 1. 델타배열을 만든다
		int[] dy = {0,0,-1,1};
		
		// 2. 큐에 초기 위치를 넣어둔다
		ArrayDeque<int[]> queue = new ArrayDeque<>(); 
		queue.add(new int[] {0,0,1}); // 시작점이랑 현재 거리 저장
		
		// 탐색한 위치는 다시 돌아가지 않도록 벽으로 처리
		map[0][0] = '0';
		
		while(!queue.isEmpty()) { // 큐가 비어있지 않을때까지 반복 
			
			int [] current = queue.poll(); // 큐 맨앞 값을 가져오고 cuurent로 저장
			
			// current에서 뽑아서 가져옴
			int x = current[0];
			int y = current[1];
			int depth = current[2];
			
			// 상하좌우 델타 좌표롤 검사
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				// map index 안의 위치해있으면 
				if(nx>=0 && nx<N && ny>=0 && ny<M) {
					if(nx ==N-1 && ny == M-1) { // 도착했을때
						System.out.print(++depth);
						return;
					}
					// 길이면 벽으로 막아두고 한 칸 이동, 깊이 +1 해서 큐에 넣기
					if(map[nx][ny] =='1') {
						map[nx][ny] = '0';
						queue.add(new int[] {nx, ny, depth+1});
					}
				}
			}
			
		}
	}

}
