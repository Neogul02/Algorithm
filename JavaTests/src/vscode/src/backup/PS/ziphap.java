package backup.PS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1717. 집합의 표현
 * @author neogul02
 * 
 * 1. N, M 입력, N은 N+1개 집합을 의미, M은 연산의 개수 
 * 2. 합집합 => union => 0 a b => a와 b가 포함된 집합을 합친다.
 * 3. 같은 집합 여부 => find => 1 a b => a와 b가 포함된 집합이 같은지 확인한다.
 * 4. 같으면 "YES", 다르면 "NO" 를 한 줄씩 출력
 */
public class ziphap {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] parents; // 서로소 트리 헤드
    static int[] commands; // 명령어

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            sb.append('#').append(tc).append(' ');
            solve();
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static void solve() throws IOException {

        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command == 0) {
                union(a, b);
            } else { // command == 1
                if (find(a) == find(b)) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }

            }
        }
    }
    
    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i; // 자기 자신이 부모인 상태로 초기화
        }
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parents[rootB] = rootA; // rootB의 부모를 rootA로 설정
        }
    }

    public static int find(int x) {
        if (parents[x] != x) {
            parents[x] = find(parents[x]); // 경로 압축
        }
        return parents[x];

    }
}