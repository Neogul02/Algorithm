import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 15654. N과 M (5)
 * @author neogul02
 * 
 * - 1부터 N까지 자연수 중 M개를 고른 수열
 * - 일반 순열로 구현하면 될듯? visited 배열로 백트래킹해서 이전 단계로 돌아가면 됨
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] numbers;

    static boolean[] visited;
    static int[] result;
    

    public static void main(String[] args) throws IOException {
        input();
        permutation(0);
        System.out.print(sb);
    }

    private static void permutation(int depth) {
        // 기저조건
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        // 수행로직 // 같은 수를 중복 선택하지 않음
        for (int i = 0; i < N; i++) {

            if (visited[i] == true) continue;
            
            visited[i] = true; // i번째 수를 선택, 방문 처리
            result[depth] = numbers[i]; 

            permutation(depth + 1); // 다음 수 선택하러 가기

            visited[i] = false; // 백트래킹
        }

    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim(), " ");
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers); // 오름차순 정렬

        result = new int[M]; // M 칸 = M 개의 수를 고르는 경우의 수
        visited = new boolean[N];
    }    
}