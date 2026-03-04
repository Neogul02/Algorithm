import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 15663. N과 M (9)
 * @author neogul02
 * 
 * - 순열 + 중복 값 제거
 * 
 * 1. 입력받은 수열을 오름차순 정렬 -> 순열 만들기
 * 2. 출력한 값을 저장해두고 현재 만든 순열이랑 비교해서
 *  2-1. 같으면 skip = 중복
 *  2-2. 다르면 sb에 저장하고 lastPrinted 업데이트
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] arr;
    static int[] result;
    static boolean[] visited;
    static int[] lastPrinted; // 직전 출력을 기억

    public static void main(String[] args) throws IOException {
        input();
        permutation(0);
        System.out.print(sb);
    }

    private static void permutation(int depth) {
        // depth == M 자릿수 만큼 채워지면 return 기저조건
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        int lastUsed = 0; // 직전에 사용한 수를 기억

        for (int i = 0; i < N; i++) {
            if (visited[i])
                continue;

            if (lastUsed == arr[i]) // 직전에 사용한 수와 같으면 중복 skip
                continue;
            
            lastUsed = arr[i]; // 현재 수 기억
            visited[i] = true;
            result[depth] = arr[i];

            permutation(depth + 1);
            visited[i] = false; // 백트래킹
        }
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim(), " ");
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr); // 오름차순 정렬

        result = new int[M];
        lastPrinted = new int[M];
        visited = new boolean[N];
    }
}