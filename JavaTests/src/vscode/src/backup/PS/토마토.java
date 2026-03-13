package backup.PS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 7569.토마토
 * @author neogul02
 * 
 * 1. 격자모양 박스를 쌓아 올린 토마토 박스
 * 2. 잘 익은 토마토(1)이랑 안익은 토마토(0)가 섞여있음 -> 잘 익은 토마토 옆에 있으면 맛있게 익어버린다..!
 * 3. 대각선은 안되고 상하좌우 + 위 + 아래 박스 까지 인접해잇다는 설정
 * 4. 모든 토마토가 익을때까지 며칠이 걸리는가? 
 *
 * 3차원 배열 박스에서 bfs 하면 될듯?
 * 
 * 맨 아래층 - 맨 위층 시뮬레이션을 동시에 진행해서 모든 토마토 0이 1이 될때가 며칠인지?
 * 
 * 1. M, N, H 가로 세로 높이 정보를 입력받고 맨 위층과 맨 아래층을 초기화 한다 = 높이가 1이라면? 
 * 
 */
public class 토마토 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int M, N, H; // 가로 M, 세로 N, 높이 H

    static int[][][] box;
    static int[][][] visited;

    static int[] dx = { -1, 1, 0, 0, 0, 0 }; // 행 (가로)
    static int[] dy = { 0, 0, -1, 1, 0, 0 }; // 열 (세로)
    static int[] dz = { 0, 0, 0, 0, -1, 1 }; // 층 (높이)

    public static void main(String[] args) throws IOException {
        input();
        bfs();
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{}
    }
    
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[M][N][H];
        visited = new int[M][N][H];


    }
    
}
