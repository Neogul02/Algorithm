import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine().trim());

		int num = 1;
		sb.append(num).append(" ");
		
		for(int i=0; i<N; i++){
			num = num<<1;
			sb.append(num).append(" ");
		}
		System.out.print(sb);
	}
}