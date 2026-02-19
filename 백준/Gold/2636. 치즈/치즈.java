import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2636. 치즈
 * 
 * @author neogul02
 * 
 * 공기와 접촉되는 부분은 1시간뒤에 녹는다.
 * 
 * 1. 안에 구멍이 있는경우에는 어떡하지 hole을 먼저 찾아야할것같음
 * 2. 치즈의 상하좌우 탐색 -> 공기(0) 이 있는가? -> 공기랑 접촉 -> 1시간뒤에 사라짐 
 * 3. 남은 치즈 개수랑 시간을 카운트해서 저장해놔야함
 * 
 * 출력 1. 치즈가 모두 없어지는 시간 
 * 출력 2. 녹기 1시간 전에 남아있는 치즈 조각의 수
 */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int R, C;
	static int[][] cheeseMap;

	static int[] dr = new int[] { -1, 1, 0, 0 };
	static int[] dc = new int[] { 0, 0, -1, 1 };

	static ArrayList<Integer> cheeseCntHistory = new ArrayList<Integer>();

	private static ArrayList<int[]> meltList;

	public static void main(String[] args) throws IOException {
		input();
		solve();

		System.out.print(sb);
	}

	/**
	 * @solve 
	 * 1. 외부 공기 찾기 
	 * 2. 녹을 치즈 찾기 -> 외부공기 visited배열과 닿아있으면 녹아야함
	 * 3. 녹아야 할 치즈가 없다 = 다 녹았다 = 루프탈출
	 * 4. 히스토리에 현재 치즈의 개수를 기록 
	 * 5. 2.에서 만든 리스트로 배열 최신화 = 치즈 녹이기
	 * 
	 * 6. 치즈가 다 녹기 전까지 시간 + 녹기 전 마지막 치즈 개수 출력
	 */
	private static void solve() {

		ArrayList<Integer> cheeseHistory = new ArrayList<Integer>();

		while (true) {
			// 1. 외부 공기 찾기
			boolean[][] airVisited = findAir();

			// 2. 녹을 치즈 찾기
			meltList = new ArrayList<>();
			findMeltingCheese(airVisited);

			// 3. 치즈 없으면 종료
			if (meltList.isEmpty())
				break;

			// 4. 치즈 개수 기록 (녹기 전)
			cheeseHistory.add(meltList.size());

			// 5. 치즈 녹이기
			meltCheese();

		}
		// 결과값 저장
		sb.append(cheeseHistory.size()).append('\n').append(cheeseHistory.get(cheeseHistory.size()-1));
	}

	/**
	 * @meltCheese 
	 * meltList에 들어가있는 녹아야할 치즈 후보들 녹이기
	 */
	private static void meltCheese() {
		for (int[] temp : meltList) {
			cheeseMap[temp[0]][temp[1]] = 0;
		}
	}

	/**
	 * @findMeltingCheese
	 * 치즈 부분만 탐색해서 주변 상하좌우에 외부공기 접촉이 됐다면 녹아야할 후보에 추가
	 */
	private static void findMeltingCheese(boolean[][] airVisited) {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				// 치즈가 아니면 패스
				if (cheeseMap[r][c] != 1)
					continue;

				// 상하좌우 검사
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					// 범위 체크
					if (nr < 0 || nr >= R || nc < 0 || nc >= C)
						continue;

					// 외부 공기 접촉?
					if (airVisited[nr][nc]) {
						meltList.add(new int[] { r, c });
						break; // 한 번만 추가
					}
				}
			}
		}
	}

	/**
	 * @findAir
	 * 0,0에서 bfs를 시작해서 0인칸만 탐색 = 외부공기
	 * 외부공기 visited 배열을 return
	 */
	private static boolean[][] findAir() {
		boolean[][] visited = new boolean[R][C];
		Queue<int[]> queue = new ArrayDeque<>();

		queue.offer(new int[] { 0, 0 });
		visited[0][0] = true; // 0,0 에서 시작

		while (queue.isEmpty()!=true) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				// 범위 체크
				if (nr < 0 || nr >= R || nc < 0 || nc >= C)
					continue;

				// 방문 체크
				if (visited[nr][nc])
					continue;

				// 치즈는 통과 불가
				if (cheeseMap[nr][nc] == 1)
					continue;

				// 외부 공기 (0)
				visited[nr][nc] = true;
				queue.add(new int[] { nr, nc });
			}
		}

		return visited;
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		cheeseMap = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int j = 0; j < C; j++) {
				cheeseMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}