package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 1987. 알파벳
 * @author neogul02
 * 
 * @main 
 * isvisited에 지금까지 다녀간 알파벳을 기억해두고 dfs로 가장 멀리 간 depth가 정답
 * (0,0) 에서 출발 
 *
 */
public class G4_1987_알파벳 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int R,C;
	static char[][] map;

	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	static int maxDepth = 1; // 최소 1 (시작위치 알파벳)
	
	static boolean[] visited = new boolean[26]; // 알파벳 26자리 위치 확인
 	
	public static void main(String[] args) throws IOException{
		input();
		visited[map[0][0]-'A'] = true; // 시작위치 (0,0)의 알파벳을 방문처리
		dfs(0,0, 1);

		System.out.print(maxDepth);
	}
	/**
	 * @dfs
	 * 1. 0,0 에서 시작, 해당 좌표 알파벳 방문처리
	 * 2. 델타 배열로 주변 탐색
	 * 3. 다음 탐색할 칸이 visited에 없다면? -> 갈 수 있는곳
	 * 4. 최대 깊이 최신화 visited 배열길이 = 방문한 깊이
	 * 5. 다음 위치로 이동 dfs(nr,nc)
	 * 6. 이전 위치로 백트래킹, visited에 마지막으로 들어간 알파벳 이전으로 백, 다시 탐색
	 */
	private static void dfs(int r, int c, int depth) {
		maxDepth = Math.max(maxDepth, depth); // 최대 깊이 갱신
		for(int i=0; i<4; i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];
			
			if(nr>=0 && nr<R && nc>=0 && nc<C) { // 배열 아웃바운드 처리
				
				char nextChar = map[nr][nc];
				int nextIdx = nextChar -'A';
				
				if(visited[nextIdx] != true) { // 방문한 적 없는 알파벳이면
					visited[nextIdx] = true; // 방문 처리
	
					dfs(nr, nc, depth+1); // 다음 위치로 이동
					
					visited[nextIdx] = false; // 가장 마지막에 탐색한 알파벳 백트래킹
				}
			}
		}
	}

	/**
	 * @input
	 * 세로 R칸 가로 C칸을 입력받아 char[][]로 맵을 초기화한다.
	 */
	private static void input() throws IOException{
		st = new StringTokenizer(br.readLine().trim()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int r=0; r<R; r++) {
			char[] tempArr = br.readLine().toCharArray();
			map[r] = tempArr;
		}
	}
}