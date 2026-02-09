import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 4008. 숫자 만들기
 * @author neogul02
* * 1. 입력 및 전역 변수 초기화
 * - 테스트케이스(T)와 숫자의 개수(N)와 연산자 4종류(+, -, *, /)의 개수를 입력받아 배열에 저장.
 * - 수열(numbers)을 입력받음.
 * - 최댓값(max)은 가장 작은 수로, 최솟값(min)은 가장 큰 수로 초기화.
 * 
 * * 2. DFS 탐색 설계
 * - 메서드 시그니처: dfs(int idx, int currentSum)
 * - idx: 현재 계산 대상인 숫자의 인덱스 (1부터 시작, 0번은 초기값)
 * - currentSum: 현재까지 누적된 연산 결과
 * 
 * * 3. 기저 조건
 * - idx == N (모든 숫자를 다 사용했을 때)
 * - 현재 만들어진 최댓값, 최솟값을 갱신하고 return.
 * 
 * * 4. 재귀+백트래킹
 * - 4가지 연산자(+, -, *, /)를 차례로 확인.
 * - 해당 연산자의 잔여 개수가 > 0 이면:
 * 1) 연산자 개수 감소 (사용 처리)
 * 2) 다음 숫자와 연산 수행 후 dfs(idx + 1, newSum) 재귀 호출
 * 3) 연산자 개수 증가 (원상 복구 - 백트래킹)
 * 
 * * 5. 결과 출력 (최댓값 - 최솟값) 출력. 
 */
public class Solution {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int[] yearSanZa = new int[4];
	static int[] numbers;
			
	static int maxValue;
	static int minValue;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			
			// 연산자 개수 yearSanZa[4]
			st = new StringTokenizer(br.readLine().trim(), " ");
			for(int y =0; y<4; y++) {
				yearSanZa[y] = Integer.parseInt(st.nextToken());
			}
			
			// 사용되는 숫자들 numbers[N]
			st = new StringTokenizer(br.readLine().trim(), " ");
			numbers = new int[N];
			for(int n=0; n<N; n++) {
				numbers[n] = Integer.parseInt(st.nextToken());
			}
			
//			System.out.println(Arrays.toString(numbers));
			
			maxValue = Integer.MIN_VALUE;
			minValue = Integer.MAX_VALUE;
			
			dfs(1, numbers[0]);
			
			sb.append(String.format("#%d %d\n", tc, maxValue-minValue));
		}
		System.out.print(sb);
	}

	// idx: 현재 계산 대상인 숫자의 인덱스 (1부터 시작, 0번은 초기값)
	// currentSum: 현재까지 누적된 연산 결과
	public static void dfs(int idx, int sum) {
		// 기저 조건
		if(idx == N) {
			maxValue = Math.max(sum, maxValue);
			minValue = Math.min(sum, minValue);
			return;
		}
		
		// 수행 로직
		if(yearSanZa[0]>0) { // +
			yearSanZa[0]--;
			dfs(idx+1, sum+numbers[idx]);
			yearSanZa[0]++; 
		}
		
		if(yearSanZa[1]>0) { // -
			yearSanZa[1]--;
			dfs(idx+1, sum-numbers[idx]);
			yearSanZa[1]++; 
		}
		if(yearSanZa[2]>0) { // *
			yearSanZa[2]--;
			dfs(idx+1, sum*numbers[idx]);
			yearSanZa[2]++; 
		}
		if(yearSanZa[3]>0) { // -
			yearSanZa[3]--;
			dfs(idx+1, sum/numbers[idx]);
			yearSanZa[3]++; 
		}
	}
}