package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * D4 - 1486. 장훈이의 높은 선반
 * @author neogul02
 * 
 * 서점에 높이가 B인  선반이 있다.
 * 장훈이는 키가 짱 크다. 
 * 장훈이는 선반 위의 물건들을 자유롭게 이용할 수 있다.
 * 서점에 있는 N명의 점원들이 선반에 접근하고자 한다.
 * 각 점원의 키 는 H, 1명이면 높이는 H이고 2명 이상이면 H+H(N명)..
 *
 * 1. 높이가 B 선반 보다 커야함.
 * 2. 1이 만족한다면 B랑 가장 차이가 작은것을 리턴해주면 됨
 * 
 * 1. 테스트케이스 T를 입력받는다 -> T
 * 2. N이랑 B를 입력받는다 -> N, B
 * 3. N 갯수만큼 점원의 키를 입력받아 배열에 저장해둔다. -> NArr[]
 * 4. 최솟값을 구해야하니까 최솟값 변수 하나 초기화 해두고 -> minHeight
 * 5. 부분집합을 구한다. -> subSet();
 * 
 * subset()
 * 모든 직원으로 조합 할 수 있는 부분집합을 구하려고함
 * 1. 비트 마스킹으로 2^N 만큼 순회하고
 * 2. 각 비트 자리수와 & 연산을통해 hit 위치만 sum에 더해둔다
 * 3. minHeight와 비교해서 최솟값을 갱신한다.
 * 4. 문제에서 선반높이 B를 뺀 높이의 차를 출력한다고 했으니 -B를 해준다.
 * 
 * N<= 20 이하니까 2^20..? 100만정도
 */
public class SWEA_1486_장훈이의높은선반 {
	
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
