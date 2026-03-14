import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N; // 고객의 수
    static int[] company;
    static int[] home;
    static int[][] customers; // 고객 좌표

    static boolean[] visited; // 방문 여부

    static int minDistance; // 최소 이동 거리

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            input();
            solve();
            sb.append("#").append(tc).append(" ").append(minDistance).append("\n");
        }
        System.out.print(sb.toString());
    }
    
    public static void solve() {
        minDistance = Integer.MAX_VALUE;
        visited = new boolean[N];

        dfs(company, 0, 0); // 회사에서 출발
    }
    
    public static void dfs(int[] current, int depth, int distance) {
        // 모든 고객을 방문한 경우
        if (depth == N) {
            // 집으로 돌아가는 거리 추가
            int totalDistance = distance + distance(current, home);
            minDistance = Math.min(minDistance, totalDistance);
            return;
        }
        
        // 아직 방문하지 않은 고객들을 모두 시도
        for (int i = 0; i < N; i++) {
            
            if (visited[i] == false) {
                visited[i] = true;
                // 다음 고객으로 이동
                int newDistance = distance + distance(current, customers[i]);
                dfs(customers[i], depth + 1, newDistance);
                visited[i] = false; // Backtracking
            }
        }
    }

    // 두 좌표 사이의 맨해튼 거리 계산
    public static int distance(int[] pos1, int[] pos2) {
        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
    }

    public static void input() throws IOException {
        // Line 1: 고객의 수 N
        N = Integer.parseInt(br.readLine().trim());

        // Line 2: 회사 좌표, 집 좌표 + N명의 고객 좌표
        st = new StringTokenizer(br.readLine().trim());
        company = new int[2];
        company[0] = Integer.parseInt(st.nextToken());
        company[1] = Integer.parseInt(st.nextToken());

        home = new int[2];
        home[0] = Integer.parseInt(st.nextToken());
        home[1] = Integer.parseInt(st.nextToken());

        customers = new int[N][2];
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            customers[i][0] = x;
            customers[i][1] = y;
        }
    }
}