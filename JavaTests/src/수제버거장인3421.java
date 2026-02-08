import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 3421. 수제 버거 장인 
 * @author neogul02
 * 
 * 1. 테스트 케이스의 개수 입력받기
 * 2. 공백기준으로 N, M 입력받기 
 * 3. aArr와 bArr 로 각각 조합 요소 저장해두기
 * 4. 가능한 ans 변수 초기화하고 1부터 N까지의 부분집합을 구하기
 * 
 */
public class 수제버거장인3421 {
		static BufferedReader br;
		static StringTokenizer st;
		static StringBuilder sb = new StringBuilder();	
		
		static int N,M;
		static boolean[] isSelected;
		
		static int[] aArr;
		static int[] bArr;
		
		static int ans; // 정답 개수 저장
		
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++){
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			aArr = new int[M];
			bArr = new int[M];
			
			isSelected = new boolean[N+1]; // 0번 인덱스 버리기
			
			for(int m=0; m<M; m++) {
				st = new StringTokenizer(br.readLine().trim());
				aArr[m] = Integer.parseInt(st.nextToken());
				bArr[m] = Integer.parseInt(st.nextToken());
				
			}
			ans=0;
			generateSubset(1);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}

	public static void generateSubset(int depth) {
		// 기저 조건
		if(depth == N+1) {
			if(check()) {
				ans++;
			}
			return;
		}
		// 수행
		isSelected[depth] = true;
		generateSubset(depth+1);
		
		isSelected[depth] = false;
		generateSubset(depth+1);
	}
	public static boolean check() {
		
		for(int i=0; i<M; i++) {
			int a = aArr[i];
			int b = bArr[i];
			
			if(isSelected[a] && isSelected[b]) {
				return false;
			}
		}	
		return true;
	}
}