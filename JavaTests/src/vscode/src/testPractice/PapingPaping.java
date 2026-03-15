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
public class PapingPaping {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
 
    static int N;
 
    static char[][] map; 
    static int[][] countMap; // 지뢰가 몇개 있는지 저장하는 배열
    static boolean[][] visited; // 방문 여부 저장하는 배열
 
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
 
 
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            input();
            
            solve(tc);
        }
        System.out.print(sb);
    }
 
    public static void solve(int tc) {
        // 1. 지뢰 개수 세기
        setMineCount();

        // 2. 클릭 수 세기
        int clickCount = countClick();

        // 3. output
        sb.append("#").append(tc).append(' ')
            .append(clickCount).append("\n");
    }
 
    /**
     * 1. countMap을 bfs로 탐색하면서
     *  1-1. 0인 칸을 클릭하면 주변 8칸을 계속 탐색하고 visited를 true로 바꿔준다.
     *  1-2. clickCount를 증가시킨다.
     * 
     * 2. 0이 아니고 9가 아닌 칸을 클릭하면 주변 8칸을 탐색하지 않는다.
     *  2-1. visited가 false이면 clickCount를 증가시킨다.
     *  2-2. visited가 true이면 이미 클릭한 칸이므로 clickCount를 증가시키지 않는다.
     * 
     * 추가 + 순서대로 탐색하지 말고, 0인 칸 위치를 먼저 저장해두고 0부터 탐색 후 순차 탐색하는게
     * 최솟값이 맞을거같음
     * 
     */
    public static int countClick() {
        int clickCount = 0;
 
        // 1단계: 0인 칸 먼저 처리
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (visited[i][j])
                    continue;
                
                if (countMap[i][j] == 9)
                    continue;
                
 
                if (countMap[i][j] == 0) { // 0인 칸만 먼저 처리!
                    bfs(i, j);
                    clickCount++;
                }
            }
        }
 
        // 2단계: 0 처리 후 아직 방문 안된 숫자 칸 처리
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (visited[i][j]) // 이미 0 bfs로 열린 칸 skip
                    continue;
                  
                if (countMap[i][j] == 9) // 지뢰 skip
                    continue;
                 
                // 여기까지 오면 0 bfs로 열리지 않은 숫자 칸
                visited[i][j] = true;
                clickCount++;
            }
        }
             
        return clickCount;
    }
    
    public static void bfs(int x, int y) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {x, y});
        visited[x][y] = true; // 방문 처리

        while (queue.isEmpty() != true) {
            int[] temp = queue.poll();
            int tempX = temp[0];
            int tempY = temp[1];

            for(int d=0; d<8; d++){
                int nx = tempX + dx[d];
                int ny = tempY + dy[d];

                // 아웃바운드
                if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                    continue;
                

                if (visited[nx][ny] == true)
                    continue;
                
                if (countMap[nx][ny] == 9)
                    continue;      

                // 탐색 가능한 칸
                visited[nx][ny] = true; // 방문 처리
                if (countMap[nx][ny] == 0) { // 주변이 0이면 계속 탐색
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }   
 
    public static void setMineCount() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] == '*') { // 지뢰가 있는 칸이면
                    countMap[i][j] = 9; // 지뢰가 있는 칸은 9로 표시
                    continue;
                }

                int count = 0;
                for (int d = 0; d < 8; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                        continue;

                    if (map[nx][ny] == '*')
                        count++;

                }
                countMap[i][j] = count; // 주변 지뢰 개수 저장
            }
        }
    }

    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        map = new char[N][N];
        countMap = new int[N][N];
        visited = new boolean[N][N];
 
        for (int i = 0; i < N; i++) {
            char[] tempLine = br.readLine().trim().toCharArray();
            map[i] = tempLine;
        }
    }
}