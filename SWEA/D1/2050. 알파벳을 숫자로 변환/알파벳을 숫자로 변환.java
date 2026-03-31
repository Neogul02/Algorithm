import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  
	public static void main(String[] args) throws IOException {
		char[] inputAlphabet = br.readLine().trim().toCharArray();
		
		for(int i=0; i<inputAlphabet.length; i++){
			int temp = inputAlphabet[i];
			sb.append(temp-64).append(" ");
		}
		System.out.print(sb);
	}
}