
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 17472. 다리 만들기 2
 * @author neogul02
 * 
 * - N * M 크기 지도, 땅이랑 바다 두개로 줌
 * - 다리는 바다에만 놓을 수 있음, 다리는 직선으로만 놓을 수 있고, 다리의 길이는 2 이상이어야 함
 * - 세로 다리는 세로연결만, 가로다리는 가로로만 연결 가능
 * 
 * + 다리가 교차할 수도 있는데 그러면 교차된 모든 다리의 길이가 합쳐서 계산됨 
 * -> A-B = 4 C-D =3 인데 중간에 교차되면 이 다리의 길이는 7이 됨
 * 
 * - 다리 길이의 최솟값을 출력
 * - 모든 섬을 연결할 수 없으면 -1 출력
 * 
 * 1. 일단 섬 덩어리를 라벨링 해주기
 *  1-1. bfs로 각 섬 덩어리를  구분해서 번호 붙이기 -> 1, 2, 3, ...
 *  1-2. 섬의 개수 저장 = map에서 가장 큰 번호
 *
 * 2. 각 섬에서 다른 섬으로 다리를 놓을 수 있는지 확인하기
 *  2-1. 지을 수 있는 다리 후보를 찾아야하는데
 *  2-2. 바다 위로만 가능, 직선으로만 가능, 길이가 2 이상이여함, 교차 가능, 끝이 다른섬에 닿아야함
 *  2-3. 다리를 놓을 수 있는 경우 -> 다리 길이 계산해서 저장하기
 *  2-4. 다리 놓을 수 없는 경우 -> 패스
 * 
 * 3. MST Kruskal로 모든 섬을 연결하기
 *  3-1. 다리 후보들을 길이 기준으로 오름차순 정렬
 *  3-2. Union-Find로 사이클 체크하면서 다리 연결하기
 *  3-3. 모든 섬이 연결되면 다리 길이의 합 계산하기
 *  3-4. 모든섬이 연결되지 않으면 -1 출력하기
 *  
 * 4. 다리 길이의 최솟값 업데이트 
 */
public class MakeBridge {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int N, M; // 지도 크기

    static int[][] map; // 지도 정보 저장 배열
    static int islandCount; // 섬 개수 저장 변수
    static int[][] bridge; // 섬 간 다리 길이 저장 배열
    // -> [섬1][섬2] = 다리 길이]
    // -> [1][2] = 3 이면 섬1과 섬2 사이에 길이 3

    static final int INF = Integer.MAX_VALUE;

    static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
    static int[] dy = { 0, 0, -1, 1 };

    static class EdgeTo {
        int v; // 섬 번호
        int w; // 다리 길이

        EdgeTo(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    
    public static void main(String[] args) throws IOException {
        // 1. 입력 처리
        input();

        // 2. 풀이
        solve();

    }

    public static void solve() {
        bfs(); // 1) 섬 라벨링
        buildGraph(); // 2) 다리 후보로 그래프 구성
        int answer = primMST(); // 3) Prim으로 MST

        System.out.println(answer);
    }
    
    public static void bfs() {
        int islandNum = 0; // 섬 번호
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                // 바다이거나 이미 방문한 땅이면 패스
                if (map[i][j] == 0 || visited[i][j])
                    continue;

                islandNum++;
                ArrayDeque<int[]> queue = new ArrayDeque<>();
                queue.add(new int[] { i, j });

                visited[i][j] = true;
                map[i][j] = islandNum;

                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    int x = cur[0];
                    int y = cur[1];

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = x + dx[dir];
                        int ny = y + dy[dir];

                        // 범위 밖이면 패스
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                            continue;

                        // 이미 방문한 땅이면 패스
                        if (visited[nx][ny])
                            continue;

                        // 바다면 패스
                        if (map[nx][ny] == 0)
                            continue;

                        // 새로운 땅 발견
                        visited[nx][ny] = true;
                        map[nx][ny] = islandNum;
                        queue.add(new int[] { nx, ny });
                    }
                }
            }

            islandCount = islandNum;
        }
    }
    
    
    public static void buildGraph() {
        bridge = new int[islandCount + 1][islandCount + 1];
        for (int i = 1; i <= islandCount; i++) {
            for (int j = 1; j <= islandCount; j++) {
                bridge[i][j] = INF;
            }
        }

        // 땅 칸에서만 4방향 탐색
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {

                if (map[x][y] == 0) continue; // 바다면 패스
                
                int fromIsland = map[x][y];

                // 4방향으로 뻗으며 다른 섬 찾기
                for (int dir = 0; dir < 4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    int bridgeLen = 0;

                    while (true) {
                        // 범위 밖이면
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;

                        // 같은 섬이면 다리 못 놓음
                        if (map[nx][ny] == fromIsland) break;

                        // 바다면 다리 길이 증가하고 계속 직진
                        if (map[nx][ny] == 0) {
                            bridgeLen++;
                            nx += dx[dir];
                            ny += dy[dir];
                            continue;
                        }

                        // 다른 섬을 만남
                        int toIsland = map[nx][ny];

                        // 다리 길이는 바다 칸 수, 2 이상이어야 유효
                        if (bridgeLen >= 2) {
                            if (bridge[fromIsland][toIsland] > bridgeLen) {
                                bridge[fromIsland][toIsland] = bridgeLen;
                                bridge[toIsland][fromIsland] = bridgeLen;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public static int primMST() {
        // 섬이 0개/1개면 비용 0
        if (islandCount <= 1) return 0;

        boolean[] visited = new boolean[islandCount + 1];

        PriorityQueue<EdgeTo> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));
        pq.add(new EdgeTo(1, 0)); // 1번 섬에서 시작

        int picked = 0;
        int total = 0;

        while (!pq.isEmpty() && picked < islandCount) {
            EdgeTo cur = pq.poll();

            if (visited[cur.v]) continue;

            visited[cur.v] = true;
            total += cur.w;
            picked++;

            // cur.v에서 갈 수 있는 다른 섬들을 PQ에 추가
            for (int next = 1; next <= islandCount; next++) {
                if (visited[next]) continue;
                if (bridge[cur.v][next] == INF) continue;
                pq.add(new EdgeTo(next, bridge[cur.v][next]));
            }
        }

        // 모든 섬을 다 못 골랐으면 연결 불가
        if (picked != islandCount) return -1;

        return total;
    }
    
    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; // 지도 정보 저장 배열 초기화

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    
}
