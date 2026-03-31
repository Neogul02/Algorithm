import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  	static StringBuilder sb = new StringBuilder();
  
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine().trim());
		for(int i=N; i>=0; i--){
			sb.append(i).append(" ");
		}
		System.out.print(sb);
	}
}