package backup.PS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 4179. 불!
 * @author neogul02
 * 
 * - 불은 4방향으로 확산됨, 지훈이는 4방향으로 이동 가능
 * - 불과 지훈이는 벽을 통과할 수 없음
 * - '.' = 빈 공간, '#' = 벽, 'J' = 지훈이 시작 위치, 'F' = 불 시작 위치
 * 
 * 1. @input
 *  1-1. R, C 입력받기
 *  1-2. map이랑 visited 배열 초기화
 *  1-3. map 입력받으면서 지훈이 위치랑 불 위치 저장하기
 * 
 * 2. @bfs
 *  2-1. 불이랑 지훈이 위치를 큐에 넣고 bfs 시작
 *  2-2. 불을 먼저 4방향으로 확산시키자.
 *   2-2-1. 벽이랑 outbound는 불이 확산할 수 없으
 *  2-3. 지훈이가 이동할 수 있는 4방향을 탐색해서
 *   2-3-1. 벽이랑 불이랑 outbound는 지훈이가 이동할 수 없음
 *   2-3-2. 지훈이가 이동할 수 있는 곳은 큐에 넣어서 다음 탐색 때 이동할 수 있게 하자
 *   2-3-3. 지훈이가 아웃바운드로 이동할 수 있다? -> 탈출성공하고 time 출력
 *  2-4. 만약 지훈이의 위치와 불의 위치가 같아지면 지훈이 브륄레 true -> IMPOSSIBLE
 *  2-5. 큐가 빌 때까지 탈출 못하면 IMPOSSIBLE
 * 
 * - 출력 : 가능하면 탈출시간 출력, 불가능하면 IMPOSSIBLE
 */
public class fire {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int R, C;
    static char[][] map;
    static boolean[][] visited;

    static int[] startPerson = new int[2]; // 지훈이의 위치
    static ArrayDeque<int[]> fireQueue = new ArrayDeque<>(); // 불의 위치 저장하는 큐

    static int[] dx = { -1, 1, 0, 0}; 
    static int[] dy = { 0, 0, -1, 1 };

    static int time = 0;

    public static void main(String[] args) throws IOException {
        input();
        bfs();
        System.out.print(sb);
    }

    public static void bfs() {
        ArrayDeque<int[]> personQueue = new ArrayDeque<>(); // 지훈이의 위치 저장하는 큐
        personQueue.add(startPerson);
        visited[startPerson[0]][startPerson[1]] = true;

        while (personQueue.isEmpty() == false) {
            time++; // 시뮬레이션 time +1

            // 1. 불 먼저 확산시키기
            int fireSize = fireQueue.size(); // 사이즈 고정

            for (int i = 0; i < fireSize; i++) {
                int[] fireLocation = fireQueue.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = fireLocation[0] + dx[d];
                    int ny = fireLocation[1] + dy[d];

                    if (nx < 0 || nx >= R || ny < 0 || ny >= C) // 아웃바운드는 불 확산 x
                        continue;

                    if (map[nx][ny] == '#' || map[nx][ny] == 'F') // 벽이랑 같은 불끼리는 확산 x
                        continue;

                    map[nx][ny] = 'F'; // 불 확산시키기
                    fireQueue.add(new int[] { nx, ny });
                }
            }

            // 2. 지훈이 이동시키기
            int personSize = personQueue.size(); // 사이즈 고정

            for (int i = 0; i < personSize; i++) {
                int[] personLocation = personQueue.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = personLocation[0] + dx[d];
                    int ny = personLocation[1] + dy[d];

                    if (nx < 0 || nx >= R || ny < 0 || ny >= C) { // 아웃바운드로 이동할 수 있다? -> 탈출성공
                        sb.append(time);
                        return;
                    }
                    // 벽이랑 불이랑 방문한곳은 갈 수 없음
                    if (map[nx][ny] == '#' || map[nx][ny] == 'F' || visited[nx][ny] == true)
                        continue;

                    visited[nx][ny] = true;
                    personQueue.add(new int[] { nx, ny });
                }
            }

            // testPrint
            // int testCase = 1;
            // System.out.println(testCase++);

            // for(int i = 0; i < R; i++) {
            //     for (int j = 0; j < C; j++) {
            //         System.out.print(map[i][j] + " ");
            //     }
            //     System.out.println();
            // }
        }
        
        // 큐가 빌 때까지 탈출 못하면 IMPOSSIBLE
        sb.append("IMPOSSIBLE");
    }
    
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String temp = br.readLine();
            for (int j = 0; j < C; j++) {

                map[i][j] = temp.charAt(j);

                if (map[i][j] == 'J') { // 'J' = 지훈이 시작위치 1개
                    startPerson[0] = i;
                    startPerson[1] = j;
                }

                if (map[i][j] == 'F') { // 'F' = 불 시작위치
                    fireQueue.add(new int[] { i, j });
                }
            }
        }
    }
    
}
