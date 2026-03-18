import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test3_최진형{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	// 가로 M 세로 N
	static int N, M, K;
	static int[][] map;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int stoneCnt;
 	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			input();
			solve();
		}
		System.out.println(sb);
	}
	
	public static void solve(){
		// 채굴골램위치를 정해야함 최대 K개
		if(stoneCnt==1) {
			sb.append(1);
			return;
		}
		
		// 0인칸에 K개부터 1개씩 줄여가면서 골렘을 배치해서 1이 커버가 되는지를 확인 
		
	}
	
	public static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		stoneCnt =0;
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine().trim());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			map[X][Y] = 1;
			stoneCnt ++;
			
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j]+ " ");
			}
			System.out.println();
		}
		System.out.println("----------------");
	}
}