import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1233. 사칙연산 유효성 검사
 * @author neogul02
 * 1. 10개의 테스트 케이스를 처리하기 위해 반복.
 * 2. 각 테스트 케이스마다 N개의 노드 정보를 한 줄씩 입력
 * 3. 별도의 Tree Node 클래스나 배열을 생성하지 앉아도 바로바로 검사 가능할거같음,
 * - StringTokenizer의 hasMoreTokens()를 사용
 * 4. 
 * - 자식이 있는 경우 (내부 노드): 데이터는 반드시 연산자(+, -, *, /)여야 함
 * - 자식이 없는 경우 (리프 노드): 데이터는 반드시 숫자여야 함
 */
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int test_case = 1; test_case <= 10; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int result = 1;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken(); // 노드 숫자
				char data = st.nextToken().charAt(0); 
				
				boolean hasChild = st.hasMoreTokens();

				if (result == 0) continue;

				if (hasChild) { // 자식이 있을때 
					if (data >= '0' && data <= '9') {
						result = 0;
					}
				} else { // 자식이 없을떄
					if (!(data >= '0' && data <= '9')) {
						result = 0;
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}
