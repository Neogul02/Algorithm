import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 1. 테스트케이스 T 입력받기
 * 2. 정수 N 입력받기
 * 3. N개의 줄에 N개의 정수 (정사각형) 배열 값 입력받기
 * 
 * 4. 가장 많은 이동을 하는 방의 번호를 찾아야함 .. dfs로 돌아
 *  4-1. 각 좌표마다 4방향 탐색을 진행 N*N 번의 dfs를 돌아야함
 *  4-2. 배열 범위 초과를 생각해줘야할듯
 *  4-3. 방문하려는 곳이 현재 값보다 1 커야만 방문 가능하게 조건을 추가
 *  
 */
public class Solution {
	
	static int N;
	static int[][] square;
	
	static boolean[][] visited;
	
	static int resultNum; // 최종 방 번호 
	static int resultDepth; // 가장 멀리간 깊이
	
	static int tempDepth; // dfs 돌 때마다 현재 깊이
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 1. 테스트케이스 T 입력받기
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			// 2. 정수 N 입력받기
			N = Integer.parseInt(br.readLine().trim());
			square = new int[N][N];
			
			// 3. N개의 줄에 N개의 정수 (정사각형) 배열 값 입력받기
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim()," ");
				for(int j=0; j<N; j++) {
					square[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			resultNum = 0;
			resultDepth = 0;
			
			visited = new boolean[N][N];
			for(int x=0; x<N; x++) {
				for(int y=0; y<N; y++) {
					// 방문했던 방이면 skip
					if(visited[x][y] == true) continue;
					else {
						visited[x][y] = true; // 방문 처리
						
						tempDepth = 1; // 시작하는 방도 포함해서 방 갯수 새기
						dfs(x,y);
						
						// 최소 번호 찾기 로직
						if(tempDepth>resultDepth) {
							resultNum = square[x][y]; // 시작 번호
							resultDepth = tempDepth; // 시작번호에서 갔을때 도달한 깊이
						}else if(resultDepth == tempDepth) { // 만약 깊이가 같으면??
							// 둘 중 resultNum이 작은걸로 결정
							resultNum = Math.min(resultNum, square[x][y]);
						}
					}
				}
			}
			
			sb.append('#').append(tc).append(' ')
			.append(resultNum).append(' ').append(resultDepth)
			.append('\n');
		}
	
		System.out.print(sb);
	}

	private static void dfs(int x, int y) {
		
		// 수행 로직
		for(int i=0; i<4; i++) {
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			
			// arrayIndexCheck
			if(nx>=0 && ny>=0 && nx<N && ny<N) {
				// 방문하려는 곳이 현재 값보다 1 커야만 방문가능
				if(square[x][y]+1 == square[nx][ny]) { // 기저 조건 역할
					visited[nx][ny] = true;
					tempDepth++;
					dfs(nx,ny);
				}
				
			}
        }	
	}
}