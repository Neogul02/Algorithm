import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수제버거장인3421_비트마스킹 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // 제약 조건을 담을 배열
            int[] aArr = new int[M];
            int[] bArr = new int[M];

            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine().trim());
                aArr[m] = Integer.parseInt(st.nextToken());
                bArr[m] = Integer.parseInt(st.nextToken());
            }

            int ans = 0;

            // 1. 모든 부분집합을 비트(subset)로 순회 (0 ~ 2^N - 1)
            // subset 변수 자체가 곧 하나의 버거 레시피입니다.
            for (int subset = 0; subset < (1 << N); subset++) {
                
                boolean isOk = true; // 현재 레시피(subset)가 유효한지 체크

                // 2. M개의 제약 조건 확인
                for (int i = 0; i < M; i++) {
                    // 재료 번호가 1번부터 시작하므로 비트 연산 시 -1 해줌
                    int ingredientA = aArr[i] - 1;
                    int ingredientB = bArr[i] - 1;

                    // A 재료가 포함되어 있고 AND B 재료가 포함되어 있다면?
                    boolean hasA = (subset & (1 << ingredientA)) != 0;
                    boolean hasB = (subset & (1 << ingredientB)) != 0;

                    if (hasA && hasB) {
                        isOk = false; // 궁합 안 맞음! 탈락
                        break; // 더 볼 필요 없음
                    }
                }

                // 3. 통과했다면 카운트 증가
                if (isOk) {
                    ans++;
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}