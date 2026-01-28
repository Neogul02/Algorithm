import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=0; tc<T; tc++) {
			
			String target = sc.next(); // 타겟 문자열
			char current = '0';
			int cnt=0;
			for(int j=0; j<target.length(); j++) {
				if(target.charAt(j)!=current) {
					cnt++;
					if(current=='0') {
						current='1';
					}else{
						current='0';
					};
				}
			}
			System.out.println(String.format("#%d %d",tc+1 ,cnt));
		}
	}
}