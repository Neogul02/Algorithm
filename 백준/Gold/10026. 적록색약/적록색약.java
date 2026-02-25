import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 10026. 적록색약
 * @author neogul02
 * 
 * 빨강이랑 초록을 구분 못함 
 * N * N 크기에 R, G, B로 이루어진 그림
 *
 * 1. 일반인은 R G B를 R G B로 보는 구역 수를 공백으로 구분해서 출력
 * 2. 적록색약은 R G B를 R B로 보는 구역 수를 공백으로 구분해서 출력
 * 
 * bfs로 각 구역을 탐색하는데 visited 배열을 이용해서 방문 체크를 2번 하면 될듯
 * 첫번째는 그냥 일반 map으로 탐색하고 다시 map을 적록색약 map으로 바꿔서 탐색하면 될듯 (visited 초기화 해줘야함)
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static char[][] map;

    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int generalPeopleCnt;
    static int noEyePeopleCnt;
    
    public static void main(String[] args) throws IOException {
        input();
        solve();

        sb.append(generalPeopleCnt).append(" ").append(noEyePeopleCnt);
        System.out.print(sb);
    }

    private static void solve() {
        generalPeopleCnt = 0;
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == false) {
                    generalPeopleCnt++;
                    bfs(i, j, map[i][j]);
                }
            }
        }
        
        noEyePeopleCnt = 0;
        visited = new boolean[N][N]; // visited 초기화
        changeMapForNoEye(); // 적록색약인 사람의 시각으로 map 변경
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]==false) {
                    noEyePeopleCnt++;
                    bfs(i, j, map[i][j]);
                }
            }
        }
    }

    private static void bfs(int x, int y, char color) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited[x][y] = true;
        queue.add(new int[] {x, y});

        while(queue.isEmpty() == false) {
            int[] temp = queue.poll();
            int tempX = temp[0];
            int tempY = temp[1];

            for (int d = 0; d < 4; d++) {
                int nx = tempX + dx[d];
                int ny = tempY + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N)  continue; // 탐색할 곳이 범위를 벗어나지 않고
                
                if (visited[nx][ny] == true) continue; // 이미 방문한 곳이 아니면서
                
                if(map[nx][ny] != color) continue; // 같은 색이 아니라면

                queue.add(new int[] {nx, ny}); // 탐색하고
                visited[nx][ny] = true; // 방문 처리
            }
        }
    }
    
    private static void changeMapForNoEye() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'G') {
                    map[i][j] = 'R';
                }
            }
        }
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim()); // N*N 크기 배열

        map = new char[N][N]; 

        for (int i = 0; i < N; i++) {
            char[] tempArr = br.readLine().trim().toCharArray();
            map[i] = tempArr;
        }
    }
}
