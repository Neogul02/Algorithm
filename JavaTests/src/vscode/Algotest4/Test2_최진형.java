import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 고대금고의 비밀해제 20점
 * @author SSAFY
 *
 * 목표 마력 수치 S를 청확히 맞춰야함
 */
public class Test2_최진형{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N; // 마력석의 갯수
	static int S;

	static boolean[] visited;

 	static int[] arr;
 	
 	static int answer;
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		
		st = new StringTokenizer(br.readLine().trim()," ");
		
		arr = new int[N];
		for(int i =0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		

		visited = new boolean[N];
		subset(0);
		
		System.out.println(answer);
		
	}
	
	public static void subset(int depth) {
		if(depth == N) {
//			System.out.println(Arrays.toString(visited));
			
			int temp=0;
			int falseCnt = 0;
			for(int i=0; i<N; i++) {
				if(visited[i] == true) {
					temp += arr[i];
				}
				else {
					falseCnt++;
				}
			}
			if(falseCnt == N) return; // 공집합인 경우는 제외
			if(temp == S) answer ++;
			
			return;
		}
		
			visited[depth] = true;
			subset(depth+1);
			
			
			visited[depth] = false;
			subset(depth +1);
			
	}	
}



