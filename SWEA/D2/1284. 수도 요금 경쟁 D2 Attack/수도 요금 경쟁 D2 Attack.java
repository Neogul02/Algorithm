import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/**
 * 1284. 수도 요금 경쟁
 * @author neogul02
 * 
 * 두 수도 회사중 요금이 더 싼 곳을 고를거임
 * 1. 테스트 케이스를 입력받는다
 * 2. A 회사는 1리터당 P원을 낸다
 * 3. B 회사는 기본요금이 Q원이고 R리터 이하로 사용하면 기본요금, 초과분은 1리터당 S원
 * 4. 종민이가 사용하는 수도 양은 W
 * 
*/
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int P; // A회사
    static int Q, R, S; // B 회사
    static int W; // 사용 수도량
 
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine().trim());
        for(int tc=1; tc<=T; tc++){
            input();
            solve(tc);
        }
        System.out.print(sb);
    }

    public static void solve(int tc){
        int ACompany = 0;
        int BCompany = 0;

        // A회사 요금 계산
        ACompany = P * W ; // 1리터당 요금 x 사용량

        // B 회사 요금 계산
        // R리터 이하일때
        if(W < R || W == R){
            BCompany = Q; // 기본요금
        }
        // R리터 초과일때
        if(R < W){
            BCompany = Q + S * (W - R); // 기본요금 + 초과량(S) * (사용량 - R리터)
        }

        int minCompany = Math.min(ACompany, BCompany);

        sb.append("#").append(tc).append(" ").append(minCompany).append("\n");

    }

    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine().trim());
        P = Integer.parseInt(st.nextToken()); // 1리터당 P 원
        Q = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
    }
}