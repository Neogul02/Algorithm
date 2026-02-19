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
public class SWEA_1486_장훈이의높은선반_dfs {
	
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
			dfs(0,0);
			
			sb.append("#").append(tc).append(" ")
			.append(minHeight-B).append("\n");
		}
		System.out.print(sb);
	}
	
	
	public static void dfs(int idx, int sum) {
	    // 최적해 발견 시 즉시 종료
		if(sum >= B) {
	        minHeight = Math.min(minHeight, sum);
	        return;
	    }
	    
	    // 끝까지 도달
	    if(idx == N) return;
	    
	    // 이미 더 좋은 답이 있으면 중단
	    if(sum + NArr[idx] >= minHeight) return;
	    
	    // 포함
	    dfs(idx + 1, sum + NArr[idx]);
	    
	    // 미포함
	    dfs(idx + 1, sum);
	}
	
}
