import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine().trim());

		for(int i=1; i<=N; i++){
			for(int j=1; j<=N; j++){
				if(i*j == N){
					sb.append(i).append(" ");
					break;
				}
			}
		}
		System.out.print(sb);
	}
}