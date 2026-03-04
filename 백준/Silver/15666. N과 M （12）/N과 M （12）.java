import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 15666. N과 M (12)
 * @author neogul02
 * 
 * - 중복 순열 + 중복 값 제거
 * - 입력받은 수열을 오름차순 정렬 -> 순열 만들기
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] arr;
    static int[] result;

    public static void main(String[] args) throws IOException {
        input();
        permutation(0, 0);
        System.out.print(sb);
    }

    private static void permutation(int depth, int start) {
        // depth == M 자릿수 만큼 채워지면 return 기저조건
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(" "); // 
            }
            sb.append("\n");
            return;
        }

        int lastUsed = 0;

        for (int i = start; i < N; i++) {
            if (lastUsed == arr[i]) // 직전에 사용한 수와 같으면 중복 skip
                continue;

            lastUsed = arr[i]; // 현재 수 기억
            result[depth] = arr[i]; // 중복 순열 

            permutation(depth + 1, i);
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
    }
}
