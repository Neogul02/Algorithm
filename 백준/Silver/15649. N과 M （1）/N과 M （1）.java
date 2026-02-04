import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 15649. N과M (1)
 * N P M -> 순열 구현
 * N-R+1
 */
public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();;
	
	static int N, M;
	static int[] arr;
	static boolean[] isSelected; // 방문 체크

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M]; // M개를 뽑을거니까 크기는 M개
		isSelected = new boolean[N+1];
		
		dfs(0); // 깊이 0부터 시작

		System.out.println(sb);
	}
	
	public static void dfs(int depth) {
		// 1. 기저조건, 마지막 재귀 리턴값 -> M개를 다 골랐으면 출력하고 반환
		if(depth==M) {
			for(int value : arr) {
				sb.append(value).append(" ");
			}
			sb.append("\n");
			return;
		}	
		//2. 반복문 1~N까지 숫자 후보 탐색
		for(int i=1; i<=N; i++) {
			
			// 이미 고른 숫자면 패스
			if (isSelected[i] == true) continue;
			
			// i 번째 원소는 (사용 중)
			isSelected[i] = true;
			arr[depth] = i;
			
			// 다음 깊이로 재귀 호출
			dfs(depth+1);
			
			// 원상복구, i 번째 숫자 반납 (사용완료)
			isSelected[i] = false;
		}
	}
}
