import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 9019. DSLR
 * @author neogul02
 * 
 * 레지스터 0<= n <= 9999 값을 저장할 수 있다.
 * 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int A, B; // A에서 B로 변환
    static boolean[] visited; // 0~9999까지의 방문 여부

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 수
        for (int tc = 0; tc < T; tc++) {
            input();
            bfs();
        }
         System.out.print(sb);
    }
 
    // BFS 탐색
    private static void bfs() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(A);

        String[] path = new String[10000]; // 각 숫자에 도달하기 위한 연산 경로를 저장할 배열
        visited = new boolean[10000]; // 방문 배열 초기화
        
        visited[A] = true; // 방문처리
        path[A] = ""; // 시작점의 경로는 빈 문자열

        while (queue.isEmpty() != true) {
            int temp = queue.poll();

            // 종료조건
            if (temp == B) {
                sb.append(path[temp]).append('\n');
                return;
            }

            // D
            int D = (temp * 2) % 10000;
            if (visited[D] == false) {
                visited[D] = true; // 방문처리
                queue.add(D); // 큐에 추가
                path[D] = path[temp] + "D"; // 연산 경로에 추가
            }

            // S
            int S = (temp == 0) ? 9999 : temp - 1;
            if (visited[S] == false) {
                visited[S] = true; // 방문처리
                queue.add(S); // 큐에 추가
                path[S] = path[temp] + "S"; // 연산 경로에 추가
            }

            // L, 천의자리를 일의자리로, 나머지 자리를 왼쪽으로 한 칸씩 이동
            int L = (temp % 1000) * 10 + temp / 1000; 
            if (visited[L] == false) {
                visited[L] = true; // 방문처리
                queue.add(L); // 큐에 추가
                path[L] = path[temp] + "L"; // 연산 경로에 추가
            }

            // R, 일의자리를 천의자리로, 나머지 자리를 오른쪽으로 한 칸씩 이동
            int R = (temp % 10 * 1000) + (temp / 10); 
            if (visited[R] == false) {
                visited[R] = true; // 방문처리
                queue.add(R); // 큐에 추가
                path[R] = path[temp] + "R"; // 연산 경로에 추가
            }
        }
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
    }
}