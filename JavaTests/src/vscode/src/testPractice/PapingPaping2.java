package testPractice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
 
/**
 * 1868. 파핑파핑 지뢰찾기
 * @author neogul02
 * 
 * 1. N*N 배열을 만들고 우선적으로 각 칸 8칸에 주변 지뢰가 몇개인지 bfs로 돌면서 초기화를 한다.
 * 2. 배열을 한 번 더 순회하면서 각 숫자를 탐색하고 숫자를 본다.
 * 3. bfs로 주변 0인 칸을 탐색한다. 숫자가 0이 아니면 visited click 수를 증가시킨다.
 *  0을 클릭하면 주위 8칸이 탐색됨, 
 *  
 */
public class PapingPaping2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
 
    static int N;
 
    static char[][] map;
    static int[][] countMap;
    static boolean[][] visited;
    
 
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
 
    static int clickCnt;
 
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            input();
            
            solve(tc);
        }
        System.out.print(sb);
    }
 
    public static void solve(int tc) {

        // 1. 지뢰 숫자 먼저 세팅해두기
        settingCountMap();

        // 2. 0인곳 먼저 클릭하기
        clickBlock();

        // 3. 출력
        sb.append('#').append(tc).append(' ').append(clickCnt).append('\n');

    }
    
    public static void settingCountMap() {
        countMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '*') {
                    countMap[i][j] = 9;
                    continue;
                }

                int mineCount = 0;
                if (map[i][j] == '.') {
                    for (int d = 0; d < 8; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                            continue;

                        if (map[nx][ny] == '*')
                            mineCount++;
                    }
                }
                countMap[i][j] = mineCount;

            }
        }
        
    }
       
    public static void clickBlock() {

        clickCnt = 0;

        // 0인 블럭들 먼저 클릭하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                // 방문한곳은 탐색할 필요 없음
                if (visited[i][j] == true)
                    continue;

                if (countMap[i][j] == 9)
                    continue;

                
                if (countMap[i][j] == 0) {
                    bfs(i, j);
                    clickCnt++;
                }

            }
        }
        // 0인 블럭 모두 클릭하면 남은 숫자칸들 처리하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && countMap[i][j] != 9) {
                    visited[i][j] = true;
                    clickCnt++;
                }
            }
        }

    }
    
    public static void bfs(int x, int y) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { x, y });
        visited[x][y] = true; // 방문 처리

        while (queue.isEmpty() != true) {
            int[] temp = queue.poll();
            int tempX = temp[0];
            int tempY = temp[1];

            for (int d = 0; d < 8; d++) {
                int nx = dx[d] + tempX;
                int ny = dy[d] + tempY;

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if (visited[nx][ny] == true)
                    continue;

                if (countMap[nx][ny] == 9)
                    continue;

                if (countMap[nx][ny] != 0) {
                    visited[nx][ny] = true; // 0이 아니면 방문처리만 하고 큐에는 안넣기
                    continue;
                }

                if (countMap[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.add(new int[] { nx, ny });
                }
            }

            // for (int i = 0; i < N; i++) {
            //     for (int j = 0; j < N; j++) {
            //         System.out.print(visited[i][j]);
            //         System.out.print(' ');
            //     }
            //     System.out.println();
            // }
            // System.out.println("-------------------");
        }
    }

    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        map = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            char[] tempLine = br.readLine().trim().toCharArray();
            map[i] = tempLine;
        }
    }
}