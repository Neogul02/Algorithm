package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 1226. 미로1
 * 1은 벽, 0은 길, 2는 출발점, 3은 도착
 * 
 * 1. 입력 처리
 *  1-1. 10개로 고정되어있어서 입력만 받기
 *  1-2. 공백 없이 붙어있는 문자열 형태 입력받기
 *  1-3. toCharArray로 2차원배열을 쉽게 만들기
 *  1-4. bfs 함수 만들어서 매개변수로 입력받은 2차원 배열 넘겨주기
 * 2. bfs 함수 구현
 *  2-1. 시작과 끝이 고정되어있으므로 좌표인자없이 배열 map 만 전달
 *  2-2. Deque에 처음 위치를 저장해두고 poll해서 조회하고 현 위치 갱신하면 add
 *  2-3. 델타 탐색 배열 dx, dy 에 4번 반복해서 현위치 기준 상하좌우 탐색
 *  ->? 갈림길이면? if 왼쪽과 오른쪽 모두 길이 있다면, 둘 다 큐에 넣어서 각각 위치 탐색을 예약해두기
 *  2-4. 범위체크, 새로운 인덱스가 배열을 넘어가는지? ndx와 ndy 모두 0과 16의 사이여야함
 *  2-5. '3'을 만나면 return 1 못만나면 return 0
 */
public class SWExpert_미로1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static int bfs(char[][] map) {
		
		int[] dx = {-1, 1, 0, 0,};
		int[] dy = {0, 0, -1, 1};
		
		Deque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {1,1});
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int x = current[0];
			int y = current[1];
			
			for(int i=0; i<4; i++) {
				int ndx = x + dx[i];
				int ndy = y + dy[i];
				
				if(ndx>=0 && ndx<16 && ndy>=0 && ndy<16) {
					if(map[ndx][ndy] =='3') {
						return 1;
					}
					if(map[ndx][ndy]=='0') {
						map[ndx][ndy] = '1'; // 현 위치는 벽으로 막기
						queue.add(new int[] {ndx, ndy});
					}
				}
			}
		}				
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		char[][] user_map = new char[16][16];
		
		for(int tc = 1; tc <= 10; tc++) {
			br.readLine();
            for(int i = 0; i < 16; i++) {              
                user_map[i] = br.readLine().trim().toCharArray();
            }
            sb.append("#").append(tc).append(" ").append(bfs(user_map)).append("\n");
		}
		System.out.println(sb);
	}
}
