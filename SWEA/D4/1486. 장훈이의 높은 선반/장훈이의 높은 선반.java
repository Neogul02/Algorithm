import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N; // 직원 수 
	static int B; // 목표 선반 높이
	
	static int[] NArr; // {1, 3, 3, 5, 6}
	
	static int minHeight;
	
	public static void main(String[] args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 테스트 케이스 갯수 T
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			st = new StringTokenizer(br.readLine().trim(), " ");
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine().trim(), " ");
			NArr = new int[N];
			for(int i=0; i<N; i++) {
				NArr[i] = Integer.parseInt(st.nextToken());
			}
			
			minHeight = Integer.MAX_VALUE;
			
			sb.append("#").append(tc).append(" ")
			.append(subSet()).append("\n");
		}
		System.out.print(sb);
	}

	public static int subSet() {
		// 2^N 승 반복, 부분집합 구하기 , N=5 -> subCnt 32 
		for(int subCnt =1; subCnt < (1<<N); subCnt++) {
			int sum =0;
			
			for (int i = 0; i < N; i++) {
				if((subCnt & (1<<i)) != 0) {
					sum += NArr[i];
					
					// 가지 치기 1: 이미 sum 값이 minHeight보다 높다면 back
					if(sum>=minHeight) break;
				}
			}
			
			if(sum>=B) { // B보다 큰 경우에
				minHeight = Math.min(minHeight, sum); // 가장 작은애가 있는가?
				// 가지치기 2: 선반 높이와 딱 맞는 경우가 있으면 더 볼 필요없음!
				if(minHeight==B) return 0;
			}
			
		}
		return minHeight-B;
	}
}