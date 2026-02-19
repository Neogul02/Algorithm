import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim()); 
		int cnt = 0;
		
		while(N>=0) {
			if(N%5 == 0) {
				cnt += (int) (N / 5);
				System.out.println(cnt);
				break;
			}
			N -= 3;
			cnt ++;
		}
		if(N<0) {
			System.out.println(-1);
		}
	}
}