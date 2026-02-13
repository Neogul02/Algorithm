import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1074. Z
 * @author neogul02
 * 
 * [IDEA]
 * 정사각형 2^N × 2^N 2의 지수N을 입력받아서 배열 초기화,
 * 사각형 사이즈가 2*2 이면 -> 왼쪽위, 오른쪽위, 왼쪽아래, 오른쪽아래 순서로 Z 모양으로 순회
 * 2*2보다 크다면? ex)4*4 라면 4등분해서 왼쪽위 오른쪽위 왼쪽아래 오른쪽아래 순서로 재귀처리 후 위의 로직 반복
 * r c 가 3 1 이면 row 3 col 1 이면 실제 2차원 배열에서는 +1 칸임 4번째행에 2번째 열에 있는 값에 몇번째로 도달하는가?
 * 
 * 1. N r c를 입력받는다.
 * 2. 현재 정사각형의 좌상단 좌표: (row, col) 이라고 할 떄,
 * 	2-1. 위쪽 영역: row ~ row+half-1
 * 	2-2. 아래쪽 영역: row+half ~ row+size-1
 * 	2-3. 왼쪽 영역: col ~ col+half-1
 * 	2-4. 오른쪽 영역: col+half ~ col+size-1
 * 
 * 배열을 실제로 만들지 말고 이동을 추적해볼까
 *
 */
public class Main {
	
	static int N, r, c;
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int size = 1<<N; // 2^N == Math.pow(N,2)
		
		dfs(0,0,size);
	}
    
	private static void dfs(int row, int col, int size) {
		 if (size == 1) {
		        System.out.println(cnt);
		        return;
		    }
        
		int half = size/2; 
	    int area = half * half;

	    if (r < row + half && c < col + half) { // 왼쪽 위
	        dfs(row, col, half); // 0 부터 시작
	    } 
	    else if (r < row + half && c >= col + half) { // 오른쪽 위
	        cnt += area;
	        dfs(row, col + half, half);
	    } 
	    else if (r >= row + half && c < col + half) { // 왼쪽 아래
	        cnt += 2 * area;
	        dfs(row + half, col, half);
	    } 
	    else { // 오른쪽 아래
	        cnt += 3 * area;
	        dfs(row + half, col + half, half);
	    }
	}
}
