import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N; // 놓아야하는 퀸의 갯수 & 보드 크기
    static int result; // 경우의 수 결과
    static int[] col; // col[row] = 해당 row에 퀸이 놓인 열 번호

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        col = new int[N];
        result = 0;
        
        dfs(0); // 0번 행부터 시작
        System.out.print(result);
    }
    
    public static void dfs(int row) {
        // 기저조건 = 퀸을 N개 다 놓으면 성공
        if (row == N) {
            result++;
            return;
        }

        // 현재 row에 퀸을 놓을 열을 선택
        for (int c = 0; c < N; c++) {
            col[row] = c; // row 행, c 열에 퀸 놓기
            
            if (isValid(row)) { // 퀸을 놓을 수 있는지 검사
                dfs(row + 1); // 다음 행으로
            }
        }
    }
    
    // row 행의 퀸과 충돌하지 않는가?
    public static boolean isValid(int row) {
        for (int i = 0; i < row; i++) {
            // 같은 열에 있는지 체크
            if (col[i] == col[row]) {
                return false;
            }
            
            // 대각선에 있는가?
            // 행의 차이와 열의 차이가 같으면 대각선
            if (Math.abs(row - i) == Math.abs(col[row] - col[i])) {
                return false;
            }
        }
        return true;
    }
}