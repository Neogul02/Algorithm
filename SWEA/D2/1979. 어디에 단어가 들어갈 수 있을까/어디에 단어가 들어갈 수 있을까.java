import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/**
 * 1979. 어디에 단어가 들어갈 수 있을까
 * @author neogul02
 * 
 * 1. 가로줄을 탐색하는데 딱 K칸만 연속된 1, 결과값 +1
 * 2. 세로줄을 탐색하는데 딱 K칸만 연속된 1, 결과값 +1
 * 3. 결과값을 출력한다.
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N; // 가로세로 길이 N
    static int K; // K는 2이상 N이하의 정수

    static int[][] map;

    static int result;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        
        for(int tc=1; tc<=T; tc++){
            sb.append("#").append(tc).append(" ");
            
            input();
            solve(tc);
        }
        System.out.print(sb);
        
    }
    public static void solve(int tc){
        // 1. 가로줄 탐색
        result = 0;
        for(int i=0; i<N; i++){
            int cnt = 0;
            for(int j=0; j<N; j++){
                if(map[i][j] == 1){
                    cnt++;
                } else {
                    if(cnt == K) result++;
                    cnt = 0;
                }
            }
            if(cnt == K) result++;
        }
        
        // 2. 세로줄 탐색
        for(int i=0; i<N; i++){
            int cnt = 0;
            for(int j=0; j<N; j++){
                if(map[j][i] == 1){
                    cnt++;
                } else {
                    if(cnt == K) result++;
                    cnt = 0;
                }
            }
            if(cnt == K) result++;
        }

        // 3. 결과값 출력
        sb.append(result).append("\n");

    }

    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
    }
}