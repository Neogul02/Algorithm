import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static boolean[] isSelected;
	static int N, M;
	static int[] arr;
	
	static int cnt=0;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		isSelected = new boolean[N+1]; 
		
		dfs(0); // 깊이 0부터 시작
		System.out.println(sb);
	}
	
	public static void dfs(int depth) {
		System.out.println("호출횟수"+(++cnt));
		
		// 종료조건
		if(depth ==  M) {
			for(int v : arr) {
				sb.append(v).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// 수행로직
		for(int i=1; i<=N; i++) {
			if(isSelected[i] == true) continue; // 고른 숫자면 패스 이거 지우면 중복 순열!
			
			isSelected[i] =true; // 선점
			arr[depth] = i;
			
			dfs(depth+1);
			isSelected[i] = false; // 선점 해제
		}
	}
}
