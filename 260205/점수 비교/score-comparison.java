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
		
		int AMath = Integer.parseInt(st.nextToken());
		int AEng = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine().trim());	
		
		int BMath =Integer.parseInt(st.nextToken());
		int BEng = Integer.parseInt(st.nextToken());
		
		if(AMath>BMath && AEng>BEng) sb.append(1);
		else sb.append(0);
		
		System.out.println(sb);
    }  
}
