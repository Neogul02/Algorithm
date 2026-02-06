import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 8275. 햄스터
 * @author neogul02
 * 
 * 햄스터 케이지 N개, 1,2,.. N
 * 
 * 1. 테스트케이스 T 를 입력받는다
 * 2. T만큼 반복~
 * 3. 입력받기 햄스터 우리 개수 N, 각 우리에 있는 햄스터 마리 수 X 이하, M개의 줄 
 *  3-1. M만큼 반복
 *  3-2. l번째 우리~ r번째 우리에서 햄스터 수를 세면 s마리를 입력받기
 *  3-3. 배열에 l,r,s의 정보를 저장해둔다.
 * --- logic
 * 우리 N개에 0마리부터 X마리까지 넣어본다. dfs depth-> 몇번째 우리인지 결정
 * 4. dfs로 첫번째 우리부터 탐색 
 *  4-1. 종료조건 -> 모든 우리를 다 돌았을때
 *   4-1-1. l부터 r까지 합이 s가 맞는지? 조건 확인 필요
 *  4-2. 수행로직 -> 0마리부터 X마리까지 다 넣어보기
 *   4-2-1. 현재 마리수를 저장해두고 dfs(다음 케이지로);
 * 5. 조건 검사
 *  5-1. l부터 r까지 케이지에 있는 햄스터를 다 더했는데 s인가?
 */
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N,X,M;
	
	
	static int l,r,s;
	static int[][] mArr;
	
	static int[] cages;      // 현재 탐색 중인 햄스터 수
	static int[] resultCages;// 최종 정답
	static int maxHamsters;  // 최대 햄스터 합 저장
	
	public static void main(String[] args) throws IOException {
		
		// 1. 테스트케이스 T 를 입력받는다
		int T = Integer.parseInt(br.readLine().trim());
		
		// 2. T만큼 반복~
		for(int tc=1; tc<=T; tc++) {
			maxHamsters = -1;
			inputTestCases();
			
			cages = new int[N+1];
			dfs(1);
			sb.append("#").append(tc).append(" ");
			if (maxHamsters == -1) {
                sb.append("-1");
            } else {
                for (int i = 1; i <= N; i++) {
                    sb.append(resultCages[i]).append(" ");
                }
            }
			sb.append("\n");
		}
		System.out.println(sb);
	}
	public static void inputTestCases() throws IOException {
		// 3. 햄스터 우리 개수 N, 각 우리에 있는 햄스터 마리 수 X, M개의 줄 입력받기
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 3-1. M만큼 반복
		mArr = new int[M][3];
		for(int m=0; m<M; m++) {
			// 3-2. l번째 우리~ r번째 우리에서 햄스터 수를 세면 s마리를 입력받기
			st = new StringTokenizer(br.readLine().trim());
 			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			// 3-3. 배열에 l,r,s의 정보를 저장해둔다.
			mArr[m] = new int[] {l,r,s};
		}
	}

	public static void dfs(int depth) {
		// 종료 조건
		if(depth == N+1) {
			if(check()) { // 5. l부터 r까지 케이지에 있는 햄스터를 다 더했는데 s인가?
				int currentSum = 0;
	            for (int i = 1; i <= N; i++) currentSum += cages[i];

	            // 햄스터 마리수가 더 많으면 갱신
	            if (currentSum > maxHamsters) {
	                maxHamsters = currentSum;
	                resultCages = cages.clone();
	            }
			}
			return;
		}
		
		// 수행
		for (int i = 0; i <= X; i++) {
	        cages[depth] = i;
	        dfs(depth + 1);
	    }
	}

	public static boolean check() {
	    for (int i = 0; i < M; i++) {
	        int l = mArr[i][0];
	        int r = mArr[i][1];
	        int s = mArr[i][2];
	        int sum = 0;
	        for (int k = l; k <= r; k++) sum += cages[k];
	        if (sum != s) return false;
	    }
	    return true;
	}
}
