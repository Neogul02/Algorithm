import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 5656. 벽돌 깨기
 * @author neogul02
 * 
 * - W * H 크기의 벽돌이 쌓여있고
 * - 구슬 N번 쏠 수 있음
 * 
 * 생각해야할거
 * 구슬은 N 번 쏠 수 있다.
 * 구슬은 좌 우로만 움직일 수 있어서 항상 맨위에 있는 벽돌만 깨진다.
 * 구슬이 명중하면 해당 벽돌에 적힌 숫자 - 1칸만큼 십자 방향으로 깨진다. 3벽돌 = 상하좌우 2칸씩 깨짐 = 연쇄 반응
 * 깨지고 난 후 벽돌 아래에 빈칸이 있으면 중력 작용으로 벽돌이 아래로 떨어진다.
 * 
 * 0은 빈칸, 1 이상은 벽돌이 있는 칸(효과)
 * 
 * 1. 구슬을 어떻게 떨어뜨려야 하는가? -> 모든 경우의수를 고려해서 가장 많은 벽돌이 깨지는 경우를 찾아야할것같음
 *  1-1. 중복순열로 구슬 떨어뜨릴 위치를 정한다. -> N 개의 구슬을 W 개의 위치에 떨어뜨리는 경우의 수 = W^N
 *      최대 w 12, n은 4니까 12^4 충분. [1,1,1,1] ~ [12,12,12,12] -> 20736
 *      가로로만 움직일 수 있으니까 W개 위치 중에서 N개를 고르는 중복순열..?
 *  1-2. N개만큼의 순열이 완성되면 => 2. 시뮬레이션 돌리기
 *  
 * 2. 시뮬레이션 구현하기
 *  2-1. 구슬이 떨어지는 위치에서 가장 위에 있는 벽돌 찾아서 시작
 *  2-2. 벽돌숫자만큼 십자모양으로 연쇄작용까지 구현
 *  2-3. 구슬 하나 떨어뜨린 다음에 검사 -> 중력 작용으로 벽돌이 떨어지는 시뮬레이션 구현하기
 * 
 * 3. 남은 블록 개수 업데이트 
 */
public class breakBricks {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, W, H; // 구슬 개수, 벽돌 가로, 벽돌 세로
    static int[][] map; // 벽돌 정보 저장 배열
    
    static int[] ballPos; // 구슬 떨어뜨릴 위치 저장 배열
    
    static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
    static int[] dy = { 0, 0, -1, 1 };

    static int minBrickCnt; // 남은 벽돌의 최소 개수

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(' ');
            // 1. 입력 처리
            input();

            // 2. 풀이
            ballPos = new int[N]; // 구슬 떨어뜨릴 위치 저장 배열 초기화
            minBrickCnt = Integer.MAX_VALUE; // 남은 벽돌의 최소 개수 초기화
            permutation(0);

            sb.append(minBrickCnt).append("\n");
        }
        System.out.print(sb);
    }

    public static void permutation(int depth) {
        // 기저조건 -> 구슬을 N번 떨어뜨렸으면 종료
        if (depth == N) {
            // 구슬을 N번 떨어뜨렸으면 시뮬레이션 실행
            // System.out.println(Arrays.toString(ballPos));
            simulate();
            return;
        }

        // 수행 로직 -> 구슬을 떨어뜨릴 위치 정하기 -> 중복순열
        for (int i = 1; i <= W; i++) {
            ballPos[depth] = i; // 구슬이 떨어지는 위치 저장
            permutation(depth + 1); // 다음 구슬 떨어뜨리기
        }
    }
    
    public static void simulate() {
        // 시뮬레이션용 맵 복사
        int[][] tempMap = new int[H][W];
        for (int i = 0; i < H; i++) {
            tempMap[i] = map[i].clone();
        }
        
        // 2-1. 구슬이 떨어지는 위치에서 가장 위에 있는 벽돌 찾아서 시작
        for (int pos : ballPos) {

            if (minBrickCnt == 0) {
                // 이미 남은 벽돌이 없는 경우, 더 이상 시뮬레이션할 필요 없음
                break;
            }

            int x = 0; // 세로
            int y = pos - 1; // 가로

            // 가장 위에 있는 벽돌 찾기
            while (x < H && tempMap[x][y] == 0) {
                x++;
            }

            if (x == H) {
                // 구슬이 떨어지는 위치에 벽돌이 없는 경우, 다음 구슬로 넘어가기
                continue;
            }

            // 2-2. 벽돌을 만나면 벽돌숫자만큼 십자모양으로 연쇄작용까지 구현
            breakBricks(tempMap, x, y); // 벽돌 깨는 함수 호출

            // 2-3. 구슬 하나 떨어뜨린 다음에 검사 -> 중력 작용으로 벽돌이 떨어지는 시뮬레이션 구현하기
            fallDownBricks(tempMap);
        }

        // 2-4. 남은 벽돌 개수 세기
        int remainBricks = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (tempMap[i][j] > 0) {
                    remainBricks++;
                }
            }
        }
        minBrickCnt = Math.min(minBrickCnt, remainBricks);

        for (int i = 0; i < H; i++) {
            System.out.println(Arrays.toString(tempMap[i]));
        }
        // System.out.println("떨어뜨린 구슬 위치: " + Arrays.toString(ballPos));
        // System.out.println("남은 벽돌 개수: " + remainBricks);
        // System.out.println("-----------------------------");

    }
    
    public static void breakBricks(int[][] copyMap, int x, int y) {
        // bfs로 구현
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        int power = copyMap[x][y];
        queue.add(new int[] { x, y, power }); // 깨지는 벽돌 위치 큐에 추가
        copyMap[x][y] = 0; // 벽돌 깨기

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int tempX = temp[0];
            int tempY = temp[1];
            int tempPower = temp[2];

            // 십자방향으로 벽돌깨기
            for (int d = 0; d < 4; d++) {
                for (int i = 1; i < tempPower; i++) { // 벽돌 숫자만큼 십자 방향으로 깨짐
                    int nx = tempX + dx[d] * i;
                    int ny = tempY + dy[d] * i;

                    if (nx < 0 || nx >= H || ny < 0 || ny >= W)
                        continue; // 범위 벗어나면 skip

                    if (copyMap[nx][ny] == 0)
                        continue; // 이미 깨진 벽돌은 skip

                    int np = copyMap[nx][ny]; // 다음 벽돌의 숫자
                    queue.add(new int[] { nx, ny, np }); // 깨지는 벽돌 위치 큐에 추가
                    copyMap[nx][ny] = 0; // 벽돌 깨기
                }
            }
        }
    }
    
    public static void fallDownBricks(int[][] copyMap) {
        // 맨 아래 열부터 검사
        for (int y = 0; y < W; y++) {
            for (int x = H - 1; x >= 0; x--) { // H = 10
                // 빈칸이면
                if (copyMap[x][y] == 0) { 

                    // 그 위에 있는 벽돌을 아래로 떨어뜨리기
                    for (int k = x - 1; k >= 0; k--) {

                        if (copyMap[k][y] != 0) { // 벽돌이 있으면
                            copyMap[x][y] = copyMap[k][y]; // 벽돌 떨어뜨리기
                            copyMap[k][y] = 0; // 원래 위치는 빈칸으로 만들기
                            break; // 다음 빈칸으로 넘어가기
                        }
                    }
                }

            }
        }
    }
    
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
    }
}
