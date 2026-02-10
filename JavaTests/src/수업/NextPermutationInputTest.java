package 수업;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author SSAFY
 * Permutation 순열 nPr <- 구현
 * n과 r을 입력받음
 * 
 * nPr은 안되고 n자리만 됨
 */
public class NextPermutationInputTest {
	
	static int input[], N;
	
	static int callCnt=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		input = new int[N];

		for(int i=0; i<N; i++) {
			input[i] = sc.nextInt();
		}
		Arrays.sort(input); // 오름차순 정렬부터 합니다.
	
	}
	
	static boolean NP() { // 현재 순열 기반으로 다음단계 순열을 생성함
		
		// step 1 꼭대기 찾기 = i-1위치인 교환위치 찾기
		int i = N-1; // 가장 오른쪽부터 출발
		while(i>0 && input[i-1] >= input[i]) --i;
		
		// step 2. 꼭대기 체크 -> i-1 교환위치가 있는지? -> 가장 큰 순열인가?
		if(i == 0) return false;
		
		// step 3. i-1이랑 i랑 교환
		int j = N-1;
		while(input[i-1]>=input[j]) --j;
		
		// step 4. i-1 위치랑 j 위치 교환
		swap(i-1,j);
		
		// step 5. 꼭대기부터 맨 뒤까지 오름차순 정렬
		int k = N-1;
		while(i<k) {
			swap(i,k);
			i++; k--;
		}
		
	}

	private static void swap(int i, int j) {
		// TODO Auto-generated method stub
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

}
