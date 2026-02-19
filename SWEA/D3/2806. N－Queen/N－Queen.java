import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2806.N-Queen
 * @author neogul02
 * 
 * 백트래킹 dfs로 가지치기를 통해 퀸의 위치를 N*N 배열에서 퀸 N개를 배치 할 수 있는 경우의 수를 구하기
 * 
 * 1. testCase T 를 입력받는다.
 * 2. N을 입력받는다.
 * 3. 0번 행부터 시작해서 각 행에 퀸을 하나씩 배치해본다
 * 4. 새롭게 배치할 때 이전 퀸들과 충돌하는지 확인한다 - 같은 행, 열, 대각선 4방향
 *  4-1. 각 두 점에서 행끼리 뺀값이랑 열끼리 뺀값이 같으면 대각선
 * 5. N개를 모두 배치하는게 성공했다? 경우의수 cnt ++;
 */
public class Solution {

	static int N;
	static int[] cols;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 T
		int T = Integer.parseInt(br.readLine().trim()); 
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			
			cols = new int[N];
			answer = 0;
			dfs(0); // 0번째 행부터 시작 ->
			
			sb.append('#').append(tc).append(' ')
			.append(answer).append('\n');
		}
		System.out.print(sb);
	}

	public static void dfs(int row) { 
		// 기저 조건
		if(row == N) { // 마지막 N행까지 퀸이 배치가 됐는가? 
			answer ++; // 경우의 수 +1
			return;
		}
		
		// 수행 로직
		for(int c=0; c<N; c++) {
			if(isVaild(row,c)==true) {
				cols[row] = c; // row 번째 행의 col 번째 열에 배치
				dfs(row+1); // 다음 행으로 이동
			}
		}
	}

	public static boolean isVaild(int r, int c) {
		
		for(int i =0; i<r; i++) {
			// 같은 열에 있으면 false
			if(cols[i]==c) return false;
			
			// 대각선에 위치하면 false
			// 행의 차이랑 열의 차이가 같으면 대각선임
			if(Math.abs(r-i) == Math.abs(c-cols[i])) return false;
		}
		return true;
	}
}