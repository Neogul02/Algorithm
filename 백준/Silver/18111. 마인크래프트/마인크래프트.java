import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 18111. 마인크래프트
 * @author neogul02
 * 
 * 1. 땅고르기 작업을 해야함 세로 N 가로 M 크기 집터, 0~256 높이 
 * 2. 인벤토리에 블록이 B개 있음, 
 * 
 * 3. 기존 블록을 제거하거나 (2초)
 *  3-1. 제거한 블록은 인벤토리에 들어감
 * 
 * 4. 인벤토리에서 블록을 설치하거나 (1초)
 *  4-1. 설치한 블록은 인벤토리에서 사라짐
 * 
 * 땅고르기의 높이를 어떻게 조절하냐가 문제일것같은데 
 * 인벤토리에 블록으로 높이를 조절할 수 있는가? 모든 경우의 수를 다 해보자
 * 높이, 
 * 
 * N, M, B -> 땅고르기 작업을 가장 빠르게 끝내는 시간과 그 때의 땅의 높이
 * 
 * 최대 500 * 500 * 256 -> 64000000 이면 충분
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, B; // 세로 N, 가로 M, 인벤토리 블록 수 B
    static int[][] mineCraftMap;

    static int minTime = Integer.MAX_VALUE; // 최소 시간
    static int minHeight = 0; // 최소 시간일 때의 높이

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.print(sb);
    }

    private static void solve() {
        for (int h = 0; h <= 256; h++) { // 높이 0~256 으로 맞춘다면? 
            int removeBlockCnt = 0; // 제거한 블록 수 = 인벤토리에 저장됨
            int useBlockCnt = 0; // 설치해야하는 블록 수 = 인벤토리에서 사라짐

            // 높이 h로 땅고르기 작업을 했을 때 제거해야하는 블록 수와 설치해야하는 블록 수 계산
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int tempH = mineCraftMap[i][j];
                    if (tempH > h) { // 원하는 높이가 현재 높이보다 낮으면
                        removeBlockCnt += (tempH - h);
                    } else{ // 원하는 높이가 현재 높이보다 높으면
                        useBlockCnt += h - tempH;
                    }
                    
                }
            }

            // 2. 인벤토리에 블록이 충분한지 확인
            if (removeBlockCnt + B < useBlockCnt)
                continue;

            // 3. 시간 계산
            int time = (removeBlockCnt * 2) + (useBlockCnt * 1); // 제거하는데 2초, 설치하는데 1초
            if (time <= minTime) {
                minTime = time;
                minHeight = h;
            }
        }
        sb.append(minTime).append(" ").append(minHeight);
    }
    
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        mineCraftMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                mineCraftMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    } 
}