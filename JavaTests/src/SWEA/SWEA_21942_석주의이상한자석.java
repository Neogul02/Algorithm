package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 21942. 석주의 이상한 자석
 * @author neogul02
 * 
 * 1. 테스트케이스 입력받기
 * 2. 자석판 내용을 입력받아 저장하기
 * 3. 아래로 땡기는 함수부터 구현 @pullDown()
 * 4. 오른쪽으로 땡기기 @pullRight()
 * 5. 최종 열과 행 1 개수 계산
 * 
 */
public class SWEA_21942_석주의이상한자석 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N; // 보드의 한 변 크기
	static int[][] map; // 2차원 배열 자석판
	
	
	public static void main(String[] args) throws IOException {
		//1. 테스트케이스 입력받기
		int T = Integer.parseInt(br.readLine().trim());
	
		for(int tc=1; tc<=T; tc++) {
			input();
			
			pullDown();
			pullRight();
			
			int cntLastRow = 0;
			int cntLastCol = 0;
					
			for(int i=0; i<N; i++) {
				if(map[N-1][i]==1) cntLastRow++;
				if(map[i][N-1]==1) cntLastCol++;
			}
			
			sb.append('#').append(tc).append(' ')
			.append(cntLastRow).append(' ').append(cntLastCol)
			.append('\n');
		}
		System.out.print(sb);
	}

	// 0번째 row를 검사
	private static void pullDown() {
		// col 검사 0이 아닌 경우에는 해당 col 바라보기
		for(int col=0; col<N; col++) {
			if(map[0][col]!=0) {
				
				double power = 1.0;
				
				int rowIdx = 1;
				int cnt = 1;
				int temp = 0;
				
				// 마지막 row 까지 검사
				while(rowIdx < N) {
					
					// 0이 아닐때까지 세면서 인력 증가
					while (rowIdx < N && map[rowIdx][col] == 0) {
						rowIdx++;
						power = power * 1.9;
					}
					
					// 자석이 끝에 도달했다면 내리기
					if (rowIdx == N) {
						moveDown(col, N - 1, cnt);
						break;
					}
					
					// 연속되게 자석(1)을 만나면 
					double alphaMagnet = 0.0;
					while (rowIdx < N && map[rowIdx][col] == 1) {
						rowIdx++;
						temp++;
						alphaMagnet += 1.0;
					}
					
					// 힘 비교해서 멈출지 합쳐서 계속 갈지 결정하기
					if (power <= alphaMagnet) {
						moveDown(col ,rowIdx - temp - 1, cnt);
						break;
					}
					
					moveDown(col, rowIdx-temp-1, cnt);
					
					cnt = cnt + temp;
					temp = 0; // 다 내리면 저장소는 초기화
					power = power + alphaMagnet;
				}
			}
		}
	}

	private static void moveDown(int col, int idx, int cnt) {
		while(cnt>0) {
			map[idx][col] = 1;
			idx--;
			cnt--;
		}
		
		while(idx>-1) {
			map[idx][col] = 0;
			idx--;
		}
	}

	
	private static void pullRight() {
		
		for(int row=0; row<N; row++) {
			if(map[row][0]!=0) {
				double power = 1.0;
				int colIdx = 1;
				int cnt = 1;
				int temp = 0;

				while(colIdx < N) {
					while (colIdx < N && map[row][colIdx] == 0) {
						colIdx++;
						power *= 1.9;
					}
					
					if (colIdx == N) {
						moveRight(row, N - 1, cnt);
						break;
					}
					
					double alphaMagnet = 0.0;
					while (colIdx < N && map[row][colIdx] == 1) {
						colIdx++;
						temp++;
						alphaMagnet += 1.0;
					}
					
					if (power <= alphaMagnet) {
						moveRight(row ,colIdx - temp - 1, cnt);
						break;
					}
					
					moveRight(row ,colIdx - temp - 1, cnt);
					cnt += temp;
					temp = 0;
					power = power + alphaMagnet;

				}
			}
		}
	}
	
	private static void moveRight(int row, int idx, int cnt) {
		while(cnt>0) {
			map[row][idx] = 1;
			idx--;
			cnt--;
		}
		
		while(idx>-1) {
			map[row][idx] = 0;
			idx--;
		}
	}

	private static void input() throws IOException {
		N = Integer.parseInt(br.readLine().trim());
		// 2. 자석판 내용을 입력받아 저장하기
		map = new int[N][N];
		
		for (int r = 0 ; r < N ; r++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int c = 0 ; c < N ; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
