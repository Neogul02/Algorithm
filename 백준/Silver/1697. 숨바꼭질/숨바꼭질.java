import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1697. 숨바꼭질
 * @author neogul02
 * 
 * 수빈이는 N 점에 위치, 걷거나 순간이동? 가능
 * 동생은 K 점에 위치
 * N+1, N-1, N*2 경우의 수 완탐?
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int K;

    static boolean[] visited;

    public static void main(String[] args) throws IOException  {
        input();
        System.out.print(bfs());
    }

    /**
     * bfs로 풀면 될거같은데 모든 경우의수에서 가장 빠른 시간
     * 1. -1 하는 경우
     * 2. +1 하는 경우
     * 3. 현재값에 x2 하는 경우
     * -> 3개 경우의수를 모두 고려해서 뻗다가 K를 찾으면 가장 빠른 시간에 찾은것-> return 기저조건
     * bfs에 초기 N이랑 time을 큐에 넣고 시작 ->
     */
    private static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();

        visited = new boolean[100001];

        queue.add(new int[] { N, 0 }); // 첫 위치 N, 초기 depth=0
        visited[N] = true; // 처음 숫자 방문처리

        while (queue.isEmpty() == false) {
            int[] temp = queue.poll();
            int position = temp[0];
            int time = temp[1];

            // K 에 도달하면 반환
            if (position == K)
                return time; // 도달했을때의 시간

            // 3가지 경우의 수 모두 탐색
            int[] next = { position - 1, position + 1, position * 2 };

            for (int nx : next) {
                if (nx < 0 || nx > 100000) continue;// 0보다 작아지거나 최대 100000보다 커지면 skip

                if (visited[nx] == true) continue;
                
                visited[nx] = true;
                queue.add(new int[] { nx, time + 1 });

            }
        }     
        
        return -1;
    }
    
    
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken()); // 수빈 위치
        K = Integer.parseInt(st.nextToken()); // 동생 위치
    }
}
