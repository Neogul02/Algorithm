import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 5427. 불
 * @author neogul02
 * 
 * - '.' : 빈 공간, '#' : 벽, '@' : 상근이의 시작 위치, '*' : 불의 시작 위치
 * - 불이 4방향으로 이동, 상근이도 4방향으로 이동, 불이 먼저 이동
 * - 상근이가 탈출할 수 있는 빠른 시간, 탈출할 수 없는 경우 IMPOSSIBLE
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int W, H; // 맵의 높이랑 너비

    static char[][] map;
    static boolean[][] visited;

    static int startX, startY; // 상근이의 시작 위치
    static ArrayDeque<int[]> fireQueue; // 불의 위치 리스트

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            input();
            bfs();
        }
        System.out.print(sb);
    }

    public static void bfs() {
        // 큐 생성해서 상근이 초기 위치넣고 상근이를 기준으로 while
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { startX, startY}); // x, y
        visited[startX][startY] = true; // 방문처리

        int time = 0; // 걸린 시간

        while (queue.isEmpty() != true) {
            time++;

            // 1. 불 먼저 이동
            int fireSize = fireQueue.size(); // 다음 불이 이동한 만큼만 시뮬
            for(int fireIdx=0; fireIdx<fireSize; fireIdx++) {
                int[] fire = fireQueue.poll();
                int fireX = fire[0];
                int fireY = fire[1];

                for(int dir=0; dir<4; dir++) {
                    int nextFireX = fireX + dx[dir];
                    int nextFireY = fireY + dy[dir];

                    // 아웃바운드면 pass
                    if (nextFireX < 0 || nextFireX >= H || nextFireY < 0 || nextFireY >= W) continue;
                    

                    // 벽이거나 이미 불이 있으면 pass
                    if(map[nextFireX][nextFireY] == '#' || map[nextFireX][nextFireY] == '*') continue;

                    // 불이 이동할 수 있는 곳이면 불 확산,
                    map[nextFireX][nextFireY] = '*';
                    fireQueue.add(new int[]{nextFireX, nextFireY});
                }
            }
           
            // 2. 상근이 이동
            int size = queue.size();
            for (int idx = 0; idx < size; idx++) {
                int[] temp = queue.poll();
                int tempX = temp[0];
                int tempY = temp[1];

                // 상근이가 이동할 수 있는 4방향 탐색
                for (int dir = 0; dir < 4; dir++) {
                    int nextX = tempX + dx[dir];
                    int nextY = tempY + dy[dir];

                    // 아웃바운드면 탈출 성공
                    if (nextX < 0 || nextX >= H || nextY < 0 || nextY >= W) {
                        sb.append(time).append('\n');
                        return;
                    }

                    // 방문한곳이면 pass
                    if (visited[nextX][nextY] == true) continue;

                    // 벽이거나 불이면 pass
                    if (map[nextX][nextY] == '#' || map[nextX][nextY] == '*') continue;

                    // 상근이가 이동할 수 있는 곳이면 탐색
                    visited[nextX][nextY] = true;
                    queue.add(new int[] { nextX, nextY});
                }
            }
        }
        // 상근이가 움직일 수 있는 수를 다 돌았는데 탈출 return이 안됐다?
        sb.append("IMPOSSIBLE").append('\n');
    }
    
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        visited = new boolean[H][W];

        fireQueue = new ArrayDeque<>();

        for (int i = 0; i < H; i++) {
            String tempLine = br.readLine().trim();

            for (int j = 0; j < W; j++) {
                map[i][j] = tempLine.charAt(j);

                // 시작 위치 찾기
                if (map[i][j] == '@') {
                    startX = i;
                    startY = j;
                }

                if (map[i][j] == '*') {
                    fireQueue.add(new int[] { i, j });
                }
            }
        }
    }
    
}