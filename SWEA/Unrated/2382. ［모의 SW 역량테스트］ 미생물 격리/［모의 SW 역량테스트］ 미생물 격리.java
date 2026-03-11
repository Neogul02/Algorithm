import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2382. 미생물 격리
 * @author neogul02
 * 
 * - N * N 크기의 격자, M개의 미생물 군집, K시간
 * 
 * 생각해야할거
 * 0. k 시간동안 시뮬레이션 하는데
 * 1. 미생물 애들을 모두 이동하고나서
 *  1-1. 이동하는법? (dir에 따라서 x,y 좌표 변화)
 *  if (dir == 1) x--; // 상
 *  if (dir == 2) x++; // 하
 *  if (dir == 3) y--; // 좌
 *  if (dir == 4) y++; // 우
 * 
 * 2. 같은 위치에 있으면 합치기
 *  2-1. a.x = b.x && a.y = b.y => 같은 위치
 *  2-2. 합치는법? num = a.num + b.num
 * 
 * 3. 만약 격자 가장자리에 위치해있으면
 *  3. 가장자리를 판단하는법? (x==0, x==N-1, y==0, y==N-1)
 * 
 * 4. 미생물 수 절반으로 줄이고, 방향 반대로 바꿔주기
 *  4-1. 미생물 수 절반으로 줄이는법? (num/2) <- int 
 *  4-2. 방향 반대로 바꿔주는법? (1->2, 2->1, 3->4, 4->3)
 *  
 * 5. K시간 시뮬레이션이 끝나면 남아있는 미생물 수의 총합
 * 
 * - 미생물 군집 정보를 저장하는 클래스 (x, y, num, dir)
 * - 두 군집이 합쳐져서 큰곳에 작은 군집이 합쳐지면, 작은군집은 num이 0으로 처리하고 continue 해서 무시하기
 * 
 * #1 fail => testCase 50개 중 41개 맞음
 * -> 가장자리 처리를 먼저하고 합치기를 나중에 해봄
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N; // 격자 크기
    static int M; // 격리 시간 -> M 만큼 시뮬레이션
    static int K; // 미생물 군집 수

    static Microbe[] microbes; // 미생물 군집 정보 저장 배열

    public static class Microbe {
        int x; // 세로 위치
        int y; // 가로 위치
        int num; // 미생물 수
        int dir; // 이동방향 (1: 상, 2: 하, 3: 좌, 4: 우)

        public Microbe(int x, int y, int num, int dir) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            input(); // 입력받기
            solve(tc); // 풀이
        }
        System.out.print(sb);
    }
    
    public static void solve(int tc) {
        // 0. M시간 동안 시뮬레이션
        for (int time = 0; time < M; time++) {
            // 1. 미생물 애들을 모두 이동
            moveMicrobes();

            // 2. 만약 격자 가장자리에 위치해있으면 
            // 3. 미생물 수 절반으로 줄이고, 방향 반대로 바꿔주기 (먼저!)
            isMicorbesOnEdge();

            // 4. 같은 위치에 있으면 합치기 (나중에!)
            combineMicrobes();

            // 1~4 과정을 M시간 동안 반복
        }
        // 5. K시간 시뮬레이션이 끝나면 남아있는 미생물 수의 총합
        int totalMicrobes = 0;
        for (int i = 0; i < K; i++) {
            totalMicrobes += microbes[i].num;
        }
        sb.append("#").append(tc).append(" ").append(totalMicrobes).append("\n");
    }
    
    public static void moveMicrobes() {
        for (int i = 0; i < K; i++) {
            Microbe m = microbes[i];
            if (m.num == 0)
                continue; // 이미 합쳐져서 num이 0인 군집은 무시

            // 이동방향에 따라 x, y 좌표 변화
            if (m.dir == 1)
                m.x--; // 상
            else if (m.dir == 2)
                m.x++; // 하
            else if (m.dir == 3)
                m.y--; // 좌
            else if (m.dir == 4)
                m.y++; // 우
        }
    }
    
    public static void combineMicrobes() {
        // 같은 위치에 있는 군집들을 합치는 로직 구현
        for (int i = 0; i < K; i++) {
            Microbe m1 = microbes[i];
            if (m1.num == 0)
                continue; // 이미 합쳐져서 num이 0인 군집은 무시

            int maxNum = m1.num; // 같은 위치에서 가장 큰 미생물 수
            int maxDir = m1.dir; // 가장 큰 군집의 방향
            int totalNum = m1.num; // 합쳐질 총 미생물 수

            for (int j = i + 1; j < K; j++) { // 다음 군집부터 비교하기
                Microbe m2 = microbes[j];
                if (m2.num == 0)
                    continue; // 이미 합쳐져서 num이 0인 군집은 무시

                // 같은 위치에 있으면
                if (m1.x == m2.x && m1.y == m2.y) {
                    totalNum += m2.num; // 총 미생물 수에 더하기
                    
                    // 원래 미생물 수가 더 많으면 그 군집의 방향으로 업데이트
                    if (m2.num > maxNum) {
                        maxNum = m2.num;
                        maxDir = m2.dir;
                    }
                    
                    m2.num = 0; // m2는 합쳐졌으므로 num을 0으로 처리
                }
            }

            // 합쳐진 결과를 m1에 저장
            m1.num = totalNum;
            m1.dir = maxDir;
        }
    }

    public static void isMicorbesOnEdge() {
        for (int i = 0; i < K; i++) {
            Microbe m = microbes[i];
            if (m.num == 0)
                continue; // num이 0인 군집은 무시

            // 격자 가장자리를 판단하는법? (x==0, x==N-1, y==0, y==N-1)
            if (m.x == 0 || m.x == N - 1 || m.y == 0 || m.y == N - 1) {
                // 미생물 수 절반으로 줄이는법? (num/2) <- int 
                m.num /= 2;
                if(m.num== 0) continue; // num이 0이 되면 무시

                // 방향 반대로 바꿔주는법? (1->2, 2->1, 3->4, 4->3)
                if (m.dir == 1)
                    m.dir = 2; // 상 -> 하
                else if (m.dir == 2)
                    m.dir = 1; // 하 -> 상
                else if (m.dir == 3)
                    m.dir = 4; // 좌 -> 우
                else if (m.dir == 4)
                    m.dir = 3; // 우 -> 좌
            }
        }
    }   
    
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken()); // 격자 크기
        M = Integer.parseInt(st.nextToken()); // 격리시간
        K = Integer.parseInt(st.nextToken()); // 군집 수

        microbes = new Microbe[K]; // 미생물 클래스 저장 배열

        // 미생물 군집의 수만큼 반복하며 각 군집 위치 저장
        for (int i = 0; i < K; i++) {
            
            st = new StringTokenizer(br.readLine().trim(), " ");

            int x = Integer.parseInt(st.nextToken()); // 세로 위치
            int y = Integer.parseInt(st.nextToken()); // 가로 위치
            int num = Integer.parseInt(st.nextToken()); // 미생물 수
            int dir = Integer.parseInt(st.nextToken()); // 이동방향
            // 이동방향 (1: 상, 2: 하, 3: 좌, 4: 우)
            microbes[i] = new Microbe(x, y, num, dir); // 미생물 군집 정보 저장
        }
        
    }
}
