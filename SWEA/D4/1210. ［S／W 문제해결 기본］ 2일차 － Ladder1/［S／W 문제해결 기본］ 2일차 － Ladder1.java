import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 1210. Ladder1
 * [IDEA]
 * 사다리는 연속된 ‘1’로 표현된다. 도착 지점은 '2'로 표현됨
 * '2'에 도착하는 출발점의 x좌표를 출력하면 됨
 * 
 * [100][100] 크기 배열인데 [0][0] 에서 '1' 개수만큼 반복해서 '2'를 찾기?
 * [99][99] 라인에서 '2'를 찾아서 역으로 올라가지 말란법이 있을까?
 * 역으로 DFS 탐색하며 올라가다가 [x-1] (왼쪽) 이나 [x+1] (오른쪽)이 '1' (길) 이면 방향 바꾸기?
 * 쭉 가다가 row가 0이되면 종료하고 그때의 x좌표가 정답?
 * 
 * 1. 10번 고정으로 반복한다. 입력은 받아야함
 * 	1-1. 테스트 케이스 T를 입력받는다 < 사용하지는 않음
 * 	1-2. 100*100 배열 map을 입력받는다.
 *   1-2-1. 입력 받을때 2가 입력된 x 좌표를 col에 저장해둔다 < 여기서부터 시작
 *  1-3. while(row > 0) 맨위에 닿을때까지 반복
 *   1-3-1. 왼쪽에 길이 있다면 col 값을 -- 해서 1이 아닐때까지 이동
 *   1-3-2. 오른쪽에 길이 있다면 col 값을 ++ 해서 1이 아닐때까지 이동
 *   1-3-3. 왼쪽 오른쪽에 길이 없다 = 위로 row ++ 1칸 올라가기
 */
public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[][] map; // 100 x 100 맵
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트 케이스 10개 고정
		for(int tc=1; tc<=10; tc++) {
			int T = Integer.parseInt(br.readLine().trim());
			
			map = new int[100][100];
			int col = 0; // 출발 x 좌표, 2의 위치
			int row = 99; // 바닥부터 시작
			
			// 맵 입력 받기
			for(int i=0; i<100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 2) {
						col = j; // 도착점 x 저장
					}
				}
			}
			// 맨 위 row=0 에 닿을 때까지
			while(row > 0) {
				map[row][col] = 0; // 지나온 길은 0으로 지우기
				
				// 왼쪽(col-1)에 길이 있다면? -> 왼쪽 길이 끝날 때까지 이동
				if(col > 0 && map[row][col-1] == 1) {
					while(col > 0 && map[row][col-1] == 1) {
						col--;
					}
				}
				// 오른쪽(col+1)에 길이 있다면? -> 오른쪽 길이 끝날 때까지 이동
				else if(col < 99 && map[row][col+1] == 1) {
					while(col < 99 && map[row][col+1] == 1) {
						col++;
					}
				}
				// 왼쪽 오른쪽에 길이 없다 = 위로 한 칸 올라감
				row--;
			}
			sb.append("#").append(tc).append(" ").append(col).append("\n");
		}
		System.out.print(sb);
	}
}