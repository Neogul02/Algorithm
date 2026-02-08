package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * BOJ_2178. 미로탐색
 * @author neogul02
 * 1은 이동할 수 있는 칸 0은 이동할 수 없는 칸
 * N M 첫 줄 이후에 미로는 공백없이 입력이 주어짐
 * 1,1에서 출발 -> [0][0]에서 출발 [N-1][M-1] 도착
 * 
 * Main
 * 1. 입력 버퍼 전처리 및 char[][] map 선언  
 * 2. N 과 M 공백 기준 입력받기 -> int
 * 	2-1. 목적지에는 임의로 '2' 값을 넣어서 값 비교하기 쉽게 해보자
 * 3. 공백없이 입력이 들어오니 toCharArray로 만들어서 map[i] 로 바로 넣자
 * 
 * Function - bfs
 * 1. 시작은 [0][0]고정, 도착점만 [N][M]이니까 N이랑 M 값은 파라미터로 주자
 * 2. 배열의 상하좌우를 탐색해야 하니까 dx, dy 델타 배열을 만들자
 * 3. 큐 하나를 만들어서 현재 위치를 저장하고 양갈래길을 대응하자 -> Deque<int[]> queue
 * 	3-1. queue.add(new int[] {0,0,1}); // 0,0 부터 시작하고 좌표별 길이, 얼마나 갔는지를 같이 넣어주자 
 * 4. 큐가 비어있을때까지 무한 반복 -> 목적지까지 무한 반복
 * 	4-1. 큐에서 최신 현재 좌표를 가져오자
 * 	4-2. 어디를 탐색할건지 2.의 델타배열을 4번 도는 반복문으로 nx ny 를 탐색한다
 *   4-2-1. nx랑 ny가 만약 배열의 인덱스를 넘어가면 안된다, nx는 0보다 크거나 같아야하고 N보다 작아야함 ny도
 *   4-2-2. 목적지(nx == N-1 && ny == M-1)에 도착하면-> 도착 
 *   4-2-3. 현 위치는 벽으로 막기
 * 
 */
public class BOJ_2178 {
	static BufferedReader br;
	static StringTokenizer st;
	
	public static int bfs(char[][] map, int N, int M) {
		
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {0,0,1}); // 시작 위치 + 현재 거리
		map[0][0] = '0'; // 시작점 방문처리
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int x = current[0]; int y = current[1]; int cnt = current[2];
			
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(nx>=0 && nx<N && ny>=0 && ny<M) {
					if(nx == N-1 && ny == M-1) {
						return ++cnt; // 도착점까지 더해주기
					}
					if(map[nx][ny]=='1') {
						map[nx][ny] = '0';
						queue.add(new int[] {nx,ny,cnt+1});
					}
				}	
			}
		}
		return 0;
	}

	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M]; 
		
		for(int i=0; i<N; i++) {
			char[] temp = br.readLine().toCharArray();
			map[i] = temp;
		}
		System.out.println(bfs(map,N,M));
	}
}


