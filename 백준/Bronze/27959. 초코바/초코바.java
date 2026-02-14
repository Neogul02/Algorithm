import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main (String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim()," ");
		StringBuilder sb = new StringBuilder();
		
		sb.append(Integer.parseInt(st.nextToken())*100 >= Integer.parseInt(st.nextToken())?"Yes":"No");
		System.out.print(sb);
    }
}