import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 최종적으로 완료된 처음 10개의 항만 출력하면 됨,
 * 1. 보자마자 testCase 10번  돌려버리기
 *  1-1.입력을 받는데 StringTokenizer로 공백기준으로 입력 받아야함 1,2,3,4번째줄까지
 * 	1-2.기존 List사이사이에 넣어야하니까 LinkedList 로 head부분만 연결 연결 해주면 될거같음
 * 	1-3.명령어 처리부분에서 공백기준으로 토큰을 가져오는데 명령어 종류 'I'를 읽고 쓰진 않음
 *  1-4.start 부분과 명령어 갯수 부분으로 List.add(start++, token); 반복문
 *  1-5.출력을 sb.append로 모아서 출력
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for(int tc=1; tc<=10; tc++) {
			LinkedList<String> list = new LinkedList<>(); // testCase 마다 초기화
			
			int N = Integer.parseInt(br.readLine().trim()); // 첫 번째 줄: 원본 암호문의 길이
			
			st = new StringTokenizer(br.readLine().trim()); // 두 번재 줄 : 원본 암호문
			
			for(int i=0; i<N; i++) {
				list.add(st.nextToken());
			}
			
			int order_N = Integer.parseInt(br.readLine().trim()); // 세 번째 줄: 명령어의 개수
			
			st = new StringTokenizer("");
			
			for (int k = 0; k < order_N; k++) {
				while(!st.hasMoreTokens()) {
					String str = br.readLine();
					if(str == null) break;
					st = new StringTokenizer(str.trim());
				}
                // (1) 명령어 종류 읽기 ('I')
                String cmd = st.nextToken(); // 쓸모는 없지만 구분용

                // (2) 앞에서부터 몇 번째 위치인지 (x)
                int startIdx = Integer.parseInt(st.nextToken());

                // (3) 몇 개를 넣을 건지 (y)
                int count = Integer.parseInt(st.nextToken());
                
                // (4) y개만큼 반복해서 넣기
                for (int j = 0; j < count; j++) {
                    // startIdx 위치에 넣고, startIdx를 1 증가시키기
                	while(!st.hasMoreTokens()) {
						st = new StringTokenizer(br.readLine().trim());
					}
                    list.add(startIdx++, st.nextToken());
                }
            }
			
			sb.append("#").append(tc);
            for (int i = 0; i < 10; i++) {
                sb.append(" ").append(list.get(i));
            }
            sb.append("\n");
		}
		System.out.println(sb);
	}

}
