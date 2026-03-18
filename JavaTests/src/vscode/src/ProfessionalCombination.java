import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 5607. 조합
 * @author neogul02
 * 
 * 
 * 페르마의 소정리 = a^(p-1) ≡ 1 (mod p) -> a^(p-2) ≡ a^(-1) (mod p)
 */
public class ProfessionalCombination {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, R;
    static final int MOD = 1234567891;

    static long[] factorial; // 팩토리얼 저장 배열

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        initFactorials(1000000);
        for (int tc = 1; tc <= T; tc++) {
            input();
            solve(tc);
            
        }
        System.out.print(sb);
    }

    public static void initFactorials(int max) {
        // 팩토리얼을 미리 계산하여 저장
        factorial = new long[max + 1];
        factorial[0] = 1; // 0! = 1;
        
        for (int i = 1; i <= max; i++) {
            factorial[i] = (factorial[i - 1] * i) % MOD;
        }

        // System.out.println(Arrays.toString(factorial));
    }

    // 조합 공식 nCr = n! / (r! * (n-r)!)
    // n! * (r! * (n-r)!)^-1
    // n! * (r!)^-1 * ((n-r)!)^-1
    public static void solve(int tc){
        long result = 0;

        long nFac = factorial[N];
        long rFac = factorial[R];
        long nMinusR = factorial[N - R];

        // (r! * (n-r)!)^-1 계산
        // 페르마의 소정리 = a^(p-1) ≡ 1 (mod p) -> a^(p-2) ≡ a^(-1) (mod p)
        long reverseRfac = pow(rFac, MOD -2) % MOD;
        long reverseNMinusRfac = pow(nMinusR, MOD -2) % MOD;

        result = nFac * reverseRfac % MOD;
        result = result * reverseNMinusRfac % MOD;

        sb.append("#").append(tc).append(" ")
            .append(result).append("\n");
    }

    //
    private static long pow(long base, int exp) {

        long result = 1;
        long cur = base;

        while (exp > 0) {
            if ((exp & 1)==1) { // exp가 홀수면
                result = (result * cur) % MOD; // result에 cur 곱하기

            }
            cur = (cur * cur) % MOD; // cur 제곱하기
            exp >>= 1; // exp를 오른쪽으로 한 비트 시프트 (2로 나누기)

        }
        return result;

    }

    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
    }
}
