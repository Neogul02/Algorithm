import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
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
		
		dfs(size, 0, 0);
	}
	private static void dfs(int size, int row, int col) {
		// 종료조건 찾는 r c 값이라면 
		 if (size == 1) {
		        System.out.println(cnt);
		        return;
		    }
		// 수행로직
		int half = size/2; 
	    int area = half * half;
	    
	    boolean isTop = r<(row+half); // 사각형을 절반으로 나누면 4길이 사각형의 half는 2
	    boolean isLeft = c<(col+half);

	    // z순서로 방문하면 왼쪽 위에서 시작해서 오른쪽 아래가 마지막에 방문
	     if (isTop && isLeft) {                // 왼쪽 위
            dfs(half, row, col);
        } else if (isTop && !isLeft) {        // 오른쪽 위
            cnt += area;
            dfs(half, row, col + half);
        } else if (!isTop && isLeft) {        // 왼쪽 아래
            cnt += 2 * area;
            dfs(half, row + half, col);
        } else {                               // 오른쪽 아래
            cnt += 3 * area;
            dfs(half, row + half, col + half);
        }
	}
}