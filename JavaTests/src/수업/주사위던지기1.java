package 수업;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author neogul02
 * 중복을 허용하지 않으면 순열
 * 중복을 허용하면 중복순열 
 * 000 ~ 666 6^3승 경우의 수
 * 
 *
 *
 * 주사위 던지기 3
 * * nCr n개의 원소 중 r개의 원소를 가지는 조합을 생성하자!
 * input[] n개의 원소를 가지고 있는 배열
 * numbers[] r개의 크기의 배열, 조합이 저장될 배열
 * 
 * 끝은 고정인데 반복문의 시작이 바뀌기 때문에 start 매개변수가 필요할수도?
 * 
 * 112, 121, 211 을 모두 중복으로 보겠다
 */
public class 주사위던지기1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	

	static boolean isSelected[];
	static int numbers[], N, totalCnt; // 뽑았던 수들을 기억하는 배열
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine().trim());
		
		int mode = Integer.parseInt(st.nextToken());
		
		N = Integer.parseInt(st.nextToken()); // N<=6
		totalCnt = 0;
		
		numbers = new int[N]; // N 크기로 만들어 두기 최대 ~ 6자리 6^6이라 
		
		switch(mode) {
			case 1: // 중복 순열
				dice1(0);
				break;
			case 2: //순열
				isSelected = new boolean[6+1]; // 인덱스 : 주사위 눈의 수 (N+1)
				dice2(0);
				// dice2(N) 하면 6번 던져와~ 하는거고 이러면 depth-1 하면서  기저에서 0이면 종료
				break;
			case 3: //중복조합
				isSelected = new boolean[6+1]; // 인덱스 : 주사위 눈의 수 (N+1)
				dice3(0,1);
				// dice2(N) 하면 6번 던져와~ 하는거고 이러면 depth-1 하면서  기저에서 0이면 종료
				break;

			case 4: //조합
				isSelected = new boolean[6+1]; // 인덱스 : 주사위 눈의 수 (N+1)
				dice4(0,1);
				// dice2(N) 하면 6번 던져와~ 하는거고 이러면 depth-1 하면서  기저에서 0이면 종료
				break;

				
			default:
				break;
		}
		System.out.println("총 경우의 수: "+ totalCnt);
		System.out.println(sb);
		
	}
	
	public static void dice1(int depth) { // 6!
		// 종료조건 - 기저조건
		if(depth == N) {
			++totalCnt;
			for(int num : numbers) sb.append(num).append(" ");
			sb.append("\n");
			return;
		}
		
		// 실행부분
		for(int i=1; i<=6; i++) {
			numbers[depth] = i;
			dice1(depth+1);
			
		}
		
	}
	
	public static void dice2(int depth) {
		// 종료조건 - 기저조건
		if(depth == N) {
			++totalCnt;
			for(int num : numbers) sb.append(num).append(" ");
			sb.append("\n");
			return;
		}
		
		// 실행부분
		for(int i=1; i<=6; i++) {
			if(isSelected[i]==true) continue;
			
			isSelected[i] = true; // 처음 쓰이는 수인가? - 사용처리
			numbers[depth] = i;
			
			dice2(depth+1);
			
			isSelected[i] = false; //
		}
		
	}
	
	// 중복 조합 H
	// 뽑는 순서 고려 안함, 한번뽑은 원소 다시 뽑기 가능
	public static void dice3(int cnt, int start) { 
		if(cnt == N) {
			++totalCnt;
			for(int num : numbers) sb.append(num).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i =start; i<=6; i++){ // 주사위 눈이니까 6
			numbers[cnt] = i;
			dice3(cnt+1, i); // 중복된 선택도 가능이니까 
			
			
		}
	}
	
	// 조합 C cnt= 몇번째 주사위? start = 시작
	// 뽑는 순서 고려 안함 , 한 번 뽑은 원소 다시 못뽑음
	public static void dice4(int cnt, int start) { 
		
		if(cnt == N) {
			++totalCnt;
			for(int num : numbers) sb.append(num).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i =start; i<=6; i++){ // 주사위 눈이니까 6
			numbers[cnt] = i;
			dice4(cnt+1, i+1); // 내가 선택한 수의 다음으로
			
			
		}
			
	}



}
