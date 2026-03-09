import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11401. 이항계수3
 * @author neogul02\
 * 
 * - 자연수 N과 정수 K 주어짐
 * - N개중에 K개를 고르는 경우의 수 = 이항계수 = N! / (K! * (N-K)!)
 * - 1 <= N <= 4,000,000 최대 400만 이므로 팩토리얼 계산 불가능
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int MODNUM = 1_000_000_007; // 10억 + 7

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 1. 미리 팩토리얼 값 계산해두기
        long[] factorial = new long[N + 1]; // 팩토리얼 저장할 배열
        factorial[0] = 1; // 0! = 1
        for (int i = 1; i <= N; i++) {
            // 팩토리얼 계산, 만약 값이 10억7보다 크면 ~ 
            factorial[i] = factorial[i - 1] * i % MODNUM;
        }

        // 2. N개중에서 K개를 고르는 경우의 수는 N! / (K! * (N-K)!)
        // 5C3 = 5! / (3! * (5-3)!)
        // NCK = N! / (K! * (N-K)!)
        // N! * (K! * (N-K)!)^-1
        // N! * (K!)^-1 * ((N-K)!)^-1
        long answer = factorial[N]; // N!

        answer = answer * pow(factorial[K], MODNUM - 2) % MODNUM; // K!^-1
        answer = answer * pow(factorial[N - K], MODNUM - 2) % MODNUM; // (N-K)!^-1

        System.out.print(answer);
    }
    
    private static long pow(long base, int exp) {

        long result = 1;
        long cur = base;

        while (exp > 0) {
            if (exp % 2 == 1) { // exp가 홀수면
                result = (result * cur) % MODNUM; // result에 cur 곱하기

            }
            cur = (cur * cur) % MODNUM; // cur 제곱하기
            exp >>= 1; // exp를 오른쪽으로 한 비트 시프트 (2로 나누기)
            
        }
        return result;
    }
}