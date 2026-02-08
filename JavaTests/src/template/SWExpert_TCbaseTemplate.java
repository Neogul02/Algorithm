package template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWExpert_TCbaseTemplate {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
    	br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			
			
			
			
			
			
			sb.append("#").append(tc).append(" ");
		}
		System.out.println(sb);
	}
    
}
