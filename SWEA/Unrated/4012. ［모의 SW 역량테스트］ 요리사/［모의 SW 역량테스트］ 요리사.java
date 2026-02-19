import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 4012. 요리사
 * @author neogul02
 * 
 * 1. 테스트케이스 T를 입력받는다.
 * 2. N을 입력받는다 (N*N) 배열
 * 3. 각 요리시너지의 값을 N * N 만큼 입력받는다.
 * 4. N개 중 N/2개를 선택한다, isSelected가 true인 팀이 A팀, false인 팀이 B팀으로 조합,
 * 5. 각 팀의 요리 시너지의 합을 계산해서 최소값을 갱신한다.
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int[][] bobs;
	static boolean[] isSelected;
	
	static int N;
	static int minDiff;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
            input();
            minDiff = Integer.MAX_VALUE;  // 초기화!
            solve(0, 0);
            sb.append("#").append(tc).append(" ").append(minDiff).append("\n");
        }
		System.out.print(sb);	
	}
	
	private static void input() throws IOException{
		// 식재료 개수 N개를 입력받는다
		N = Integer.parseInt(br.readLine().trim());
		
		bobs = new int[N][N];
		isSelected = new boolean[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine().trim()," ");
			for(int j=0; j<N; j++) {
				bobs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	/**
     * 조합으로 N개 중 N/2개 선택
     * 
     * @param idx 현재 검사 중인 식재료 번호
     * @param cnt 현재까지 선택한 개수
     */
	private static void solve(int idx, int cnt) {
		
		// 종료조건 식재료를 N/2개 선택했을때 검사
		if(cnt == N/2) {
			// A팀, B팀으로 나눠서 요리의 시너지 계산
			diff();
			return;
		}
		
		if (idx == N) return;
		
		
		// 식재료를 선택하는경우
		isSelected[idx] = true;
		solve(idx+1, cnt+1);
		
		// 식재료를 선택 안하는경우
		isSelected[idx] = false;
		solve(idx+1, cnt);
	}

	/**
	 *  A팀, B팀 시너지 차이 계산 및 최소값 갱신
	 */
	private static void diff() {
		
		int sumA=0, sumB=0;
		
		// A팀 시너지 합 (isSelected = true)
		for (int i = 0; i < N; i++) {
	        if (isSelected[i]==true) continue;  
	        for (int j = 0; j < N; j++) {
	            if (isSelected[j]==true) continue; 
	            sumA += bobs[i][j];
	        }
	    }
	    
	    // B팀 시너지 합 (isSelected = false)
	    for (int i = 0; i < N; i++) {
	        if (isSelected[i]==false) continue; 
	        for (int j = 0; j < N; j++) {
	            if (isSelected[j]==false) continue;  
	            sumB += bobs[i][j];
	        }
	    }
	    // 차이 최소값 갱신
	    int diff = Math.abs(sumA - sumB);
	    minDiff = Math.min(minDiff, diff);
	}
}