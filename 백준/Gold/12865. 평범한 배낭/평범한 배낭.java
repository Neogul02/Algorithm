import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, K;

    static int[][] arr;

    static int result;

    public static void main(String[] args) throws IOException{
        input();
        solve();
        System.out.println(result);
    }

    public static void solve(){
        // dp를 활용한 메모이제이션 [N+1][K+1] -> N개의 물건과 K의 무게까지 고려
        int[][] dp = new int[N+1][K+1];
        for(int i=1; i<=N; i++){ // 1부터 N개의 물건을 고려 1,2,3,..
            for(int j=1; j<=K; j++){ // 버틸수있는 무게까지 
                if(arr[i][0] > j){ // 현재 무게보다 작으면
                    dp[i][j] = dp[i-1][j]; // 이전 물건의 최대 가치
                } else { // 현재 무게보다 크거나 같으면
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-arr[i][0]] + arr[i][1]);
                }
            }
            
        }

        result = dp[N][K];
    }


    public static void input() throws IOException {
        st= new StringTokenizer(br.readLine().trim()," ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1][2];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine().trim()," ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // System.out.println(Arrays.deepToString(arr));
        result = 0;

    }
}
