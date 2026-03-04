import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 11053. 가장 긴 증가하는 부분 수열
 * @author neogul02
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] arr;

    static int[] dp;
    

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.print(sb);
    }
        
    private static void solve() {
        dp = new int[N];
        dp[0] = 1; // 첫 번째 원소는 항상 1로 시작

        for (int i = 1; i < N; i++) {
            dp[i] = 1; // 각 원소는 최소한 자기 자신으로 시작

            // {3, 5, 1, 2} 
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) { // 증가하는 부분 수열 조건
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 최대 길이 갱신
                }
            }
        }

        int maxLength = 0;
        for (int length : dp) {
            maxLength = Math.max(maxLength, length); // 최대 길이 찾기
        }

        sb.append(maxLength).append('\n');
    }
    
    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim(), " ");

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}