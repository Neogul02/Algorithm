import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 16926. 배열 돌리기 1
 * @author neogul02
 *
 * N*M 크기의 배열이 주어지고 R번 회전시키는 문제
 * 회전은 가장 바깥쪽부터 시계방향으로 한 칸씩 이동
 * 회전이 끝난 후의 배열을 출력하면 된다.
 */
public class G5_16926 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M, R; // 행, 열, 회전 수
    static int[][] map; // 배열

    public static void main(String[] args) throws IOException {
        input();
        rotate();
    }

    private static void rotate() {

    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
