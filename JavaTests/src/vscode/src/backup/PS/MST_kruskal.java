package backup.PS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 그래프 표현법
 * - 정점 중심 : 인접 행렬, 인접 리스트
 * - 간선 중심 : 간선 리스트
 */
public class MST_kruskal {

    static class Edge implements Comparable<Edge> {
        int start; // 정점 start -> 정점 end 의 간선
        int end;
        int weight; // 가중치

        public Edge(int start, int end, int weight) {
            super();
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        // 가중치 기준으로 오름차순 정렬하는 implemet Comparable->
        // 원리 : 간선 리스트를 가중치 기준으로 정렬한 다음
        // 사이클이 생기지 않는 간선들을 선택해서 트리를 만들어야 하기 때문에 
        // 가중치 기준으로 오름차순 정렬해야 함.
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static Edge[] edgeList; // 간선 리스트
    static int[] parents; // 부모 노드 저장하는 배열
    static int V, E; // 정점의 수, 간선의 수

    // @makeSet 초기 자기자신이 부모인 서로소 집합 초기화
    static void makeSet() {
        parents = new int[V];
        for (int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }

    // @findSet find -> a가 속한 집합의 부모(대표)를 반환 
    static int findSet(int a) {
        if (parents[a] == a) // 자기 자신이 부모인 경우
            return a;

        return parents[a] = findSet(parents[a]); // path compression
    }

    // @union a랑 b가 속한 집합을 합치자 = 합집합 연산, + a의 대표를 b의 대표로 바꿔주기
    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) // 이미 같은 집합인 경우
            return false;

        parents[bRoot] = aRoot; // b의 대표를 a의 대표로 바꿔주기
        return true;
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 1. 간선 리스트 만들기
        edgeList = new Edge[E]; // 간선의 갯수만큼 만들기

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(start, end, weight);
        }

        // 2. 간선 리스트 가중치 기준으로 오름차순 정렬하기
        Arrays.sort(edgeList);

        // 3. V개의 정점 서로소 집합을 초기화 하기
        makeSet();

        // 4. 간선 리스트를 순회하면서 사이클이 생기지 않는 간선들을 선택해서 트리를 만들기
        // + 사이클이 없다는 가정 하에 
        int cnt = 0; // 선택한 간선의 수
        int result = 0; // 선택한 간선의 가중치 합
        for (Edge edge : edgeList) {

            if (union(edge.start, edge.end) == true) {

                // 선택한 간선의 가중치 합
                result += edge.weight;

                // 간선의 수가 V-1개면 트리가 완성된 것 = 탈출
                if (cnt++ == V - 1)
                    break;

            }

        }

        // 5. 결과 출력하기
        System.out.println(result);

    }

}
// + 몇개의 집합인가? == 집합 덩어리가 몇 개인가? == 대표의 수 
// == findSet의 결과가 다르다 = > 대표의 수가 1개면 모든 정점이 연결된 것 = 트리가 완성된 것

// + 각 집합의 구성요소가 몇 개 인가? 

// 초기화 -> 정렬 -> 

/*
5 10
0 1 5
0 2 10
0 3 8
0 4 7
1 2 5
1 3 3
1 4 6
2 3 1
2 4 3
3 4 1

=> 10

4 5
0 1 5
0 2 6
0 3 7
1 2 8
1 3 9

=> 18

 */