import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++){
			st = new StringTokenizer(br.readLine().trim(), " ");

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			sb.append("#").append(tc).append(" ")
			.append((int)(A/B)).append(" ").append(A%B)
			.append("\n");
		}
		System.out.print(sb);
	}
}