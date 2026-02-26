import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1012. 유기농 배추
 * 
 * @author neogul02
 * 
 * 배추흰지렁이 -> 해충을 먹는다 -> 인접한 지역에 배추 흰지렁이가 있으면 인접한 배추들은 모두 보호받는다. -> 인접해있다
 * = 상하좌우 연결
 * 
 * 0은 배추 없는땅, 1은 배추 심은 땅
 * 가로길이 M, 세로길이 N, 배추 위치 개수 K
 */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int M, N; // 가로길이, 세로길이
	static int K; // 배추 위치 개수

	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			input();
			bfs();
		}
		System.out.print(sb);
	}

	private static void bfs() {
		int CaterpieCnt = 0; // 캐터피

		for (int i = 0; i < M; i++) { // 모든 위치를 돌다가
			for (int j = 0; j < N; j++) {

				if (map[i][j] == 1) { // 1을 만나면 멈추고

					if (visited[i][j] == true) // 방문한 곳이라면? 다음으로
						continue;

					Queue<int[]> queue = new ArrayDeque<>();
					queue.add(new int[] { i, j });
					visited[i][j] = true;
					CaterpieCnt++;

					while (queue.isEmpty() == false) {
						int[] temp = queue.poll();
						int tempX = temp[0];
						int tempY = temp[1];

						for (int d = 0; d < 4; d++) {
							int nx = tempX + dx[d];
							int ny = tempY + dy[d];

							if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue; // 아웃바운드 처리
								
							if (visited[nx][ny] == true) continue; // 방문한곳이 아니고

							if (map[nx][ny] == 0) continue; // 0이 아니라면

							queue.add(new int[] { nx, ny }); // 다음 배추 덩어리로 들어가고 
							visited[nx][ny] = true; // 미리 방문처리
						}
					}
				}
			}
		}
		sb.append(CaterpieCnt).append('\n');
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim(), " ");
		M = Integer.parseInt(st.nextToken()); // map 가로 길이
		N = Integer.parseInt(st.nextToken()); // map 세로 길이
		K = Integer.parseInt(st.nextToken()); // K = 배추의 개수

		map = new int[M][N];
		visited = new boolean[M][N];

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			map[X][Y] = 1; // 배추 위치 찍기
		}
	}
}