import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 15652. N과 M (4)
 * @author neogul02
 * 
 * - 1부터 N까지 자연수 중 M개를 고른 수열
 * - 같은 수를 여러 번 골라도 된다. 
 * - 4 2 -> 4개중에 2개를 고르는 경우의 수, 중복도 됨 1 1, ~ 4 4
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static boolean[] visited;

    static int[] result; // 조합 결과 저장

    public static void main(String[] args) throws IOException {
        input();
        combination(0, 1);
        System.out.print(sb);
    }
    
    private static void combination(int depth, int start) {
        // 종료조건 = depth가 고르는 수 M 과 같으면 return
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 수행 로직 = 중복 조합 -> start부터 N까지 반복
        for (int i = start; i <= N; i++) {
            result[depth] = i;
            combination(depth+1, i);
        }
    }
    
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1]; 
        result = new int[M]; // M 칸 = M 개의 수를 고르는 경우의 수
    }
}