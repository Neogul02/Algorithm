package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author neogul02
 * 영식 요금제 30초마다 10원씩,
 * 민식 요금제 60초마다 15원씩, 
 * (통화한 시간 // 30 * {10}) + (통화한 시간 % 30)!=0 {+10}
 * (통화한 시간 // 60 * {10}) + {통화한 시간 % 60)!=0 {+15}
 * 
 * + 나눠지기는 하는데 나눴을때 나머지가 없다? 10이나 15의 배수임 > 1번 더 더해줘야함 
 */
public class BOJ_1268 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());		
		
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		
		int Ymoney = 0;
		int Mmoney = 0;
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			Ymoney += ((int)(arr[i]/30))*10 + ((arr[i]%30)!=0 ? 10 : 0) ;
			Mmoney += ((int)(arr[i]/60))*15 + ((arr[i]%60)!=0 ? 15 : 0) ;
			
			if(((int)(arr[i]/30))!=0 && arr[i]%30 == 0){
				Ymoney+=10;
			}
			if(((int)(arr[i]/60))!=0 && arr[i]%60 == 0){
				Mmoney+=15;
			}
		}
		
		if(Ymoney>Mmoney) {
			sb.append("M ").append(Mmoney);
		}else if(Mmoney>Ymoney) {
			sb.append("Y ").append(Ymoney);
		}else{
			sb.append("Y M ").append(Ymoney);
		}
		System.out.println(sb);
	}
}
