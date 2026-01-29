import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 1. 테스트 케이스 개수 입력받기
 * 2. 카드 개수 입력받기
 * 3. 일단 배열에 다 담기
 * 4. 중간점을 잡는 포인터 ? 처음과 중간지점을 잡는다.0, mid
 * 5. 번갈아가면서 하나씩 찍기 0
 */
public class Solution {
	 public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 1. 테스트 케이스  입력받기 : T
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			// 2. 카드 개수 입력받기 : cardCnt
			int cardCnt = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			String[] cards = new String[cardCnt];

			// 3. 일단 배열에 다 담기
			for (int i = 0; i < cardCnt; i++) {
                cards[i] = st.nextToken();
            }

            int p1 = 0;   // 4. 앞쪽 덱 포인터
            int p2 = 0;
            if(cardCnt %2 == 0) {
            	p2 = cardCnt / 2; // 뒤쪽 덱 포인터 (짝수)
            }else {
            	p2 = cardCnt / 2 + 1; // 뒤쪽 덱 포인터 (홀수일때는 하나 더 뒤로)
            }
            
            sb.append("#").append(tc).append(" ");

            // 5. 번갈아 가면서 찍기
            for (int i = 0; i < cardCnt; i++) {
                if (i % 2 == 0) {
                    sb.append(cards[p1]).append(" ");
                    p1++;
                } 
                else {
                    sb.append(cards[p2]).append(" ");
                    p2++;
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
	}
}