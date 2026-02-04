import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws IOException {
    	br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());	
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		sb.append(N).append("\n");
		while(N>0) {
			if(N==0|| M>N) break;
			
			sb.append((int)(N/M)).append("\n");
			N = (int)(N/M);			
		}
		System.out.println(sb);
    }
}
