package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 7733. 치즈 도둑
 * @author neogul02
 * 1일부터 100일 동안 1,2,3,... 100 까지 각각 맛 칸이 사라진다. 
 * 100일중에서 치즈 덩어리가 가장 많을때의 덩어리 개수를 구하자
 * 
 * 맵 입력을 받고 동일하게 visited 배열을 선언해서 방문처리를 하자
 * 방문이 된 칸은 건너뛰고 요정이 먹은 부분도 방문을 했다고 처리를 해줘도 될것같다.
 * bfs를 돌며 방문처리를 하다가 더이상 큐에 남아있는게 없으면 탈출하고 bfs를 탈출한다. <- 언제 끝내야할까?
 * 0,0부터 N,N까지 돌면서 N,N에 도달했을때 bfs가 몇번 돌았는가? 
 * -> 덩어리의 갯수를 1일부터 100일까지 비교해서 가장 많은 덩어리가 있는값이 result 
 * 
 * @main
 * 1. 테스트케이스 입력받고 반복
 * 2. 입력처리 @input
 * 3. 문제풀이 @solve
 * 4. sb 버퍼 출력
 * 
 */
public class SWEA_치즈도둑 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N; // 맵 한 변의 길이
		
	static int[][] cheeseMap;
	static boolean[][] visited;
	
	static int maxCheeseBlock;

	public static void main(String[] args) throws IOException{
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			input();
			solve();
			sb.append('#').append(tc).append(' ')
			.append(maxCheeseBlock).append('\n');
		}
	System.out.print(sb);
	}

	/**
	 * @solve
	 * 1. 0일차는 1블럭, 1일차에는 1짜리 맛을 먹어버리니까 1로 초기화
	 * 2. 굳이 100일까지 시뮬레이션 할 필요없이 최대 맛을 구하면 가지칠 수 있음
	 * 3. 1일차부터 요정이 먹는 cheese 배열을 초기화
	 * 4. 만들어진 맵을 bfs탐색해서 덩어리 개수를 찾기
	 */
	private static void solve() {
		maxCheeseBlock = 1; // 0일치는 덩어리 1부터 시작
		
		// 최대 맛 점수 구하기, 100번 돌지말고 최대맛의 이하로만 돌아도 괜찮음
	    int maxTaste = 0;
	    for(int r = 0; r < N; r++) {
	        for(int c = 0; c < N; c++) {
	            maxTaste = Math.max(maxTaste, cheeseMap[r][c]);
	        }
	    }  
		
		// 1일 부터 최대 맛(100일)까지 시뮬레이션
		for(int day=1; day<=maxTaste; day++) { 
			// bfs에서 모두 방문처리가 되기때문에 일마다 다시 초기화
			visited = new boolean[N][N];
			// 요정이 치즈 day의 치즈를 먹어버린당
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					// 해당 날과 치즈가 같으면 그 치즈는 먹어버린걸로 처리
					if(cheeseMap[r][c] <= day) {
						visited[r][c] = true;
					}
				}
			}
			// 요정이 해당 일자의 치즈를 다 먹었으면 이제 해당 날은 몇개의 덩어리인지 계산
			cheeseBFS();
		}
	}


	/**
	 * @cheeseBFS
	 * 1. 맵에서 덩어리의 개수를 찾으면 된다
	 * 2.0,0부터 N,N까지 돌다가 방문하지않은곳을 찾으면 그게 한 덩어리가 됨 -> bfs 
	 */
	private static void cheeseBFS() {
		int blockCnt = 0;
		// 0,0부터 N,N까지 검사해야함
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				// 방문하지 않은곳 = 아직 안먹힌 치즈 덩어리
				if(visited[r][c] == false) {
					blockCnt++; // 새 덩어리
					
					// 방문처리용 bfs
					bfs(r,c);
				}
			}
		}
		maxCheeseBlock = Math.max(maxCheeseBlock, blockCnt);
	}


	/**
	 * @bfs
	 * 1. 큐 만들어서 r,c 핸들링 및 방문한 위치 방문처리
	 * 2. 4방향 델타탐색, queue가 빌때까지 반복
	 * 3. 방문한곳에서 visited != true 면 방문하지 않은곳이니까 큐에 넣고 방문처리
	 */
	private static void bfs(int r, int c) {
		// bfs 용 큐 만들기
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {r,c}); // 찾은 덩어리 처음 블럭 큐에 넣고 방문처리
		visited[r][c] = true;
		
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,1,-1};
		
		while(queue.isEmpty() != true) {
			int [] temp = queue.poll();
			int temp_r = temp[0];
			int temp_c = temp[1];
			
			for(int i=0; i<4; i++) {
				int nr = temp_r+dx[i];
				int nc = temp_c+dy[i];
				
				// 아웃바운드 처리
				if(nr>=0 && nr<N && nc>=0 && nc<N) {
					// 방문했던곳이 아니라면 방문하고 다음칸으로 이동
					if(visited[nr][nc] != true) {
						visited[nr][nc] = true;
						queue.add(new int[] {nr,nc});
					}
				}
			}
		}
	}


	private static void input() throws IOException {
		// 한 변의 길이 N 입력받기
		N = Integer.parseInt(br.readLine().trim());
		
		// 입력받은 N으로 치즈 배열 초기화
		cheeseMap = new int[N][N];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine().trim()," ");
			for(int c=0; c<N; c++) {
				cheeseMap[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
}
