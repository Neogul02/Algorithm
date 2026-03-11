import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 15686. 치킨배달
 * @author neogul02
 * 
 * - N * N 크기 도시에서 0은 빈 칸, 1은 집, 2는 치킨집
 * - 치킨 거리 = 집과 가장 가까운 치킨집 사이의 거리 = 맨헤튼 거리 계산 |r1 - r2| + |c1 - c2|
 * - 프렌차이즈의 만행을 저질러보자, 가맹점주님들 미안해
 * - 도시의 치킨거리는 모든 집의 치킨거리의 합 = 집마다 가장 가까운 치킨거리 += 
 * - 방법이 2갠데 dfs로 모든 치킨집 조합하고 거리구하는 방법
 * 
 * - @see input
 * 1. 첫 번째 줄에는 N, M // 도시의 크기 N과 폐업시키지 않을 치킨집의 수 M
 * 2. N개의 줄에는 도시의 정보. 0은 빈 칸, 1은 집, 2는 치킨집 
 *  + 굳이 배열을 안만들어도 각 x,y 위치로 거리 합을 구할 수 있지 않을까?
 * 3. 집이랑 치킨집 위치를 리스트에 담아둔다.
 * 
 * - @see combinationDFS
 * 1. 기저조건
 *  1-1. M개의 치킨집을 고른경우 return
 *  1-2. 고른 치킨집 조합에서 모든 집 사이의 치킨거리를 구하고 min값이 answer보다 크면 return
 * 2. 수행로직
 *  2-1. 총 치킨집 수에서 M개 치킨집을 고르는 "조합" 구하기
 *  2-2. visited 배열로 return 이후에 백트래킹으로 해제
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[][] map;

    static ArrayList<int[]> homes = new ArrayList<>();
    static ArrayList<int[]> chickens = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input(); // 입력받기
        visited = new boolean[chickens.size()]; // 치킨집 수 만큼 조합하는 방문체크
        combinationDFS(0, 0);

        System.out.print(answer);
    }

    public static void combinationDFS(int depth, int start) {
        // 종료조건, M개 치킨집을 고른 경우
        if (depth == M) {
            int sum = 0;
            for (int[] home : homes) {
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < chickens.size(); i++) {
                    if (visited[i] == true) {
                        int distance = Math.abs(home[0] - chickens.get(i)[0]) + Math.abs(home[1] - chickens.get(i)[1]);
                        min = Math.min(min, distance);
                    }
                }

                sum += min;
                // 가지치기, 더한값이 최솟값보다 크면 더 계산할 필요 없음
                if(sum > answer) 
                    return;
            }
            // 도시의 치킨거리 = 모든 집의 치킨거리의 합
            answer = Math.min(answer, sum);
            return;
        }

        // dfs 수행로직, 총 치킨집 수에서 M개 치킨집을 고르는 "조합"
        for (int i = start; i < chickens.size(); i++) {
            visited[i] = true;
            combinationDFS(depth + 1, i + 1);
            visited[i] = false; // 백트래킹
        }
    }
    
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int v = Integer.parseInt(st.nextToken());

                if (v == 1)
                    homes.add(new int[] { i, j });

                else if (v == 2)
                    chickens.add(new int[] { i, j });

            }
        }
    }
    
}