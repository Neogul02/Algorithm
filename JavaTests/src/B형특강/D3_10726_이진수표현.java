package B형특강;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_10726_이진수표현 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int N,M;
    
    public static void main(String[] args) throws IOException{
    	br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			String result = isBitOn();

			sb.append("#").append(tc).append(" ")
			.append(result).append("\n");
		}
		System.out.println(sb);
	}

	public static String isBitOn() {
		// 입력받은 M에 대하여 
		// 1,2,3 N 번째 비트가 켜져있는가 ? On : Off
		int testInt = ((1<<N) -1); // 1<<N으로 밀면 1 0000 일때 -1 하면 0 1111 됨
		if((M&testInt) == testInt) {
			return "ON";
		}
		return "OFF";
	}
    
}
