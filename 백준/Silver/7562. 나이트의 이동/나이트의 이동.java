import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 7562. 나이트의 이동
 * @author neogul02
 * 
 * 1. T -> 테스트 케이스 수 입력
 * 2. 3줄 입력을 받는다.
 *  2-1. 체스판의 한 변의 길이 I 입력
 *  2-2. 나이트의 현재 위치 입력 from ->
 *  2-3. 나이트가 이동하려는 위치 입력 -> To
 * 
 * 출력 => 최소 이동 횟수
 * bfs 사용해서 푸는데 dx dy 배열을 8칸으로 고려하면 될듯? 
 * 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int I;
    static int fromX, fromY; // 출발 좌표
    static int toX, toY; // 도착 좌표

    static boolean [][] visited;

    static int[] dx = { 2,1,-1,-2,-2,-1,1,2 }; // 나이트 이동 방향 x
    static int[] dy = { 1,2,2,1,-1,-2,-2,-1 }; // 나이트 이동 방향 y

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            input();
            bfs();
        }
        System.out.print(sb);
    }

    public static void bfs() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { fromX, fromY, 0 }); // {x, y, 이동 횟수}

        visited = new boolean[I][I]; // 방문 체크 배열

        visited[fromX][fromY] = true; // 방문처리
        
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int tempX = temp[0];
            int tempY = temp[1];
            int moveCnt = temp[2];

            if (tempX == toX && tempY == toY) { // hit
                sb.append(moveCnt).append('\n');
                return;
            }

            for (int i = 0; i < 8; i++) {
                int nx = tempX + dx[i];
                int ny = tempY + dy[i];

                if (nx < 0 || ny < 0 || nx >= I || ny >= I) // 1. outBound
                    continue;

                if (visited[nx][ny] == true) // 2. 방문한곳인가? 
                    continue;

                // 3. 갈 수 있는 다음 탐색 위치
                visited[nx][ny] = true; // 방문처리
                queue.add(new int[] { nx, ny, moveCnt + 1 });
            }
        }
        
    }
    
    public static void input() throws IOException{   
        I = Integer.parseInt(br.readLine().trim()); // 정사각형 board 한 변 길이

        st = new StringTokenizer(br.readLine().trim(), " ");
        fromX = Integer.parseInt(st.nextToken());
        fromY = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim(), " ");
        toX = Integer.parseInt(st.nextToken());
        toY = Integer.parseInt(st.nextToken());

    }
}
