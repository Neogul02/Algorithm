package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author neogul02
 * 1. 사용자에게 입력을 받는다 br.readLine으로
 * 2. 받은 입력의 첫번째 인덱스를 target char로 분리한다. (모든 String을 소문자나 대문자로 변경)
 * 3. 첫번째 인덱스를 제외한 두번째 인덱스부터 subString 해서 분리
 * 	3-1. 분리한 subString을 한 char씩 2번과 비교 후 cnt++;
 * 4. StringBuilder에 출력 후 Line마다 초기화 setLength(0);
 */
public class BOJ_2387 {
	static BufferedReader br;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while(true) {
			String temp = br.readLine().trim();
			char target = Character.toLowerCase(temp.charAt(0));
			if(target=='#') break;
			
			String content = temp.substring(1).toLowerCase();

			int cnt =0;
			for(int i=0; i<content.length(); i++) {
				if(content.charAt(i)==target) {
					cnt++;
				}
			}
			sb.append(target).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
