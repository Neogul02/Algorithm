import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1249. 보급로
 * @author neogul02
 * 
 * - 도로 곳곳이 파손됨
 * - 출발지 (S)는 좌상단 0,0 / 도착지 (D)는 우하단 N-1,N-1 고정
 * - 깊이 1이면 복구시간도 1
 * - 4방향 이동 가능
 * 
 * - 가중치 최단경로 문제 -> Dijkstra
 * - 그래프로 graph[from].add(new Edge(to, weight)); 로 초기화를했었는데
 * - 2차원 배열자체가 그래프니까.. 해당 칸에 적혀있는 숫자가 가중치고 각 좌표가 from to 로 보면될듯
 * 
 */

class SupplyRoad {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N; // 지도의 크기
    static int[][] map; // 지도 정보
    static int[][] dist; // 최단 거리 저장 배열

    static final int[] dx = { -1, 1, 0, 0 };
    static final int[] dy = { 0, 0, -1, 1 };
    static final int INF = Integer.MAX_VALUE;

    static class Node {
        int x;
        int y;
        int cost;
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            // input
            input();

            // solve
            dijkstra(tc);
        }
        // output
        System.out.print(sb);
    }
    
    public static void dijkstra(int tc) {
        // min-heap
        PriorityQueue<Node> pq = new PriorityQueue<>((A, B) -> A.cost - B.cost);
        
        // 1. dist Init
        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = INF;
            }
        }
        dist[0][0] = 0; // 출발지 초기화
        pq.add(new Node(0, 0, map[0][0]));

        while (pq.isEmpty() != true) {
            Node temp = pq.poll();
            int x = temp.x;
            int y = temp.y;
            int cost = temp.cost;

            // 목적지 도착하면 종료
            if (x == (N - 1) && y == (N - 1)) {
                sb.append('#').append(tc).append(' ')
                .append(dist[N - 1][N - 1]).append('\n');
                return;
            }

            // 이미 처리된 경로라면 스킵
            if (dist[x][y] < cost) {
                continue;
            }

            // 4방향 탐색
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                // out bounds 
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                // 해당 칸으로 가는 비용 계산
                int newCost = cost + map[nx][ny];

                // 최단 거리 갱신
                if (newCost < dist[nx][ny]) {
                    dist[nx][ny] = newCost;
                    pq.add(new Node(nx, ny, newCost));
                }
            }
        }   
    }

    public static void input() throws IOException{
        // Line 1 : N => N*N map
        N = Integer.parseInt(br.readLine().trim());

        // Line 2 ~ N + 1 => initMap
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        // System.out.println(Arrays.deepToString(map));
    }
}