import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 3124. 최소 스패닝 트리
 * @author neogul02
 * 
 * - 그래프의 모든 정점을 연결하는 부분 그래프 중
 * - 간선의 가중치 합이 최소인 트리를 구하자
 * - 크루스칼 알고리즘 - Union-FindSet 자료구조로 풀이
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int V, E; // 정점의 수, 간선의 수
    static Edge[] edges; // 간선 리스트
    static int[] parents; // 부모 배열

    public static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            input();
            solve(tc);
        }
        System.out.print(sb);
    }
    
    public static void solve(int tc) {
        // 1. 간선 리스트 초기화
        makeSet();

        // 2. 간선 리스트를 가중치 기준으로 오름차순 정렬
        Arrays.sort(edges, (a, b) -> Integer.compare(a.weight, b.weight));

        // 3. 간선 리스트 순회
        long result = 0L;
        for (Edge e : edges) {
            // 4. 간선의 양 끝 정점이 같은 집합에 속하는지 확인
            if (union(e.from, e.to)) {
                // 5. 같은 집합에 속하지 않는 경우, 간선을 선택하고 가중치 합에 더함
                result += e.weight;
            }
        }
        sb.append('#').append(tc).append(' ')
            .append(result).append('\n');
    }
    
    public static void makeSet() {
        parents = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }

    public static int findSet(int a) {
        // a가 부모 노드인 경우, a를 반환
        if (parents[a] == a) {
            return a;
        }
        // a가 부모 노드가 아닌 경우, a의 부모 노드를 찾아서 반환
        return parents[a] = findSet(parents[a]);
    }

    public static boolean  union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        // a와 b가 이미 같은 집합인 경우, 합치지 않음
        if (aRoot == bRoot) {
            return false;
        }

        // a와 b가 다른 집합인 경우, 합침
        parents[bRoot] = aRoot;
        return true;
    }
        
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = new Edge[E];
        parents = new int[V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, weight);
        }
    }
}
