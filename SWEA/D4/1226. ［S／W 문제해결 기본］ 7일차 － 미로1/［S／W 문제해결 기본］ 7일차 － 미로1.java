import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1226. 미로
 * @author neogul02
 * 
 */
public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	static char[][] map;; // 16 * 16 행렬
	
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=10; tc++) {
			
			input();
			int result = bfs();
					
			sb.append('#').append(tc).append(' ')
			.append(result).append('\n');
		}
		System.out.print(sb);
	}
	
	private static int bfs() {
		Deque<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {1,1});
		
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];
			
			for(int i=0; i<4; i++) {
				int nx = dx[i] + x;
				int ny = dy[i] + y;
				
				if(nx>=0&&nx<16&&ny>=0&&ny<16) {
					if(map[nx][ny] == '3') return 1;
					
					if(map[nx][ny] == '0') { // 갈 수 있는 곳이면
						map[nx][ny] = '1'; // 현위치는 방문 처리
						
						queue.add(new int[] {nx,ny});
					}
				}
			}
		}
		return 0;
		
	}

	private static void input() throws IOException {
	
		String trash = br.readLine().trim(); // 사용 안 함
		
		map = new char[16][16];
		
		for(int i=0; i<16; i++) {
			char[] temp = br.readLine().trim().toCharArray();
			map[i] = temp;
		}
//		System.out.println(Arrays.deepToString(map));
	}
}
