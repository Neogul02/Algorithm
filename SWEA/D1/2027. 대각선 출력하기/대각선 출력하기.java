import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if(i==j) sb.append("#");
				else sb.append("+");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}