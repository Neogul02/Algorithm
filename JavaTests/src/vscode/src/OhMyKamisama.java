import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 7793. 오! 나의 여신님
 * - 악마는 상하좌우 영역을 '부식' 시킨다.. 크큭
 * 
 * - 수연이는 'S', Kamisama는 'D' 는 하나로 fix
 * - 악마는 '*' 돌은 'X', 빈 공간은 '.'
 * 
 * - bfs로 풀이
 * 
 * - 악마는 매 초마다 상하좌우로 부식시킴
 * - 입력 받으면서 각각의 이벤트들의 위치를 저장해두자
 * - 부식이 먼저 일어난 다음에 수연이를 이동시키기
 * 
 * @see bfs
 * 1. 수연이 위치 부터 시작해서 bfs 탐색
 * 2. 매 턴마다 악마를 먼저 확산시키고, 그 다음 수연이를 이동시킨다.
 * 3. 수연이가 카미사마 위치에 도달하면 종료
 */
public class OhMyKamisama {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int answer;

    static int[] suyeonPos; // 수연이 위치
    static ArrayDeque<int[]> devilPos; // 악마 위치는 여러개 있을 수 있음

    static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            // 1. input
            input();

            // 2. solve
            solve(tc);
        }

        // 3. output
        System.out.print(sb);
    }

    public static void solve(int tc) {
        // 1. Init 
        visited = new boolean[N][M];
        answer = -1;

        // 2. bfs
        bfs();

        // 3. output
        sb.append("#").append(tc).append(' ');
        if (answer == -1) {
            sb.append("GAME OVER");
        } else {
            sb.append(answer);
        }
        sb.append('\n');
    }

    public static void bfs() {
        ArrayDeque<int[]> suyeonQueue = new ArrayDeque<>();
        
        // 수연이 위치 큐에 추가
        suyeonQueue.add(new int[] { suyeonPos[0], suyeonPos[1], 0 });
        visited[suyeonPos[0]][suyeonPos[1]] = true;

        // 매 턴마다 악마를 먼저 확산시키고, 그 다음 수연이를 이동시킨다.
        while (suyeonQueue.isEmpty() != true) {

            // 사이즈 고정
            int devilSize = devilPos.size(); 
            for (int i = 0; i < devilSize; i++) {
                int[] cur = devilPos.poll();
                int r = cur[0];
                int c = cur[1];

                for (int d = 0; d < 4; d++) {
                    int nr = r + dx[d];
                    int nc = c + dy[d];

                    // 범위 밖이면 확산 불가
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                        continue;
                    }

                    // 이미 부식시킨곳이거나 돌이거나 카미사마 위치는 부식 불가
                    if (map[nr][nc] == '*' || map[nr][nc] == 'X' || map[nr][nc] == 'D') {
                        continue;
                    }

                    // 빈 공간이랑 수연이 위치로는 전파 가능
                    if (map[nr][nc] == '.' || map[nr][nc] == 'S') {
                        map[nr][nc] = '*'; // 전파 처리
                        devilPos.add(new int[] { nr, nc });
                    }
                }
            }

            // 사이즈 고정
            int suyeonSize = suyeonQueue.size();
            for (int i = 0; i < suyeonSize; i++) {
                int[] cur = suyeonQueue.poll();
                int r = cur[0];
                int c = cur[1];
                int time = cur[2];

                for (int d = 0; d < 4; d++) {
                    int nr = r + dx[d];
                    int nc = c + dy[d];

                    // 범위 밖이면 이동 불가
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                        continue;
                    }

                    // 카미사마 위치면 종료
                    if (map[nr][nc] == 'D') {
                        answer = time + 1;
                        return;
                    }

                    // 이미 방문한곳은 스킵
                    if (visited[nr][nc]) {
                        continue;
                    }

                    // 악마가 부식시킨 곳이거나 돌이 있는 곳은 이동 불가
                    if (map[nr][nc] == '*' || map[nr][nc] == 'X') {
                        continue;
                    }

                    // 이동할 수 있는 곳이면 방문처리하고 큐에 추가
                    if (map[nr][nc] == '.') {
                        visited[nr][nc] = true;
                        suyeonQueue.add(new int[] { nr, nc, time + 1 });
                    }
                }
            }
        }
    }
    
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        devilPos = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                // 수연이 위치
                if(map[i][j] == 'S') {
                    suyeonPos = new int[] { i, j };
                }

                // 악마 위치는 여러개 있을 수 있음
                if(map[i][j] == '*') {
                    devilPos.add(new int[] { i, j });
                }
            }
        }
    }
} 