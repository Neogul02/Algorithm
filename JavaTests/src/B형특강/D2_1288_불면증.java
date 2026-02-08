package B형특강;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1288. 새로운 불면증 치료법 [D2]
 * @author neogul02
 */
public class D2_1288_불면증 {
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int cnt;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine().trim());
			int result = countSheep();
			
			sb.append("#").append(tc).append(" ")
			.append(result).append("\n");
		}
		System.out.print(sb);
	}

	public static int countSheep() {
		int visited = 0; // 기록
		int cnt = 0;
		
		int k=1;
		
		while(true) {
			if(visited==(1<<10)-1) break;
			cnt = N * k;
			int temp = cnt;
			
			while (temp > 0) {
			    int digit = temp % 10; // 1. 나머지 연산으로 맨 끝자리 획득
			    visited |= (1 << digit);
			    temp /= 10;            // 2. 몫 연산으로 맨 끝자리 제거
			}
			k++;
		}
		return cnt;
	}
}
