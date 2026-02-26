import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 18870. 좌표 압축
 * @author neogul02
 * 
 * 수직선 위에 N개의 좌표 X1, X2... 다른 좌표보다 큰 개수를 세는 문제
 * HashMap을 사용해서 key -> cnt 갯수를 저장
 * 
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N; // 좌표의 개수
    static int[] arr; // 좌표 배열
    
    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.print(sb);
    }

    private static void solve() {
        // 1. 복사본 정렬
        int[] sorted = arr.clone();
        Arrays.sort(sorted); 

        // 2. 중복 제거 + 순위 매핑 (HashMap)
        HashMap<Integer, Integer> map = new HashMap<>();
        int rank = 0;
        for (int val : sorted) {
            if (!map.containsKey(val)) {
                map.put(val, rank++); // 처음 나온 값만 순위 부여
            }
        }

        // 3. 원래 배열에서 순위 출력
        for (int val : arr) {
            sb.append(map.get(val)).append(' ');
        }
    }
    
    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        arr = new int[N];

        st = new StringTokenizer(br.readLine().trim(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // System.out.println(Arrays.toString(arr));
    }
}
