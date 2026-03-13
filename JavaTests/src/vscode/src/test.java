import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * SW Academy
 *
 * 1. N, M과 맵 정보를 입력받는다.
 * 2. dfs, 크루스칼을 활용하여 MST를 구한다.
 *  2-1. dfs / bfs하여 섬 정보를 만들고, 섬 간 최소 거리를 저장한다.
 * 	2-2. 크루스칼 알고리즘으로 MST를 구성한다.
 *
 */

public class test {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static final int INF = 1_000_000_007;
	static int N, M, ans;
	static int[][] map = new int[10][10];
	static boolean[][] visited = new boolean[10][10];

	static int num = 1;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static List<Integer>[] border = new ArrayList[7];

	static int[][] minDist = new int[7][7];

	static int[] parent = new int[7];

	static List<Edge> list = new ArrayList<>();

	static class Edge implements Comparable<Edge> {
		int f;
		int t;
		int w;

		public Edge(int f, int t, int w) {
			this.f = f;
			this.t = t;
			this.w = w;
		}

		@Override
		public int compareTo(Edge other) {
			return this.w - other.w;
		}
	}

	static void dfs(int r, int c, int num) {
		if (visited[r][c]) {
			return;
		}
		visited[r][c] = true;
		map[r][c] = num;
		for (int dir = 0; dir < 4; ++dir) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
				if (!visited[nr][nc] && map[nr][nc] > 0) {
					dfs(nr, nc, num);
				}

				if (map[nr][nc] == 0) {
					border[num].add(r * M + c);
				}
			}
		}
	}

	static boolean checkRange(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M) {
			return true;
		}
		return false;
	}

	static void make_edge() {
		for (int from = 1; from < num; ++from) {
			for (int coord : border[from]) {
				int r = coord / M;
				int c = coord % M;

				for (int dir = 0; dir < 4; ++dir) {

					int nr = r + dr[dir];
					int nc = c + dc[dir];
					int dist = 1;

					while (checkRange(nr, nc)) {
						if (map[nr][nc] == from) {
							break;
						}
						if (map[nr][nc] > 0) {
							if (dist > 2) {
								int to = map[nr][nc];
								if (dist - 1 < minDist[from][to]) {
									list.add(new Edge(from, to, dist - 1));
									minDist[from][to] = dist - 1;
								}
							}
							break;

						}
						nr += dr[dir];
						nc += dc[dir];
						++dist;
					}
				}

			}
		}
		Collections.sort(list);
	}

	static void union(int v1, int v2) {
		int pa = find(v1);
		int pb = find(v2);

		if (pa != pb) {
			parent[pa] = pb;
		}
	}

	static int find(int v) {
		if (parent[v] == v) {
			return v;
		}
		return parent[v] = find(parent[v]);
	}

	static void solve() {

		for (int i = 1; i < 7; ++i) {
			border[i] = new ArrayList<>();
			parent[i] = i;
			for (int j = 1; j < 7; ++j) {
				minDist[i][j] = INF;
			}
		}

		for (int r = 0; r < N; ++r) {
			for (int c = 0; c < M; ++c) {
				if (map[r][c] > 0 && !visited[r][c]) {
					dfs(r, c, num++);
				}
			}
		}

		make_edge();

		int cnt = 0;
		for (Edge e : list) {
			if (cnt == num - 2) {
				break;
			}
			int f = e.f;
			int t = e.t;
			int w = e.w;
			if (find(f) != find(t)) {
				union(f, t);
				ans += w;
				cnt++;
			}
		}
		if (cnt != num - 2) {
			ans = -1;
		}

		sb.append(ans);
	}

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int r = 0; r < N; ++r) {
			st = new StringTokenizer(br.readLine().trim());
			for (int c = 0; c < M; ++c) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		solve();

		System.out.println(sb);
	}
}