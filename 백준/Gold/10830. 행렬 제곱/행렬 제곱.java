import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 10830. 행렬 제곱
 * @author neogul02
 * 
 * - N*N 행렬 A와 정수 B가 주어질 때, A^B를 구하는 문제
 * - N은 최대 5, B는 최대 100,000,000,000 ??? 
 * - B가 1000억 = 10^11 이므로 O(B) > 로 풀이해야함
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static long B;
    static int[][] arr;

    static final int MODNUM = 1000; // 결과값을 1000으로 나눈 나머지로 출력

    public static void main(String[] args) throws IOException {
        input();
        pow();
        System.out.print(sb);
    }

    public static void pow() {
        // 행렬의 B제곱을 계산하기
        // B가 최대 1000억이라 분할 제곱으로 계산하기

        int[][] result = new int[N][N]; // 결과 행렬
        // 행렬의 곱셈이므로 1 행렬로 초기화
        for (int i = 0; i < N; i++) {
            result[i][i] = 1;
        }

        int[][] base = arr; // 초기 행렬
        long exp = B; // 지수

        while (exp > 0) {
            if ((exp & 1) == 1) { // exp가 홀수면
                result = multiply(result, base); // 결과 행렬에 base 곱하기
            }
            // 짝수면 base를 제곱하기
            base = multiply(base, base); // base를 제곱
            exp >>= 1; // exp를 오른쪽으로 한 비트 시프트 (2로 나누기)
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(' '); // 결과 행렬 출력
            }
            sb.append('\n');
        }
    }

    public static int[][] multiply(int[][] A, int[][] B) {
        int[][] result = new int[N][N]; // 곱셈 결과 행렬

        /** 행렬 곱셈 공식 
         * [0][0] = A[0][0]*B[0][0] + A[0][1]*B[1][0]
         * [0][1] = A[0][0]*B[0][1] + A[0][1]*B[1][1]
         * [1][0] = A[1][0]*B[0][0] + A[1][1]*B[1][0]
         * [1][1] = A[1][0]*B[0][1] + A[1][1]*B[1][1]
         */
        for (int i = 0; i < N; i++) { // A의 i행
            for (int j = 0; j < N; j++) { // B의 j열
                long sum = 0; 
                for (int k = 0; k < N; k++) {
                    sum += (long) (A[i][k] * B[k][j]); // A의 i행과 B의 j열의 곱을 누적
                }
                result[i][j] = (int) (sum % MODNUM); // 결과값을 1000으로 나눈 나머지로 저장
            }
        }
        return result;
    }

    
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }    
}
