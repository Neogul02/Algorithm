import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 10번 루프 안에서 동작 ->
 * 1. Main에서는 테스트 케이스 번호 입력받기 : int T
 * 2. 8개의 숫자를 입력받아 static Deque에 넣어두기 : deque
 * 3. Solution으로 로직 분리,사이클 안에서 1부터 5까지 굴리기 덱의 앞에서부터  뽑기
 * 	3-1. 만약 무한루프안에서 마지막 원소가 0보다 작거나 같으면 루프 탈출
 * 4. StringBuilder sb 에 append 해둔 문자열 출력
 */ 
public class Solution {
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
