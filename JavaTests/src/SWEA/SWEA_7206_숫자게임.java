package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * D5 7206. 숫자게임 
 * @author neogul02
 * 
 * 
 * 1이상, 99999이하 수니까 999*99 = 98901 아무리 크게 쪼개도 5자리는 안넘는것같음
 * 5자리 문자열을 쪼개는 경우의 수? 비트 마스킹으로 각 모든 자리의 부분집합을 구하고 집합의 원소마다 dfs를 돌리면서 turn을 저장해두고
 * 가장 MaxTurn을 출력하면 될것같음
 *
 * 출력값인 turn수가 커지려면?? -> 숫자가 커야함 -> 그렇다면 3,4,5개로 쪼개는게 의미있나?
 * 덩어리를 크게 가져가는게 큰 수를 만드는거니까 2+n개로 쪼개는건 없다고 가정하고 풀어보자 -> 안됨 -> 모든 분할을 봐야할듯
 * 
 * 9 9 9 9 9 = 59,049
 * 99 9 99 = 88,209
 * 999 99 = ..
 * 9999 9 = 89,991
 * 
 *  1. 첫줄은 테스트케이스 입력받기 T
 *  2. 숫자를 입력받는다. 
 *   2-1 입력받은 숫자가 1자리 수이면 바로 turn 수는 0 으로 리턴
 *   길이 L이면 자를 수 있는 경계는 (L-1)개니까 경계를 비트 마스킹으로 여러 경우의수로 숫자로 subString
 *   2-2. 특정 숫자의 turn 값은 일정하므로 memo 배열로 저장해두기
 *   2-3. dfs(x) = max( 1 + dfs(다음 값next) ) 로 최대 turn 반환
 *
 */
public class SWEA_7206_숫자게임 {
	
	static int[] memo = new int[100000]; // 0~99999
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Arrays.fill(memo, -1);
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			
			int num = Integer.parseInt(br.readLine().trim());
			
			int result = dfs(num);
			
			sb.append('#').append(tc).append(' ')
			.append(result).append('\n');
		}
		System.out.println(sb);
	
	}

	private static int dfs(int num) {
		// 종료조건 -> 한 자리면 더이상 분할 불가
		if(num<10) return 0;
		// 계산해둔 값이 있으면 가져오기
		if (memo[num] != -1) return memo[num];
		
		String strNum = Integer.toString(num);
		
		int best = 0;
		
		int maxMask = (1<<(strNum.length()-1))-1;
		for(int i=1; i<=maxMask; i++) {
			
			int product = 1;
			int start = 0;

			// i번째 비트가 1이면 i와 i+1 사이에서 자름
            for (int j = 0; j < strNum.length() - 1; j++) {
                if ( (i & (1 << j)) != 0 ) {
                    // s[start -> i]가 하나의 조각 (substring은 end exclusive라 i+1)
                    int part = Integer.parseInt(strNum.substring(start, j + 1));
                    product *= part;
                    start = j + 1; // 다음 조각 시작
                }
            }

            // 마지막 조각 곱하기
            int lastPart = Integer.parseInt(strNum.substring(start, strNum.length()));
            product *= lastPart;
     

            // 다음 상태로 진행 (1턴+)
            int next = (int) product;
            best = Math.max(best, 1 + dfs(next));
        }
        memo[num] = best;
        return best;
		
	}

}
