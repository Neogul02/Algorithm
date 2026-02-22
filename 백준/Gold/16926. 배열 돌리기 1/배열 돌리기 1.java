import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 16926. 배열 돌리기 1
 * @author neogul02
 *
 * - 배열은 여러 겹의 "테두리(레이어)"로 이루어져 있다.
 *   레이어 개수 = min(N, M) / 2
 *
 * - 배열의 각 레이어를 나눠서 독립적으로 회전시킨다.
 *   1. 해당 레이어의 테두리 원소들을 "반시계 방향" 순서로 리스트에 뽑아 담는다.
 *      (왼쪽세로, 아래가로, 오른쪽세로, 위가로)
 *   2. 회전은 R번이지만, 레이어 길이를 len이라 하면 rot = R % len 만큼만 실제로 이동하면 된다.
 *   3. 리스트에서 rot만큼 왼쪽으로 이동한 효과를 내도록 시작 인덱스를 rot로 잡고,나중에 추가해주자
 *
 * - 이렇게 모든 레이어를 처리하면 전체 배열이 R번 반시계 회전한 결과가 된다.
 */

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int N, M, R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    // 입력을 읽어 N, M, R, 배열 a를 초기화
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    // 레이어별로 "필요한 횟수만큼만" rotate()를 호출
    static void solve() {
        int layers = Math.min(N, M) / 2;

        for (int layer = 0; layer < layers; layer++) {
            // 레이어 테두리 원소 수 = 2*(height + width - 2)
            int height = N - 2 * layer;
            int width  = M - 2 * layer;
            int len    = 2 * (height + width - 2);
            int rot    = R % len; // 실제로 필요한 회전 횟수

            for (int r = 0; r < rot; r++) {
                rotate(layer);
            }
        }
    }

    // 특정 레이어를 반시계 방향으로 한 칸 회전
    static void rotate(int layer) {
        int top    = layer;
        int left   = layer;
        int bottom = N - 1 - layer;
        int right  = M - 1 - layer;

        // 시작점(top, left)의 값을 임시 저장
        int temp = map[top][left];

        // 위쪽 행: 왼쪽으로 당기기
        for (int j = left; j < right; j++) map[top][j] = map[top][j + 1];

        // 오른쪽 열: 위로 당기기
        for (int i = top; i < bottom; i++) map[i][right] = map[i + 1][right];

        // 아래쪽 행: 오른쪽으로 당기기
        for (int j = right; j > left; j--) map[bottom][j] = map[bottom][j - 1];

        // 왼쪽 열: 아래로 당기기
        for (int i = bottom; i > top + 1; i--) map[i][left] = map[i - 1][left];

        // 임시 저장한 값을 (top+1, left)에 채워 반시계 완성
        map[top + 1][left] = temp;
    }

    // 결과 출력
    static void print() {
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]);
                if (j + 1 < M) sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}