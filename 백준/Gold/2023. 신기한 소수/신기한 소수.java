import java.io.*;

/**
 * 2023. 신기한 소수
 * @author neogul02
 * 
 * 모든 자릿수가 소수여야하니까 
 * 1의 자리는 무조건 소수여야함 = 최소조건
 * 10.. 100.. 1000 자리수를 만든다. => 기존 수*10 + 새로운 수 = 이게 소수가 아니면 돌아가기 backTracking 
 * 
 * 1. N자리 수를 입력받는다 / 4 = 4자리 숫자 1000~9999
 * 2. 백트래킹 dfs 를 생각하고 구현하는데 첫번째 1의자리숫자가 소수인경우로 시작한다.
 *  2-1. 종료조건 -> N자리수까지 생성이 완료되면 종료 sb에 저장해두고 한번에 출력하기
 *  2-2. 수행로직 -> 새로운 수 만들기, 근데 새로운 수가 소수일때만 다음 재귀 호출하기
 *  
 * 3. 소수 판별 메서드 -> 받은 int형 정수가 소수인가? true / false 반환
 */

public class Main {

	static StringBuilder sb = new StringBuilder();
	
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 1. N자리 수를 입력받는다
		
		dfs(2, 1);
		dfs(3, 1);
		dfs(5, 1);
		dfs(7, 1);
		
		System.out.print(sb);
	}
	
	//2. 백트래킹 dfs 를 생각하고 구현
	public static void dfs(int number, int depth) {  
		// 종료조건 -> N자리수까지 생성이 완료되면 종료, sb에 저장해두고 한번에 출력하기
		if(depth == N ) {
			sb.append(number+"\n");
			return;
		}
		
		// 2-2. 수행로직 -> 새로운 수 만들기, 근데 새로운 수가 소수일때만 다음 재귀 호출하기
		for(int i=1; i<10; i++) {
			int nextNumber = number*10+i;
			if(isPrime(nextNumber)) { // 새로 만든 수가 소수인가? yes -> 다음재귀 호출
				dfs(nextNumber,depth+1);
			}
		}
		
	}
	
	// 3. 소수 판별 메서드 -> 받은 int형 정수가 소수인가?
	public static boolean isPrime(int num) {
		if (num < 2) return false; // 0 이랑 1은 소수가 아님
		
		for (int i = 2; i <= Math.sqrt(num); i++) { // 루트 num 이랑 같음
			if (num % i == 0) return false;
		}
		return true;
	}
}
