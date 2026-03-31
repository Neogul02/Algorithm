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
		int sum = 0;
		for(int i=1; i<=N; i++){
			sum += i;
		}

		System.out.print(sum);
	}
}