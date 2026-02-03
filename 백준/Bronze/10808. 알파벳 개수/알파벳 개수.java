import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아스키 코드 char 로 계산
 * 'a' = 97
 * 'b' = 98
 * b -'a' = [1]
 * a -'a' = [0]
 */
public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		String S = br.readLine().trim();
		int[] alphaArr = new int[26];
		
		for(int i=0; i<S.length(); i++) {
			char c = S.charAt(i);
			alphaArr[c-'a']+=1;
		}
		for(int i=0; i<26; i++) {
			sb.append(alphaArr[i]).append(" ");
		}
		System.out.println(sb);
	}
}