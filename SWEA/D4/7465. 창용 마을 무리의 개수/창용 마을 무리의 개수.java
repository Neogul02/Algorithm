import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 7465. 창용 마을 무리의 개수
 * @author neogul02
 * 
 * - 서로를 알고 있는 관계가 공백으로 주어진다 = 그래프? 유니온 집합? 
 * 1. 서로소 집합 트리를 이용해서 합집합을 한다.
 * 2. 모든 원소를 탐색하면서, 자기 자신이 부모인 경우, 즉 루트 노드인 경우 ++
 * 
 * + 입력에서 원소가 하나만 입력되는 경우가 있다고 함
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static int[] parents; // 서로소 트리 헤드
    static int muriCnt; // 무리 개수


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(' ');
            // 1. 입력 처리
            input();

            // 2. 풀이
            solve();

            sb.append(muriCnt).append("\n");
        }
        System.out.print(sb);
    }

    public static void solve() throws IOException {
        // 1. 서로소 집합 트리 초기화
        init();

        // 2. 서로소 집합 트리 합집합
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            int a = Integer.parseInt(st.nextToken());

            if (st.hasMoreTokens()) { // a와 b가 모두 입력된 경우
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            } 
            else { // a만 입력된 경우, 자기 자신과 합집합
                union(a, a); // 자기 자신과 합집합
            }
        }

        // 3. 무리 개수 세기
        for (int i = 1; i <= N; i++) {
            // 모든 원소를 탐색하면서, 자기 자신이 부모인 경우, 즉 루트 노드인 경우 무리 개수 증가
            if (parents[i] == i) {
                muriCnt++;
            }
        }
    }
    
    public static int find(int a) {
        // 자기 자신이 부모인 경우, 즉 루트 노드인 경우 a를 반환
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]); // 압축
    }
    
    public static void union(int a, int b) {

        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parents[rootB] = rootA; // rootB의 부모를 rootA로 설정
        }
    }

    public static void init() {

        parents = new int[N+1];
        for(int i=0; i<=N; i++){
            parents[i] = i; // 자기 자신이 부모인 상태로 초기화
        }
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 무리 개수 초기화
        muriCnt = 0;
    }
}