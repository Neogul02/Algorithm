import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 4014. 활주로 건설
 * @author neogul02
 * - 활주로는 동일한 높이의 구간에서 건설이 가능함
 * - 가로랑 세로방향을 각각 체크하는게 좋을듯함
 * - 활주로는 경사로가 필요할 때, 경사로를 설치할 수 있는지 체크해야함
 * - 경사로의 높이는 1로 고정, X는 2이상 4이하
 * 
 * 1. 가로행 체크
 *  1-1. 높이 차이가 0이면 같은 높이니까 skip
 *  1-2. 높이 차이가 2이상이면 불가
 *  1-3. 높이 차이가 1이면 경사로 설치 가능 여부 체크
 *   1-3.1. 현재 위치에서 뒤로 X칸 체크
 *   1-3.2. 범위 벗어나거나, 높이가 다르거나, 이미 경사로 설치된 곳이면 불가
 *   1-3.3. 경사로 설치 가능하면 used 배열에 표시
 *  1-4. 높이 차이가 -1이면 경사로 설치 가능 여부 체크
 *   1-4.1. 현재 위치에서 앞으로 X칸 체크
 *   1-4.2. 범위 벗어나거나, 높이가 다르거나, 이미 경사로 설치된 곳이면 불가
 *   1-4.3. 경사로 설치 가능하면 used 배열에 표시
 * 2. 세로행 체크
 *  2-1. 가로행 체크와 동일한 로직으로 체크
 * 3. 활주로 건설 가능하면 answer++
 */
public class BuildSkyRoad {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, X; // N은 맵의 크기, X는 경사로의 길이
    static int[][] map;

    static int answer; // 활주로 건설 가능 경우의 수

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            // 1. input
            input();

            // 2. solve
            solve(tc);
        }
        System.out.print(sb);
    }
    
    public static void solve(int tc) {
        // 0. Init
        answer = 0;

        // 1. 가로행 체크
        checkRow();

        // 2. 세로행 체크
        checkCol();

        // 3. output
        sb.append("#").append(tc).append(" ").append(answer).append("\n");

    }

    public static void checkRow() {
        for (int i = 0; i < N; i++) {
            int[] line = new int[N];

            // 가로 행 한 줄 복사
            for (int j = 0; j < N; j++) {
                line[j] = map[i][j];
            }

            if (canBuild(line)) {
                answer++;
            }
        }
    }
    
    public static void checkCol() {
        for (int j = 0; j < N; j++) {
            int[] line = new int[N];

            // 세로 열 한 줄 복사
            for (int i = 0; i < N; i++) {
                line[i] = map[i][j];
            }

            if (canBuild(line)) {
                answer++;
            }
        }
    }

    public static boolean canBuild(int[] line) {
        boolean[] used = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int diff = line[i + 1] - line[i];

            // 높이 차이가 0이면 같은 높이니까 skip
            if (diff == 0) {
                continue;
            }

            // 2이상 높이차면 불가
            if (Math.abs(diff) > 1) {
                return false;
            }

            // 높이 차이가 1이면 경사로 설치 가능 여부 체크
            if (diff == 1) {
                for (int j = i; j > i - X; j--) {

                    if (j < 0 || line[j] != line[i] || used[j]==true) {
                        return false;
                    }
                    used[j] = true;
                }
            } else { // 높이 차이가 -1
                for (int j = i + 1; j <= i + X; j++) {
                    if (j >= N || line[j] != line[i + 1] || used[j]==true) {
                        return false;
                    }
                    used[j] = true;
                }
            }
        }

        return true;
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }
}
