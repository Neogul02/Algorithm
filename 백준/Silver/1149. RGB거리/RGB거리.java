import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1149. RGB 거리
 * @author neogul02
 * 
 * 1. 집의 수 N이 주어진다.
 * 2. N개의 줄에 빨강 초록 파랑 순서대로 비용이 주어진다.
 * 3. N번 집은 N-1번 집의 색과 같지 않아야 함
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N;
    static int[][] dp;
    static int[][] cost;

    public static void main(String[] args) throws IOException{
        input();
        solve();

        System.out.print(Math.min(dp[N-1][0],Math.min(dp[N-1][1],dp[N-1][2])));
    }
    
    public static void solve(){
        dp = new int[N][3];

        // 초기 값 세팅
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];  
        dp[0][2] = cost[0][2];

        // i번째집 = i번째 비용 + 이전 집 색깔 2개 중 최솟값
        for(int i=1; i<N; i++){
            dp[i][0] = cost[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = cost[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = cost[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

    }

    public static void input() throws IOException{
        N = Integer.parseInt(br.readLine().trim());

        // ㅁㅁㅁ
        // ㅁㅁㅁ
        cost = new int[N][3]; // 집을 색칠하는 비용
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine().trim());
            cost[i][0] = Integer.parseInt(st.nextToken()); // R로 색칠하는 비용
            cost[i][1] = Integer.parseInt(st.nextToken()); // G로 색칠하는 비용
            cost[i][2] = Integer.parseInt(st.nextToken()); // B로 색칠하는 비용
        }
    }
}
