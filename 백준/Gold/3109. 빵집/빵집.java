import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 3109. 빵집
 * @author neogul02
 * 글로벌 재정 위기를 피해가지 못한 김원웅.. 파산 직전이다.
 *
 * R * C 격자 배열로 map
 * 첫째 열은 근처 빵집의 가스관이고(가스 훔칠곳), 마지막 열은 원웅이의 빵집(훔친놈)
 * 왼쪽 -> 오른쪽 으로 그리디 
 * 1순위. 오른쪽 위로 가보기 시도
 * 2순위. 1이 안될때 오른쪽으로 시도
 * 3순위 . 1,2가 안될때 오른쪽 아래로 시도
 * -> 1,2,3이 모두 안된다면 나가리 -> 다음 열로 
 * 
 */
public class Main {
	
	static int R,C;
	static char[][] map;
	
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken()); // Row 행 
		C = Integer.parseInt(st.nextToken()); // Column 열
		
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().trim().toCharArray();
		}
		
		for (int r = 0; r < R; r++) {
            // 첫 번째 열(0)에서 시작
            if (dfs(r, 0)) {
                result++;  // 마지막 열까지 도달 성공
            }
        }
		System.out.println(result);
	}
    
	// 오른쪽위 (-1,1), 오른쪽 (0,1), 오른쪽아래(1,1)
	static int[] dr = {-1,0,1}; // 우선순위 배열
	static int[] dc = {1,1,1};
	
	static boolean dfs(int r, int c) {
		// 종료조건
		if(c==C-1) return true; // 마지막 우리 빵집까지 오면 성공
		
		for(int i = 0; i<3; i++) {
			 int nr = r + dr[i];
	         int nc = c + dc[i];
	         
	         // 1. 범위 체크
	         if(!(nr>=0 && nr<R && nc>=0 && nc<C)) continue;
	         
	         // 2. 장애물인지 체크
	         if(map[nr][nc]!='.') continue;
	         
	         // 3. 1,2 만족이면 갈 수 있다는거니까 현재 자리는 방문처리
	         map[nr][nc] = 'x';
	         
	         // 4. 다음 칸으로 이동 (dfs)
	         if(dfs(nr,nc)) return true;
		}
		return false;
	}
}