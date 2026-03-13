package backup.PS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 15682. 감시
 * @author neogul02
 * 
 * - 0 = blank , 6 wall, 1~5 cctvNumber, 감시가능영역 #
 * - cctv끼리는 통과 가능, 벽은 불가능
 * - 사각지대의 최소 크기를 출력
 * -
 * 1. cctv 위치 저장하기
 * 
 * 2. cctv 방향 조합 구하기 
 *  2-1. 1번은 4가지, 2번은 2가지, 3번은 4가지, 4번은 4가지, 5번은 1가지
 *  2-2. dfs로 조합 구하고, 조합마다 '#'으로 감시영역 표시하기
 * 
 * ex ) 만약 1, 2번 cctv가 맵에 있으면 4*2가지의 조합이 나옴
 * dfs로 조합 구하고, 조합마다 '#'으로 감시영역 표시하기
 * 
 * 3. 사각지대 개수 세기 = 0 개수 세기
 * 
 * 최대 cctv 개수 <= 8, 4^8 65,536, 충분
 */
public class gamsi {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static char[][] map; // 사무실 정보 저장하는 배열

    static int[] num1D = { 0, 1, 2, 3 }; // 1번 cctv 방향
    static int[] num2D = { 0, 1 }; // 2번 cctv 방향
    static int[] num3D = { 0, 1, 2, 3 }; // 3번 cctv 방향
    static int[] num4D = { 0, 1, 2, 3 }; // 4번 cctv 방향
    static int[] num5D = { 0 }; // 5번 cctv 방향

    static int[] dx = { -1, 0, 1, 0 }; // 상우하좌
    static int[] dy = { 0, 1, 0, -1 };

    static ArrayList<int[]> cctvLocation = new ArrayList<>(); // cctv 위치 저장하는 배열
    static int minBlankCnt = Integer.MAX_VALUE; // 사각지대 최소 크기

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.print(sb);
    }

    public static void solve() {
        // 1. cctv 위치 저장하기
        saveLocation();

        // 2. cctv 방향 조합 구하기
        dfs(0, new int[cctvLocation.size()]);

        // 3. 최소 사각지대 출력
        sb.append(minBlankCnt);
    }

    public static void saveLocation() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (map[i][j] >= '1' && map[i][j] <= '5') 
                    cctvLocation.add(new int[] { i, j });    
            }
        }
    }

    public static void dfs(int depth, int[] selected) {
        // 기저조건, 모든 cctv의 방향이 선택되면 계산하고 return
        if (depth == cctvLocation.size()) {
            // 선택된 방향으로 감시영역 표시하기
            setWatchArea(selected);
            return;
        }
        
        // 수행 로직
        int[] tempCCTV = cctvLocation.get(depth); // cctv 위치 하나 가져오기
        int cctvType = map[tempCCTV[0]][tempCCTV[1]] - '0'; // cctv 번호

        int[] directions = null;
        switch (cctvType) {
            case 1:
                directions = num1D;
                break;
            case 2:
                directions = num2D;
                break;
            case 3:
                directions = num3D;
                break;
            case 4:
                directions = num4D;
                break;
            case 5:
                directions = num5D;
                break;
        }

        for (int d : directions) {
            selected[depth] = d; // 현재 cctv의 방향 선택
            dfs(depth + 1, selected); // 다음 cctv로 이동
        }
    }

    public static void setWatchArea(int[] selected) {

        // 1. 기존 맵 복사
        char[][] tempMap = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tempMap[i][j] = map[i][j];
            }
        }

        // 2. cctv 위치 하나씩 꺼내면서, 선택된 방향으로 감시 영역 # 매핑
        for (int i = 0; i < cctvLocation.size(); i++) {

            int[] cctvPos = cctvLocation.get(i);

            int x = cctvPos[0];
            int y = cctvPos[1];

            int cctvType = map[x][y] - '0'; // cctv 번호
            int direction = selected[i];

            // CCTV 타입별 처리
            if (cctvType == 1) {
                // 한 방향만 감시
                watch(tempMap, x, y, direction);

            } else if (cctvType == 2) {
                // 양 반대 방향 감시 (상하 또는 좌우)
                if (direction == 0) { // 상하
                    watch(tempMap, x, y, 0);
                    watch(tempMap, x, y, 2);
                } else { // 좌우
                    watch(tempMap, x, y, 1);
                    watch(tempMap, x, y, 3);
                }

            } else if (cctvType == 3) {
                // 직각
                watch(tempMap, x, y, direction);
                watch(tempMap, x, y, (direction + 1) % 4);

            } else if (cctvType == 4) {
                // 3방향
                watch(tempMap, x, y, direction);
                watch(tempMap, x, y, (direction + 1) % 4);
                watch(tempMap, x, y, (direction + 2) % 4);

            } else if (cctvType == 5) {
                // 4방향
                watch(tempMap, x, y, 0);
                watch(tempMap, x, y, 1);
                watch(tempMap, x, y, 2);
                watch(tempMap, x, y, 3);
            }
        }

        // 3. 사각지대 개수 세기
        countBlank(tempMap);
    }

    public static void watch(char[][] tempMap, int x, int y, int dir) {
        // 해당 방향으로 계속 진행하면서 감시 영역 표시
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
            if (tempMap[nx][ny] == '6') break; // 벽을 만나면 중단
            if (tempMap[nx][ny] == '0') {
                tempMap[nx][ny] = '#';
            }
            nx += dx[dir];
            ny += dy[dir];
        }
    }

    public static void countBlank(char[][] tempMap) {

        int tempBlankCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempMap[i][j] == '0') {
                    tempBlankCnt++;
                }
            }
        }
        // 현재 사각지대 수가 최소 사각지대 수보다 작으면 최신화
        if(tempBlankCnt < minBlankCnt){
            minBlankCnt = tempBlankCnt;
        }
    }

    public static void input() throws IOException {

        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }
    }      
}