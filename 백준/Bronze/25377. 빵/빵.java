import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main (String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder(); 
		
		int N = Integer.parseInt(br.readLine().trim());

		int minTime = Integer.MAX_VALUE;
		int FalseCnt = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine().trim()," ");
			int A = Integer.parseInt(st.nextToken()); // 현재위치에서 가게까지 가는데 걸리는 시간
			int B = Integer.parseInt(st.nextToken()); // 현재 시점에서 빵이 들어올때까지의 시간
			
			if(A>B) FalseCnt++;
			else if(A<=B) {
				minTime = Math.min(minTime, B);
			}
		}
		sb.append(FalseCnt==N?-1:minTime);
		System.out.print(sb);
    }
}