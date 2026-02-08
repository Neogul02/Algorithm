package codeTests;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 10번 루프 안에서 동작 ->
 * 1. 테스트 케이스 번호 입력받기 : int T
 * 2. 8개의 숫자를 입력받아 Deque에 넣어두기 : numQ
 * 3. 
 * 4. 사이클 안에서 1부터 5까지 굴리기
 * 5. 출력하기
 */ 
public class Password {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static Deque<Integer> deque = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for(int tc=1; tc<=10; tc++) {
			br.readLine().trim();
			st = new StringTokenizer(br.readLine().trim());
			
			
			for(int cnt=0; cnt<8; cnt++) {
				deque.add(Integer.parseInt(st.nextToken()));
			}
			sb.append("#"+tc+" ");
			Solution();
			deque.clear();
			
		}
		
		System.out.println(sb);
		
	}
	public static void Solution() {
		boolean isPasswordOk = true;
		while(isPasswordOk) {
			for(int i=1; i<=5; i++) {
				Integer temp = deque.poll();
				deque.add(temp-i);
				
				if(deque.getLast()<=0) {
					deque.pollLast();
					deque.add(0);
					isPasswordOk = false;
					break;
				}
			}
		}
		for(int j=0; j<8; j++) {
			sb.append(deque.poll()+" ");
		}
		sb.append("\n");
		
		
	}
}
