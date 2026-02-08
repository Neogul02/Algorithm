package B형특강;

import java.io.*;
import java.util.*;

/**
 * 1230. 암호문3
 */

public class D3_1230_암호문3 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            ArrayList<String> list = new ArrayList<>();

            int N = Integer.parseInt(br.readLine().trim()); // 원본 암호문 길이
            
            st = new StringTokenizer(br.readLine().trim()); // 원본 암호문
            for (int i = 0; i < N; i++) {
                list.add(st.nextToken());
            }

            int order_N = Integer.parseInt(br.readLine().trim()); // 명령어 개수
            
            // 명령어 줄 읽기
            st = new StringTokenizer(br.readLine().trim()); 
            
            for (int k = 0; k < order_N; k++) {
                // 토큰이 떨어지면 다음 줄 읽어서 채워넣기
                while (!st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine().trim());
                }

                char cmd = st.nextToken().charAt(0);

                if (cmd == 'I') {
                    int x = Integer.parseInt(st.nextToken()); // 위치
                    int y = Integer.parseInt(st.nextToken()); // 개수
                    // 삽입은 x번째부터 순서대로 넣어야 하므로 x를 증가시키며 add
                    for (int j = 0; j < y; j++) {
                        list.add(x++, st.nextToken());
                    }
                } else if (cmd == 'D') {
                    int x = Integer.parseInt(st.nextToken()); // 위치
                    int y = Integer.parseInt(st.nextToken()); // 개수
                    // 삭제는 해당 위치를 계속 지우면 뒤에 있는게 당겨져 옴
                    for (int j = 0; j < y; j++) {
                        list.remove(x);
                    }
                } else if (cmd == 'A') {
                    // A는 x(위치)가 없습니다! y(개수)만 받음
                    int y = Integer.parseInt(st.nextToken()); // 개수
                    for (int j = 0; j < y; j++) {
                        list.add(st.nextToken()); // 맨 뒤에 추가
                    }
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