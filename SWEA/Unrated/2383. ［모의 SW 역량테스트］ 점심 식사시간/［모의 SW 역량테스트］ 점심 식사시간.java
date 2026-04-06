import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 2383. 점심 식사시간
 * @author neogul02
 * - 사람이 현재 위치에서 계단의 입구까지 이동하는데 걸리는 시간 
 *   이동 시간(분) = | PR - SR | + | PC - SC |
 * 
 * 구현해야할부분
 * 모든사람들이 빠르게 계단을 내려가는 최소 시간 출력
 * 1. P 사람이 어느 계단 S으로 갈것인가? - 계단은 반드시 두 개임 
 * 2. 계단의 입구에서 내려가는 시간 계산
 *  2-1. 계단의 입구에서 내려가는 시간은 계단의 길이 + 1분 (완전히 내려가기까지)
 *  
 * 거리이동(분) + 계단길이 + 1분 (완전히 내려가기까지) = P가 S로 내려가는 시간
 * but..
 * 1. 계단에는 최대 3명만 올라갈 수 있음 
 * 
 * 각 사람으로 내려갈 수 있는 모든 경우의 수를 dfs 완탐으로 시뮬레이션?
 * 
 */
public class Solution {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  static int N;
  static int[][] map;

  static ArrayList<int[]> people; // 사람 위치 저장
  static ArrayList<int[]> stairs; // 계단 위치 저장

  static int[] match; // 각 사람이 선택한 계단 인덱스 (0 or 1)

  static int minTime;

  public static void main(String[] args) throws IOException{
    int T = Integer.parseInt(br.readLine().trim()); 

    for(int tc=1; tc<=T; tc++){
      sb.append("#").append(tc).append(" ");
      input();

      dfs(0);
      sb.append(minTime).append("\n");
    }

    System.out.print(sb);

  }

  static void dfs(int depth) {
    if (depth == people.size()) {
        // 모든 사람이 계단을 정했으면 시뮬레이션 tlwkr
        simulate();
        return;
    }

    // 0번 계단을 선택한 경우
    match[depth] = 0;
    dfs(depth + 1);

    // 1번 계단을 선택한 경우
    match[depth] = 1;
    dfs(depth + 1);
}

  static void simulate() {
    int maxStairTime = 0; // 두 계단 중 더 늦게 끝나는 시간

    for (int i = 0; i < 2; i++) { // 각 계단(0, 1) 독립 시뮬레이션
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 도착 시간 순 정렬
        for (int j = 0; j < people.size(); j++) {
            if (match[j] == i) {
                int[] p = people.get(j);
                int[] s = stairs.get(i);
                int arrivalTime = Math.abs(p[0] - s[0]) + Math.abs(p[1] - s[1]);
                pq.add(arrivalTime);
            }
        }

        int stairTime = 0; // 현재 계단의 소요 시간
        int stairLength = stairs.get(i)[2];
        ArrayDeque<Integer> stairQueue = new ArrayDeque<>(); // 계단 안 사람들의 완료 시간

        // 현재 계단 시뮬레이션 시작
        while (!pq.isEmpty() || !stairQueue.isEmpty()) {
            stairTime++; // 1초씩 흐름
            
            // 1. 계단 다 내려온 사람 제거 (현재 시간 기준)
            while (!stairQueue.isEmpty() && stairQueue.peek() <= stairTime) {
                stairQueue.poll();
            }

            // 2. 계단 진입 시도
            while (!pq.isEmpty() && pq.peek() < stairTime && stairQueue.size() < 3) {
                pq.poll();
                stairQueue.add(stairTime + stairLength); // 현재 진입 + K분 후 완료
            }
        }
        
        // 두 계단 중 더 오래 걸린 쪽이 전체 시간
        maxStairTime = Math.max(maxStairTime, stairTime);
    }
    
    minTime = Math.min(minTime, maxStairTime);
}

  public static void input() throws IOException{
    N = Integer.parseInt(br.readLine().trim());

    map = new int[N][N];
    people = new ArrayList<>(); // 사람 위치 저장
    stairs = new ArrayList<>(); // 계단 위치 저장

    match = new int[10]; // 최대 10명

    minTime = Integer.MAX_VALUE;

    for(int i=0; i<N; i++){
      st = new StringTokenizer(br.readLine().trim());
      for(int j=0; j<N; j++){
        map[i][j] = Integer.parseInt(st.nextToken());
        if(map[i][j] == 1){
          // 사람 위치 저장
          people.add(new int[]{i, j});

        } else if(map[i][j] > 1){
          // 계단 위치 저장
          stairs.add(new int[]{i, j, map[i][j]}); // 계단 위치, 길이
        }
      }
    }
  }
}