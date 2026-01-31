import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuffer sb;
	
	public static void solution(int m, int n) {
		if (m == 0) {
	        System.out.println("0");
	        return;
	    }
		sb = new StringBuffer();
		
		while(m>0) {
			int temp = m%n;
			
			if(temp < 10) sb.append(temp);
			else sb.append((char)('A'+temp-10));
			m = (int) m/n;
		}
		System.out.println(sb.reverse().toString());
		return;

	}
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		solution(m,n);
	}
}
