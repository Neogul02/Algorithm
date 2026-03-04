
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 3273. 두 수의 합
 * @author neogul02
 * 
 * - N개의 서로 다른 양의정수 수열
 * - 1<= N <= 100,000 -> O(N^2) = 10,000,000,000 -> 시간초과 -> O(N log N)이하 필요
 * - N개 원소에서 2개를 골라서 더한 값이 x 면? cnt++;
 * 
 * 1. 입력
 *  1-1. N과 수열 입력
 *  1-2. x 입력
 *  1-3. 입력받은 arr 정렬
 * 
 * 2. 투 포인터 사용
 *  2-1. left = 0, right = N-1
 *  2-2. left가 right보다 작을 때까지 반복
 *  2-3. 두 수를 더한값이 x보다 작으면 left++ (더 큰 수로 이동)
 *  2-4. 두 수를 더한값이 x보다 크면 right-- (더 작은 수로 이동)
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int X; // 목표값

    static int[] numbers;
    static int hitCount;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.print(hitCount);
    }

    private static void solve() {
        // 투포인터 알고리즘
        Arrays.sort(numbers); // 배열 오름차순 정렬

        int leftP = 0;
        int rightP = N - 1;

        while (leftP < rightP) { // 왼쪽 포인터가 right포인터보다 크면 엇갈림
            int sum = numbers[leftP] +numbers[rightP];
            
            if (sum == X) {
                hitCount++;
                leftP++;
                rightP--;
            } 
            else if (sum < X) {
                leftP++;
            } 
            else {
                rightP--;
            }
        }
    }
    
    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim()); // 수열의 크기
        
        st = new StringTokenizer(br.readLine().trim(), " ");
        numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        X = Integer.parseInt(br.readLine().trim());
    }
}