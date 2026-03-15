package testPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 미생물 격리
 * @author neogul02
 * 
 * 이동방향 => 1: 상, 2: 하, 3: 좌, 4: 우
 */
class micro {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, K; // 격자 크기, 격리 시간, 미생물 군집의 수
    static int microbes[][]; // 미생물 군집 정보 저장 배열

    static int[] dx = { 0, -1, 1, 0, 0 }; // 1. 상, 2. 하, 3. 좌, 4. 우
    static int[] dy = { 0, 0, 0, -1, 1 };
    
    static int answer;
 
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            input();
            solve();
            sb.append("#").append(tc).append(" ").append(answer).append('\n');
        }
        System.out.print(sb);
    }

    public static void solve() {
        // 1. M 시간동안 미생물이 이동함 = M번 반복하는 시뮬레이션
        for (int m = 0; m < M; m++) {

            // 2. 미생물을 이동시킨다
            moveMicrobes();

            // 3. 가장자리에 있는 미생물은 절반으로 줄이고 방향을 반대로 바꾼다.
            isMicrobesOnEdge();

            // 4. 같은 셀에 여러 군집이 모이면 하나로 합쳐진다. (크기가 가장 큰 군집의 이동 방향을 따른다.)
            combineMicrobes();

        }
        // 5. M시간이 지난 후 남아있는 미생물 수의 총합을 구한다.
        countMicrobes();
    }
    
    public static void moveMicrobes() {
        for (int i = 0; i < K; i++) {
            int x = microbes[i][0];
            int y = microbes[i][1];
            int count = microbes[i][2];
            int direction = microbes[i][3];

            // 이미 사라진 군집은 건너뛰기
            if(count == 0) {
                continue;
            }

            // 새로운 위치 계산
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            // 아웃바운드 
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue; // 범위를 벗어나면 이동하지 않음
            }

            // 새로운 위치로 이동
            microbes[i][0] = nx;
            microbes[i][1] = ny;
        }
    }
    
    public static void isMicrobesOnEdge() {
        for (int i = 0; i < K; i++) {
            int x = microbes[i][0];
            int y = microbes[i][1];
            int count = microbes[i][2];
            int dir = microbes[i][3];

            // 이미 사라진 군집은 건너뛰기
            if(count == 0) {
                continue;
            }

            // 가장자리에 있으면
            if (x == 0 || x == N - 1 || y == 0 || y == N - 1) {

                // 미생물 수를 절반으로 줄이고
                microbes[i][2] = count / 2;

                // 군집이 사라진 경우 방향도 초기화
                if (microbes[i][2] == 0) {
                    microbes[i][3] = 0; // 방향 초기화
                    continue; // 다음 군집으로 넘어감
                }

                // 이동방향을 반대로 바꾼다.
                if (dir == 1) {
                    microbes[i][3] = 2; // 상 -> 하
                } else if (dir == 2) {
                    microbes[i][3] = 1; // 하 -> 상
                } else if (dir == 3) {
                    microbes[i][3] = 4; // 좌 -> 우
                } else if (dir == 4) {
                    microbes[i][3] = 3; // 우 -> 좌
                }
            }
        }
    }

    public static void combineMicrobes() {
        // 같은 위치에 있는 모든 군집들을 하나로 통합
        for (int i = 0; i < K; i++) {
            if (microbes[i][2] == 0) continue; // 이미 합쳐진 군집은 건너뛰기
            
            int x = microbes[i][0];
            int y = microbes[i][1];
            
            // Step 1: 같은 위치에서 가장 큰 군집 찾기
            int maxIdx = i;
            for (int j = i + 1; j < K; j++) {
                if (microbes[j][2] > 0 && microbes[j][0] == x && microbes[j][1] == y) {
                    if (microbes[j][2] > microbes[maxIdx][2]) {
                        maxIdx = j;
                    }
                }
            }
            
            // Step 2: 같은 위치의 모든 군집을 가장 큰 군집으로 통합
            for (int j = 0; j < K; j++) {
                if (j == maxIdx) continue; // 자기 자신은 제외
                if (microbes[j][2] > 0 && microbes[j][0] == x && microbes[j][1] == y) {
                    // 가장 큰 군집에 수 더하기
                    microbes[maxIdx][2] += microbes[j][2];
                    
                    // 합쳐진 군집은 삭제
                    microbes[j][2] = 0;
                    microbes[j][3] = 0;
                }
            }
        }
    }

    
    public static void countMicrobes() {
        for (int i = 0; i < K; i++) {
            answer += microbes[i][2]; // 남아있는 미생물 수의 총합을 구한다.
        }
    }
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken()); // 격자 크기
        M = Integer.parseInt(st.nextToken()); // 격리 시간
        K = Integer.parseInt(st.nextToken()); // 미생물 군집의 수

        microbes = new int[K][4]; // 미생물 군집 정보 저장 배열

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            microbes[i][0] = Integer.parseInt(st.nextToken()); // x 좌표
            microbes[i][1] = Integer.parseInt(st.nextToken()); // y 좌표
            microbes[i][2] = Integer.parseInt(st.nextToken()); // 미생물 수
            microbes[i][3] = Integer.parseInt(st.nextToken()); // 이동 방향
        }

        // answer 초기화
        answer = 0;
        // testPrint Arrays
        // System.out.println(Arrays.deepToString(microbes));
    }
}