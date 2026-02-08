import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 15650. N과 M (2)
 * @author neogul02
 * 
 * 자연수 N과 M이 공백으로 주어지고
 * 1부터 N까지 자연수 중 중복없이 M개를 고른 수열
 * 
 * 1. N과 M을 입력받는다.
 * 2. 1 부터 N까지 자연수 중 "중복없이"->(조합) M개를 고른 수열 구한다.
 * 3. 오름차순으로 정렬한다.
 * 4. 각 수열을 공백으로 구분해서 출력한다.
 * 
 */
public class N과M2 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N,M;
	static int[] numbers;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		// 1. N과 M을 입력받는다.
		inputTestCase();
		
		numbers = new int[M];
		isSelected = new boolean[N+1];
		
		// 2. 1 부터 N까지 자연수 중 "중복없이"->조합 M개를 고른 수열 구한다.
		dfs(0, 1); 
		
		// 4. 각 수열을 공백으로 구분해서 출력한다.
		System.out.println(sb);
	}

	public static void dfs(int depth, int start) {
		// 기저조건 = 자리 수 만큼 뽑았는가? 깊이 == M 개일때
		if(depth==M) {
			for(int number : numbers) {
				sb.append(number).append(" ");
			}
			sb.append("\n");
			return;
		}
		// 수행부분 start부터 N까지 자연수 중에 ~ 
		// 3. 자연스럽게 오름차순 설정 완료 
		for(int i=start; i<=N; i++) {

			numbers[depth] = i; // numbers 배열에 넣기
			
			dfs(depth+1, i+1);
		}

	}

	public static void inputTestCase() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}
}
