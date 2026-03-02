
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 21736. 헌내기는 친구가 필요해
 * @author neogul02
 * 
 * - N x M 크기의 학교 맵
 * - O는 빈공간 X는 벽, I는 도연이, P는 사람 - I는 하나로 고정
 * 
 * 1. N, M 입력받고 학교 맵 입력받기
 * 2. 도연이 위치 찾기
 * 3. bfs탐색을하는데 X벽이랑 캠퍼스 바깥 (아웃바운드)는 탐색 못함
 * 4. 탐색한 칸이 친구 P 라면 사람 수 +1
 * 5. 만난 사람이 한명도 없으면 "TT", 있으면 만난 사람 수 출력
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int peopleCnt = 0;

    public static void main(String[] args) throws IOException {
        input();
        bfs();

        if (peopleCnt != 0)
            System.out.print(peopleCnt);
        else
            System.out.print("TT");

    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(findDoyunLocation()); // 도윤이 위치부터 시작

        // 큐가 비어있지 않을때까지
        while (queue.isEmpty() == false) {
            int[] temp = queue.poll();
            int tempX = temp[0];
            int tempY = temp[1];

            // 4방향 검사
            for (int idx = 0; idx < 4; idx++) {
                int nx = tempX + dx[idx];
                int ny = tempY + dy[idx];

                // 아웃 바운드 처리
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                // 방문한곳이면 pass
                if (visited[nx][ny] == true)
                    continue;

                // 벽이면 못감
                if (map[nx][ny] == 'X')
                    continue;

                // 탐색한 칸이 사람이라면
                if (map[nx][ny] == 'P') {
                    peopleCnt++; // 사람을 만났으니 +1;
                }

                visited[nx][ny] = true; // 해당 위치 방문처리
                queue.add(new int[] { nx, ny }); // 다음위치 큐에 저장
            }
        }
    }
    
    private static int[] findDoyunLocation() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'I') {
                    visited[i][j] = true; // 시작위치 방문처리
                    return new int[] { i, j }; // 도윤이 위치 리턴
                }
            }
        }
        return new int[] {0, 0};
    }
    
    
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }
    }
}