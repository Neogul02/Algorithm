import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 2819. 격자판의 숫자 이어붙이기
 * @author neogul02
 * 
 * - 4 * 4 크기 고정 격자판, 각 칸에는 0~9 사이의 숫자가 있는데
 * - 상하좌우로 6번 움직여서 만들 수 있는 7자리 수를 모두 구하자.
 * - 근데 중복숫자는 안됨 -> TreeSet? 굳이 정렬이 필요없을거같음 -> HashSet으로 중복 제거
 * - 7자리 수는 String으로 만들어서 Set에 저장하자.
 * - 7자리 수를 모두 구한 다음에 Set의 크기 출력하기
 * - 각 좌표로 이동하는 dfs를 구현해서 7자리 depth가 되면 Set에 저장하기
 */
public class BoardNumber {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static HashSet<String> numbersSet = new HashSet<>();
    static char[][] board; // 격자판

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            // 1. 입력 처리
            input();

            // 2. 풀이
            solve(tc);
        }
        System.out.print(sb);
    }

    public static void solve(int tc) {
        // 상하좌우로 6번 움직여서 만들 수 있는 7자리 수를 모두 구하자.
        numbersSet = new HashSet<>(); // Set 초기화

        // 7자리 수 탐색 dfs
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                dfs(i, j, String.valueOf(board[i][j]));
            }
        }

        // System.out.println(numbersSet);

        sb.append('#').append(tc).append(' ')
                .append(numbersSet.size()).append('\n');
    }
    
    public static void dfs(int x, int y, String numStr) {
        // 7자리 수 완성 Set에 저장
        if (numStr.length() == 7) {
            numbersSet.add(numStr);
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // outbound check
            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;

            dfs(nx, ny, numStr + board[nx][ny]);
        }
    }
    
    
    public static void input() throws IOException {
        // 격자판 정보 저장 배열 초기화
        board = new char[4][4];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            for (int j = 0; j < 4; j++) {
                board[i][j] = st.nextToken().charAt(0);
            }
        }
        
        
        // testPrint
        // for (int i = 0; i < 4; i++) {
        //     for (int j = 0; j < 4; j++) {
        //         System.out.print(board[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }
}
