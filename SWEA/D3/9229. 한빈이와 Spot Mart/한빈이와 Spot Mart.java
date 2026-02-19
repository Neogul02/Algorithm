import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 9229. 한빈이와 Spot Mart
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N,M;
    static int[] snacks;

    static int maxWeight;
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine().trim());
        for(int tc=1; tc <= T; tc++) {

            input(); // 입력받기
            solve(); // 풀이

            sb.append("#").append(tc).append(" ")
                    .append(maxWeight).append("\n");
        }

        System.out.print(sb);
    }

    private static void solve() {
        maxWeight = -1;
        dfs(0, 0, 0);
    }

    private static void dfs(int idx, int count, int weightSum) {
        // 기저 조건
        if(weightSum > M) return; // 무게 초과하면 더 이상 탐색할 필요 없음
        if(count == 2) { // 과자 2개 선택한 경우
            maxWeight = Math.max(maxWeight, weightSum);
            return;
        }
        if(idx == N) return; // 모든 과자 탐색 완료
        dfs(idx+1, count+1, weightSum + snacks[idx]); // 현재 과자 선택
        dfs(idx+1, count, weightSum); // 현재 과자 선택하지 않음
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        snacks = new int[N];
        st = new StringTokenizer(br.readLine().trim()," ");
        for(int i=0; i<N; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
        }
        maxWeight = -1; // 불가능한 경우를 위해 -1로 초기화
    }
}