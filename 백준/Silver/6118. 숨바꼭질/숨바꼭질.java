import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void solve() {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        dist[1] = 0;

        while (!queue.isEmpty()) {
            int temp = queue.poll();

            for (int next : graph[temp]) {
                if (dist[next] != -1) {
                    continue;
                }

                dist[next] = dist[temp] + 1;
                queue.add(next);
            }
        }

        int maxDist = -1;
        for (int i = 1; i <= n; i++) {
            if (dist[i] > maxDist) {
                maxDist = dist[i];
            }
        }

        int minBarn = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == maxDist) {
                if (i < minBarn) {
                    minBarn = i;
                }
                count++;
            }
        }

        System.out.print(minBarn + " " + maxDist + " " + count);
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }
    }
}