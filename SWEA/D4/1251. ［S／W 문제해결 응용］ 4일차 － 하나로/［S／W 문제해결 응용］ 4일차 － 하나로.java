import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1251. 하나로
 * @author neogul02
 * 
 * - N개의 섬을 모두 해저 터널로 연결
 * - 세금(E) x 해저터널의 길이(L)의 제곱 = 환경부담금
 * - 환경 부담금을 최소로 하여 모든 섬을 연결하고 출력
 * - 크루스칼 알고리즘으로 풀이
 * - 각 섬간의 거리 = 가중치
 * 
 * @see input
 * 1. 첫 번째 줄에는 섬의 개수 N
 * 2. 두 번째 줄에는 각 섬들의 x좌표
 * 3. 세 번째 줄에는 각 섬들의 y좌표
 * 4. 네 번째 줄에는 환경 세율 실수 E
 * 
 * @see solve   
 * 1. 모든 섬들을 순회하면서 거리를 계산하여 edges에 저장한다.
 * 2. edge를 distance기준 오름차순 정렬한다.
 * 3. makeSet으로 집합 초기화한다. 
 * 4. 모든 섬을 돌며 최적 간선을 선택해 result에 더한다.
 * 5. result를 반올림하여 sb에 저장한다.
 * 
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N; // 섬의 갯수

    static int[] x; // 섬의 x좌표
    static int[] y; // 섬의 y좌표

    static double E; // 환경 세율 실수

    public static class Edge{
        int from;
        int to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    // --- Union Find ---
    static int parent[]; // 부모노드 저장
    static Edge[] edgeList; // 간선 배열

    // 1. makeSet : 자기 자신이 부모인 서로소 집합 초기화
    static void makeSet() {
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    // 2. findSet : a가 속한 집합의 대표(부모)를 반환
    static int findSet(int a) {
        if (parent[a] == a) // 자기 자신이 부모인 경우
            return a;

        return parent[a] = findSet(parent[a]); // path compression
    }

    // 3. union : a와 b가 속한 집합을 합치자 = 합집합 연산, + a의 대표를 b의 대표로 바꿔주기
    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) // 이미 같은 집합인 경우
            return false;

        parent[bRoot] = aRoot; // b의 대표를 a의 대표로 바꿔주기
        return true;
    }

    // ------------------------------------------

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            sb.append('#').append(tc).append(' ');
            input();
            solve();
        }
        System.out.print(sb);
    }

    public static void solve() {
        

        // 1. 모든 섬들을 순회하면서 거리를 계산하여 edges에 저장한다.
        edgeList = new Edge[N * (N - 1) / 2];
        int idx = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                double dist = Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2);
                double weight = E * dist;
                edgeList[idx++] = new Edge(i, j, weight);
            }
        }

        // 2. edge를 가중치 기준으로 오름차순 정렬한다.
        Arrays.sort(edgeList, (a, b) -> Double.compare(a.weight, b.weight));

        // 3. 집합 초기화
        makeSet();

        // 4. 모든 섬을 돌며 최적 간선을 선택해 result에 더한다.
        double result = 0;
        int cnt = 0;
        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
                result += edge.weight;
                if (++cnt == N - 1)
                    break;
            }
        }

        // 5. result를 반올림하여 sb에 저장한다.
        sb.append(Math.round(result)).append('\n');
    }
    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim()); // 섬의 개수 N
        
        x = new int[N];
        y = new int[N];
        
        st = new StringTokenizer(br.readLine().trim()); // 2번째 줄에는 각 섬들의 x좌표
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine().trim()); // 3번째 줄에는 각 섬들의 y좌표
        for (int i = 0; i < N; i++) {
            y[i] = Integer.parseInt(st.nextToken());
        }
        
        E = Double.parseDouble(br.readLine().trim()); // 4번째 줄에는 환경 세율 실수 E
    }
}
