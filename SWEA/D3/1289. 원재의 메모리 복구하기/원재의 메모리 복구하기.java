import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<T; tc++) {
			
			String target = br.readLine(); // 타겟 문자열
			char current = '0';
			int cnt=0;
			for(int j=0; j<target.length(); j++) {
				if(target.charAt(j)!=current) {
					cnt++;
					current = (current=='0')?(current='1'):(current='0');
				}
			}
			System.out.println(String.format("#%d %d",tc+1 ,cnt));
		}
	}
}
