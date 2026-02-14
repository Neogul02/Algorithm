import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 6782. 현주가 좋아하는 제곱근 놀이
 * @author neogul02
 * 
 * - N은 N+1 로 치환 가능
 * - 루트를 씌운값이 정수라면 루트를 씌울 수 있음
 * - 최종적으로 N을 2로 만드는게 목표
 * 
 * N+1을 했는데 루트를 씌운다고 바로 정수가 되는가? -> x
 * 바로 다음 제곱근으로 넘어가려면?? 더하다가 정수가 되면 루트를 씌우며 2까지 줄어들게 하는 로직
 */
public class Solution {
	
	static long N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			N = Long.parseLong(br.readLine().trim()); 
			int result = solve();
			
			sb.append('#').append(tc).append(' ')
			.append(result).append('\n');
		}
		System.out.print(sb);
		
	}

	private static int solve() {
		int cnt = 0;

		while(true) {
			if(N==2) return cnt;
			
			long root = (long) Math.sqrt(N);
			
			// 제곱근을 씌웠을때 정수인가? 그렇다면 루트를 씌울 수 있다.
			if (root * root == N) {
				N = root;
			    cnt++;
			}else {
				// 다음 제곱근 수로 점프 할 수 있다면? ++ 연산을 줄일 수 있을것같은데
				long next = (root + 1) * (root + 1); // 4, 9, 16, 25 ..
                cnt += (next - N);
				N = next;
				
			}	
		}
		
	}
}
