import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class test12 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] map;
    static int maxCore, minLen;

    // 상 하 좌 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Core {
        int r, c;
        public Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static ArrayList<Core> coreList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        // trim() 습관 굿!
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            coreList.clear(); // 리스트 초기화 필수

            // 초기화
            maxCore = 0;
            minLen = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        // 가장자리는 이미 연결된 것으로 간주 -> 리스트에 안 넣음
                        if (i != 0 && i != N - 1 && j != 0 && j != N - 1) {
                            coreList.add(new Core(i, j));
                        }
                    }
                }
            }

            // 탐색 시작
            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(minLen).append("\n");
        }
        System.out.println(sb);
    }

    // idx: 고려중인 코어 인덱스, cnt: 연결된 코어 수, len: 전선 길이 합
    public static void dfs(int idx, int cnt, int len) {
        // [기저조건]
        if (idx == coreList.size()) {
            if (cnt > maxCore) {
                maxCore = cnt;
                minLen = len;
            } else if (cnt == maxCore) {
                minLen = Math.min(minLen, len);
            }
            return;
        }
        
        // [가지치기] 현재 상태에서 남은걸 다 연결해도 maxCore 갱신 불가능하면? return
        if(cnt + (coreList.size() - idx) < maxCore) return;

        Core core = coreList.get(idx);

        // 1. 4방향 연결 시도
        for (int d = 0; d < 4; d++) {
            int wireLen = getWireLength(core.r, core.c, d);
            
            if (wireLen > 0) { // 연결 가능하다면
                setWire(core.r, core.c, d, 2); // 전선 설치 (map 변경)
                dfs(idx + 1, cnt + 1, len + wireLen); // 재귀
                setWire(core.r, core.c, d, 0); // 전선 해제 (원상 복구)
            }
        }

        // 2. 연결 안 하고 건너뛰기
        dfs(idx + 1, cnt, len);
    }

    // 해당 방향으로 끝까지 갈 수 있는지 체크하고 길이 반환 (못 가면 0)
    public static int getWireLength(int r, int c, int d) {
        int count = 0;
        int nr = r + dr[d];
        int nc = c + dc[d];

        while (true) {
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) return count; // 성공
            if (map[nr][nc] != 0) return 0; // 장애물 (1 or 2)

            nr += dr[d];
            nc += dc[d];
            count++;
        }
    }

    // 맵에 전선을 깔거나(2) 지우는(0) 함수
    public static void setWire(int r, int c, int d, int val) {
        int nr = r + dr[d];
        int nc = c + dc[d];

        while (true) {
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
            map[nr][nc] = val;
            nr += dr[d];
            nc += dc[d];
        }
    }
}