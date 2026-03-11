import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1799. 비숍
 * @author neogul02
 * 
 * - 체스판의 크기 N * N, 비숍을 놓을 수 있는 칸(1) 과 놓을 수 없는 칸(0)
 * - 비숍은 대각선으로만 공격 가능
 * - 최대한 많은 비숍을 놓기
 * 
 * 체스판을 흑백으로 나눠서 따로 생각해도 됨
 * - 같은 색 칸끼리만 대각선이 겹침
 * - (i+j)가 짝수면 한 색, 홀수면 다른 색
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[][] map;

    static boolean[] diag1; // 왼쪽 위 -> 오른쪽 아래 대각선 (i-j가 같음)
    static boolean[] diag2; // 오른쪽 위 -> 왼쪽 아래 대각선 (i+j가 같음)

    static int maxBlack, maxWhite; // 흑백 칸의 최대 비숍 수

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 대각선 체크 배열 초기화 (크기: 2*N-1)
        diag1 = new boolean[2 * N - 1];
        diag2 = new boolean[2 * N - 1];
        
        maxBlack = 0;
        maxWhite = 0;
        
        // 흑백 칸을 나눠서 각각 DFS
        dfsBlack(0, 0); // (i+j)가 짝수인 칸들
        
        diag1 = new boolean[2 * N - 1]; // 초기화
        diag2 = new boolean[2 * N - 1];
        
        dfsWhite(0, 0); // (i+j)가 홀수인 칸들
        
        System.out.print(maxBlack + maxWhite);
    }
    
    // (i+j)가 짝수인 칸들 (흑색)
    public static void dfsBlack(int idx, int count) {
        maxBlack = Math.max(maxBlack, count);
        
        for (int pos = idx; pos < N * N; pos++) {
            int i = pos / N;
            int j = pos % N;
            
            // 짝수 칸이 아니면 스킵
            if ((i + j) % 2 != 0) continue;
            
            // 비숍을 놓을 수 없는 칸이면 스킵
            if (map[i][j] == 0) continue;
            
            // 대각선 체크
            int d1 = i - j + N - 1; // i-j 범위: -(N-1) ~ (N-1), 0 ~ 2N-2로 변환
            int d2 = i + j;
            
            if (!diag1[d1] && !diag2[d2]) {
                diag1[d1] = true;
                diag2[d2] = true;
                dfsBlack(pos + 1, count + 1);
                diag1[d1] = false;
                diag2[d2] = false;
            }
        }
    }
    
    // (i+j)가 홀수인 칸들 (백색)
    public static void dfsWhite(int idx, int count) {
        maxWhite = Math.max(maxWhite, count);
        
        for (int pos = idx; pos < N * N; pos++) {
            int i = pos / N;
            int j = pos % N;

            // 홀수 칸이 아니면 스킵
            if ((i + j) % 2 != 1) continue;

            // 비숍을 놓을 수 없는 칸이면 스킵
            if (map[i][j] == 0) continue;

            // 대각선 체크
            int d1 = i - j + N - 1;
            int d2 = i + j;

            if (!diag1[d1] && !diag2[d2]) {
                diag1[d1] = true;
                diag2[d2] = true;
                dfsWhite(pos + 1, count + 1);
                diag1[d1] = false;
                diag2[d2] = false;
            }
        }
        
    }
}
