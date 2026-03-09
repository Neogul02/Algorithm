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

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] parents; // 서로소 트리 헤드
    static int[] commands; // 명령어 담아두기

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.print(sb);
    }

    public static void solve() {
        init();
        union();
    }
    
    public static void init() { // 초기 집합 초기화 
        for (int i = 0; i < N + 1; i++) {
            parents[i] = i;
        }
    }
    
    static void union() {
        int idx = 0;
        for (int i = 0; i < M; i++) {
            int order = commands[idx++];
            int rootX = find(commands[idx++]);
            int rootY = find(commands[idx++]);

            if (order == 0) { // 합집합
                if (rootX != rootY) { // 가져온 집합의 루트 값이 다르면 -> 서로소니까
                    parents[rootY] = rootX; // 합칠 수 있음
                }
            }else{ // order == 1, 같은 집합이면
                if (rootX == rootY)
                    sb.append("YES\n");
                else
                    sb.append("NO\n");                    
            }
        }
    }
    
    public static int find(int x) {
        if (parents[x] != x) { // 자기자신이 루트가 아니다 => 부모가 있다.
            parents[x] = find(parents[x]); // 부모의 루트를 찾아서 최고 루트로 갱신
        }
        return parents[x];
    }
    
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        commands = new int[M * 3]; // 3배 입력
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            for (int j = 0; j < 3; j++) {
                commands[i * 3 + j] = Integer.parseInt(st.nextToken());    
            }
        }
    }
}