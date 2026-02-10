import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class S1_2178_미로탐색 {
	
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
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		
	}

	public static void bfs() {
		// 델타 배열을 만들어보자
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		// 큐 하나를 생성해 초기 위치를 잡아둔다.
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {0,0,1});
		map[0][0] = '0';
		
		
		// 큐가 비어있지 않을때까지 반복
		while(!queue.isEmpty()) {
			int [] temp = queue.poll();
			
			int x = temp[0];
			int y = temp[1];
			int depth = temp[2];
			
			for(int i=0; i<4; i++) {
				int nx = x+ dx[i];
				int ny = y+ dy[i];
				// 인덱스 바운더리 안이면서
				if(nx>=0 && nx<N && ny>=0 && ny<M) {
					// 도착지에 도착하면 sysout ++depth
					if(nx==N-1 && ny==M-1) {
						System.out.println(++depth); 
						return;
						}
					
					if(map[nx][ny] == '1') {
						map[nx][ny] ='0';
						queue.add(new int[] {nx,ny,depth+1});
					}
				}
			}	
		}
	}

}
