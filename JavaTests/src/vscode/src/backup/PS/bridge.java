package backup.PS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 17352. 여러분의 다리가 되어 드리겠습니다!
 * @author neogul02
 */
public class bridge {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];

        init();

        // 1. 집합 연결
        for (int i = 1; i <= N - 2; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        // 2. 다른 서로소 집합을 연결하는 다리 출력
        for (int i = 1; i <= N; i++) {
            if (find(i) != find(1)) {
                // 1은 무조건 하나의 집합의 원소이므로 다른 집합의 원소랑 찾아서 연결하면 정답?
                sb.append(1).append(" ").append(i);
                break;
            }
        }
        System.out.print(sb);
    }
    
    public static void init() {
        for (int i = 1; i <= N; i++) {
            parent[i] = 0;
        }
    }

    public static void union(int a, int b) throws IOException {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA != parentB) {
            parent[parentB] = parentA;
        }
    }
    
    public static int find(int a) {
        if (parent[a] == 0) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}
