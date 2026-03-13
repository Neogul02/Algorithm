import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 4014. 활주로 건설
 * @author neogul02
 * 
 * 
 * - 활주로는 동일한 높이의 구간에서 건설이 가능함
 * - 가로랑 세로방향을 각각 체크하는게 좋을듯함
 * - 활주로는 경사로가 필요할 때, 경사로를 설치할 수 있는지 체크해야함
 * - 경사로의 높이는 1로 고정, X는 2이상 4이하
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
            int count = 1; // 동일한 높이의 구간의 길이
            boolean canBuild = true; // 활주로 건설 가능 여부

            for (int j = 1; j < N; j++) {
                if (map[i][j] == map[i][j - 1]) {
                    count++;
                    // 높이가 1 증가하는 경우
                } else if (map[i][j] == map[i][j - 1] + 1) {
                    // 경사로 설치 가능
                    if (count >= X) {
                        count = 1; // 새로운 구간 시작
                    } else {
                        canBuild = false;
                        break;
                    }
                    // 높이가 1 감소하는 경우
                } else if (map[i][j] == map[i][j - 1] - 1) {
                    int tempCount = 0;

                    for (int k = j; k < N; k++) {
                        if (map[i][k] == map[i][j]) { // 같은 높이가 계속되는지
                            tempCount++;
                        }
                    }
                    if (tempCount >= X) { // 경사로 설치 가능
                        count = 0; // 새로운 구간 시작
                        j += tempCount - 1; // 경사로 설치한 만큼 인덱스 이동
                    } else {
                        canBuild = false;
                        break;
                    }
                } else { // 높이가 2 이상 차이
                    canBuild = false;
                    break;
                }
            }

            if (canBuild) {
                answer++;
            }
        }
    }
    
    public static void checkCol() {
        for (int j = 0; j < N; j++) {
            int count = 1; // 동일한 높이의 구간의 길이
            boolean canBuild = true; // 활주로 건설 가능 여부

            for (int i = 1; i < N; i++) {
                if (map[i][j] == map[i - 1][j]) {
                    count++;
                    // 높이가 1 증가하는 경우
                } else if (map[i][j] == map[i - 1][j] + 1) {
                    // 경사로 설치 가능
                    if (count >= X) {
                        count = 1; // 새로운 구간 시작
                    } else {
                        canBuild = false;
                        break;
                    }
                    // 높이가 1 감소하는 경우
                } else if (map[i][j] == map[i - 1][j] - 1) {
                    int tempCount = 0;

                    for (int k = i; k < N; k++) {
                        if (map[k][j] == map[i][j]) { // 같은 높이가 계속되는지
                            tempCount++;
                        }
                    }
                    if (tempCount >= X) { // 경사로 설치 가능
                        count = 0; // 새로운 구간 시작
                        i += tempCount - 1; // 경사로 설치한 만큼 인덱스 이동
                    } else {
                        canBuild = false;
                        break;
                    }
                } else { // 높이가 2 이상 차이
                    canBuild = false;
                    break;
                }
            }

            if (canBuild) {
                answer++;
            }
        }
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }
}
