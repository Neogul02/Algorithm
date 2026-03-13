package MST;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1251. 하나로
 * @author neogul02
 * 
 * - N개의 섬을 모두 해저 터널로 연결
 * - 세금(E) x 해저터널의 길이(L)의 제곱 = 환경부담금
 * - 환경 부담금을 최소로 하여 모든 섬을 연결하고 출력
 * - 프림 알고리즘으로 풀이
 * - 각 섬간의 거리 = 가중치
 * 
 * @see input
 * 1. 첫 번째 줄에는 섬의 개수 N
 * 2. 두 번째 줄에는 각 섬들의 x좌표
 * 3. 세 번째 줄에는 각 섬들의 y좌표
 * 4. 네 번째 줄에는 환경 세율 실수 E
 * 
 * @see solve   
 * 1. 인접리스트 그래프 생성
 *  1-1. 섬 쌍마다 거리 계산하여 양방향 간선 추가
 *  1-2. 거리 계산 x차이, y차이의 제곱
 *  1-3. 
 * 2. 프림 알고리즘으로 MST 구하기
 *  2-1. PQ 초기화
 *  2-2. 초기 탐색위치 0,0 으로 PQ에 넣기
 *  2-3. PQ에서 최소 간선 뽑아서 MST에 추가
 *  2-4. MST에 없는 다른 정점으로 가는 간선들을 PQ에 추가
 * 3. MST 비용 * E 계산하여 출력
 * 
 */
public class HanaroPrim {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N; // 섬의 갯수

    static int[] x; // 섬의 x좌표
    static int[] y; // 섬의 y좌표

    static double E; // 환경 세율 실수

    static class Node {
        int to;
        double weight; // double로 해야 거리 계산 정확
        Node next;

        Node(int to, double weight, Node next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }
    }

    static class EdgeTo {
        int v;
        double w; // 정렬 기준, double

        EdgeTo(int v, double w) {
            this.v = v;
            this.w = w;

        }

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

            // 1. 인접 리스트 그래프 생성
            Node[] adj = new Node[N];
            // 섬 쌍 구현하기 -> 양방향 그래프 
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    // 거리의 제곱 계산: (x차이)² + (y차이)²
                    long dx = (long) (x[i] - x[j]);
                    long dy = (long) (y[i] - y[j]);
                    double dist = dx * dx + dy * dy;

                    // 양방향 그래프
                    adj[i] = new Node(j, dist, adj[i]);
                    adj[j] = new Node(i, dist, adj[j]);
                }
            }

            // 2. 프림 알고리즘
            boolean[] visited = new boolean[N];
            PriorityQueue<EdgeTo> pq = new PriorityQueue<>((a, b) -> Double.compare(a.w, b.w)); // 가중치 기준 오름차순
            pq.add(new EdgeTo(0, 0)); // 시작점 0, 비용 0

            int pickCnt = 0; // MST에 선택된 정점 수
            double mstCost = 0; // 총 비용

            while (pq.isEmpty() != true) {
                // 탈출 조건 - MST에 모든 정점이 선택되면 종료
                if (pickCnt == N)
                    break;

                EdgeTo temp = pq.poll();

                if (visited[temp.v])
                    continue; // 이미 MST에 포함된 정점이면 무시

                // MST에 temp.v 추가
                visited[temp.v] = true;
                mstCost += temp.w;
                pickCnt++;

                // temp.v에서 갈 수 있는 간선들을 PQ에 후보로 추가
                for (Node e = adj[temp.v]; e != null; e = e.next) {
                    if (visited[e.to] == true)
                        continue; // 이미 MST에 포함된 정점으로 가는 간선이면 무시
                    else { // MST에 없는 다른 정점은 갈 수 있음 -> PQ에 후보로 추가
                        pq.add(new EdgeTo(e.to, e.weight));
                    }
                }
            }

            double result = mstCost * E; // 환경 부담금 계산
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
}