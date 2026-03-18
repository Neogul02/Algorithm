import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class NomalBag{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, K;
    static int[] W, V;
    static int[][] dp;
    public static void main(String[] args) throws Exception{
        input();
        solve();
        System.out.println(dp[N][K]);
    }

    public static void solve() {
        // 1번 물건부터 N번 물건까지 순차적으로 고려
        for (int i = 1; i <= N; i++) {
            // 배낭의 임시 용량 w를 1부터 K까지 늘려가며 확인
            for (int w = 1; w <= K; w++) {
                
                // 1. 현재 물건을 넣을 수 없는 경우 (물건 무게가 배낭 용량보다 큼)
                if (W[i] > w) {
                    dp[i][w] = dp[i - 1][w]; // 이전 상태를 그대로 가져옴
                } 
                // 2. 현재 물건을 넣을 수 있는 경우
                else {
                    // (안 넣는 경우) vs (현재 물건 무게만큼 공간을 비우고 넣는 경우)
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - W[i]] + V[i]);
                }
            }
        }
    }   

    public static void input() throws Exception{
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게

        W = new int[N + 1]; // 물건의 무게
        V = new int[N + 1]; // 물건의 가치
        dp = new int[N + 1][K + 1]; // dp[i][w] : i번 물건까지 고려했을 때, 배낭 용량 w에서의 최대 가치

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine().trim());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }
    }
}