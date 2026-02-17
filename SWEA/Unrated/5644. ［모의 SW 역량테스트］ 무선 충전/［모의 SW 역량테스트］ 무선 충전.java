import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 5644. 무선 충전 - 두 사용자의 무선 충전 시뮬레이션
 * @author neogul02
 *
 * 10x10 지도에서 두 명의 사용자(A, B)가 이동하면서
 * 무선 충전 베이스 스테이션(BC)으로부터 충전을 받는 시뮬레이션을 처리한다.
 * 이동 방향 코드: 0(이동없음), 1(상), 2(우), 3(하), 4(좌)
 */
public class Solution {
    static final int SIZE = 10;
    static final int[] dr = {0, -1, 0, 1, 0};
    static final int[] dc = {0, 0, 1, 0, -1};

    static int M, A;
    static int[] moveA, moveB;
    static int[] posA, posB;
    static int[][] bc; // {r, c, coverage, power}
    static ArrayList<Integer>[][] coverage;

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // 테스트 케이스를 입력
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {

            input();
            buildCoverage();
            int answer = simulate();

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    // M: 총 이동 시간, A: BC의 개수
    // 사용자 A와 B의 이동 경로를 입력받는다.
    // 각 BC의 정보 (좌표, 충전 범위, 충전량)를 입력받는다.
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());

        moveA = new int[M];
        moveB = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            moveA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            moveB[i] = Integer.parseInt(st.nextToken());
        }

        bc = new int[A][4];
        for (int i = 0; i < A; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            bc[i][0] = y;
            bc[i][1] = x;
            bc[i][2] = c;
            bc[i][3] = p;
        }

        posA = new int[]{0, 0};
        posB = new int[]{SIZE - 1, SIZE - 1};
    }

    // 각 위치별로 충전 가능한 BC의 목록을 미리 계산하여 저장.
    // 10x10 지도의 모든 위치에 대해, 맨해튼 거리가 BC의 충전 범위 이내인 BC들의 인덱스를 저장.
    static void buildCoverage() {
        // coverage[r][c] : 위치 (r, c)에서 충전 가능한 BC의 인덱스 리스트
        // 최대 8개의 bc가 겹칠 수 있는가? ..? test
        coverage = new ArrayList[SIZE][SIZE];
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                coverage[r][c] = new ArrayList<>();
            }
        }

        for (int i = 0; i < A; i++) {
            int br = bc[i][0]; // BC의 행 좌표
            int bcC = bc[i][1]; // BC의 열 좌표
            int C = bc[i][2]; // BC의 충전 범위

            for (int r = 0; r < SIZE; r++) {
                for (int c = 0; c < SIZE; c++) {
                    int dist = Math.abs(r - br) + Math.abs(c - bcC); // 충전 범위 계산 식
                    // 멘헤튼 거리 계산: |r - br| + |c - bcC| ?? 
                    if (dist <= C) {
                        coverage[r][c].add(i);
                    }
                }
            }
        }
    }

    // 무선 충전 시뮬레이션
    // 초기 위치(0초)에서의 충전을 포함하여, M번의 이동 각각 후에 충전량을 계산하고 이를 모두 합산하여 반환한다.
    static int simulate() {
        int total = 0;

        // 0초일때
        total += charge();

        for (int t = 0; t < M; t++) { // M 번 이동
            move(moveA[t], posA);
            move(moveB[t], posB);
            total += charge();
        }

        return total;
    }

    // 주어진 방향으로 위치를 이동
    // 0(이동없음), 1(상), 2(우), 3(하), 4(좌)
    static void move(int dir, int[] pos) {
        pos[0] += dr[dir];
        pos[1] += dc[dir];
    }

    // 현재 시점에서 두 사용자가 받을 수 있는 최대 충전량을 계산한다.
    // 사용자 A와 B의 현재 위치에서 접근 가능한 BC들을 확인하고 최대 충전량을 계산한다.
    // 둘 다 BC가 없으면 0, 한 명만 있으면 그 사용자의 최대값, 둘 다 있으면 모든 조합 중 최대값을 반환한다.
    static int charge() {
        // A와 B가 충전할 수 있는 BC의 인덱스 리스트
        ArrayList<Integer> aList = coverage[posA[0]][posA[1]];
        ArrayList<Integer> bList = coverage[posB[0]][posB[1]];

        int max = 0;

        // 둘 다 충전할 수 있는 BC가 없을 때
        if (aList.isEmpty() && bList.isEmpty()) return 0;

        if (aList.isEmpty()) { // A가 충전할 수 있는 BC가 없을 때, B의 최대 충전량을 계산
            for (int b : bList) {
                max = Math.max(max, bc[b][3]); // [3] 는 충전량, 가장 큰 충전량을 찾는다.
            }
            return max;
        }

        if (bList.isEmpty()) { // B가 충전할 수 있는 BC가 없을 때, A의 최대 충전량을 계산
            for (int a : aList) {
                max = Math.max(max, bc[a][3]); // [3] 는 충전량, 가장 큰 충전량을 찾는다.
            }
            return max;
        }

        for (int a : aList) {
            for (int b : bList) {
                int sum;
                if (a == b) sum = bc[a][3];
                else sum = bc[a][3] + bc[b][3];

                max = Math.max(max, sum);
            }
        }

        return max;
    }
}