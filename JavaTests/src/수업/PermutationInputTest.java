package 수업;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author SSAFY
 * Permutation 순열 nPr <- 구현
 * n과 r을 입력받음
 */
public class PermutationInputTest {
	
	static boolean[] isSelected;
	static int numbers[], input[], N, R;
	
	static int callCnt=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		R = sc.nextInt();
		
		input = new int[N];
		isSelected = new boolean[N]; // 인덱스에 해당하는 수가 선택되었는지의 여부
		numbers = new int[R];
		
		for(int i=0; i<N; i++) {
			input[i] = sc.nextInt();
		}
		permutation(0);
		System.out.println("callCnt: "+callCnt);

	}
	public static void permutation(int depth) {
		// 종료조건 - 기저조건
		if(depth == R) { // R 개를 뽑는거니까 (자릿수)
			++callCnt;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		// 실행부분
		for(int i=0; i<N; i++) {
			if(isSelected[i]==true) continue;
			
			isSelected[i] = true; // 처음 쓰이는 수인가? - 사용처리
			numbers[depth] = input[i];
			
			permutation(depth+1);
			
			isSelected[i] = false; //
		}
		
	}

}
