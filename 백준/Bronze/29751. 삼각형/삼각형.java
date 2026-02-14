import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main (String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim()," ");
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("%.1f",(double)(Integer.parseInt(st.nextToken())*Integer.parseInt(st.nextToken()))/2));
		System.out.print(sb);
    }
}