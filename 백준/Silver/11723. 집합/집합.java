import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 11723. 집합
 * @author neogul02
 * 
 * 1. 연산의 수 M을 입력받기.
 * f1. add x -> x를 추가
 * f2. remove x -> x를 제거
 * f3. check x -> x가 있으면 1, 없으면 0 print
 * f4. toggle x -> x가 있으면 remove, 없으면 add
 * f5. all -> {1,2...20} 으로 통편집 
 * f6. empty -> {} 초기화
 * 
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	// check[x] == true면 x가 집합에 있는 것, false면 없는 것
	static boolean[] S; 
	
	static String method;
	static int x;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine().trim());
		
		S = new boolean[21]; // 기본값은 false (공집합 상태)
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine()," ");
			method = st.nextToken();
			
			// all, empty는 뒤에 숫자가 안 옴
			if(st.hasMoreTokens()) {
				x = Integer.parseInt(st.nextToken());
			}
			order();
		}
		System.out.println(sb);
	}
	
	public static void order() {
		switch (method) {
		case "add": {
			// 있으면 무시, 없으면 추가인데 boolean은 true 덮어쓰면 끝이라 조건문 필요 없음
			S[x] = true; 
			break;
		}
		case "remove": {
			// 있어도 false, 없어도 false 덮어쓰면 끝
			S[x] = false; 
			break;
		}
		case "check": {
			if(S[x]) sb.append(1).append("\n");
			else sb.append(0).append("\n");
			break;
		}
		case "toggle": {
			// true면 false로, false면 true로 (Not 연산)
			S[x] = !S[x]; 
			break;
		}
		case "all": {
			// 1~20번을 모두 true로
			Arrays.fill(S, true); 
			break;
		}
		case "empty": {
			// 1~20번을 모두 false로
			Arrays.fill(S, false);
			break;
		}
		}
	}
}