import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 17135.캐슬 디펜스 
 * @author neogul02
 * 
 * N×M 격자판, 궁수 3명 배치
 * 
 * 두 위치 거리 |r1-r2| + |c1-c2|
 * 
 * [N+1][M] 배열로 초기화 하고 마지막 행은 성 영역
 * 궁수의 위치마다 시뮬레이션을 해서 잡은 적의 최대값을 출력
 * 
 * MC3 -> M 개의 칸에서 3칸을 궁수로 배치하는 경우의수 -> 조합
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M;
	static int D; // distance 궁수 사거리
	static int[][] map; 
	
	static int[] archers = new int[3];
	
	static int maxKillScore;
	
	public static void main(String[] args) throws IOException{
		input();
		combinationArchers(0,0);
		
		System.out.println(maxKillScore);
	}

	/**
	 * @combinationArchers
	 * 궁수 3명의 위치 인덱스를 를 M칸에 조합 해서 시뮬레이션
	 */
	private static void combinationArchers(int depth, int start) {
		// 기저조건 = depth 가 3이면 return
		if(depth == 3) {
			int killScore = defenceStart(archers);
			maxKillScore = Math.max(maxKillScore, killScore);
			return;
		}
		
		for(int i=start; i<M; i++) {
			archers[depth] = i;
			combinationArchers(depth+1, i+1);
		}
	}

	/**
	 * @gameStart
	 * 1. 원본 맵을 복사하고
	 * 2. 적이 모두 사라질때까지 반복함
	 * 3. 3명의 궁수 각각이 쏠 목표를 계산 @findTarget
	 * 4. 찾은 목표를 쏘는데, 같은 적이라면 중복처리
	 * 5. 다음 디펜스 턴으로, 전체 배열을 한 칸씩 이동
	 */
	private static int defenceStart(int[] archers) {
		// 1. 원본 맵을 복사하기
		int[][] simulateMap = copyMap(map);
		int killScore = 0;
		
		// 2. 적이 모두 사라질때까지 반복
		while(hasEnemy(simulateMap)) {
			
			ArrayList<int[]> targets = new ArrayList<>();
			
			// 3. 궁수가 쏠 목표 찾기 
			for(int i=0; i<3; i++) {
				int[] temp = findTarget(simulateMap, archers[i]);
				if(temp != null) {
                    targets.add(temp);
                }
			}
			
			// 4. 찾은 적 쏘기
			Set<String> killed = new HashSet<>(); // 적 하나에게 중복되면 손해기때문에 중복 적 있으면 제거
			for(int[] target : targets) {
				int r = target[0];
				int c = target[1];
				
				String key = r+","+c;
				
				if(killed.add(key)==true) { // 추가 성공 = true
					if(simulateMap[r][c] == 1) {
						simulateMap[r][c] = 0; // 잡고
						killScore ++; // 점수 +1
					}
				}
			}
			// 5. 다 쐈으면 다음 디펜스로 1칸 내려줘야함
			nextDefence(simulateMap);
		}
		
		return killScore; // killscore를 리턴
	}

	private static void nextDefence(int[][] simulateMap) {
		
		for(int r = N - 1; r >= 0; r--) { // 5 4 3 2 1
            for(int c = 0; c < M; c++) {
            	
                if(r == N - 1) {
                    simulateMap[r][c] = 0;  // 마지막 행은 0으로 밀기
                    
                } else {
                    simulateMap[r + 1][c] = simulateMap[r][c]; // 한 칸씩 내려주고
                    simulateMap[r][c] = 0; // 기존 칸은 0으로 밀기
                }
            }
        }
	}

	private static int[] findTarget(int[][] simulateMap, int archerIdx) {
		
        int archerRow = N;  // 궁수는 항상 N번째 행
        // 거리 순, 왼쪽 우선 탐색
        for(int dist = 1; dist <= D; dist++) {
        	
            for(int col = 0; col < M; col++) {
                for(int row = 0; row < N; row++) {
                	
                    if(simulateMap[row][col] == 1) {
                    	// 거리계산
                        int curDist = Math.abs(archerRow - row) + Math.abs(archerIdx - col);
                        if(curDist == dist) {
                            // 현재 거리에서 가장 왼쪽 적 찾음
                            return new int[]{row, col};
                        }
                    }
                }
            }
        }
        
        return null;
	}

	private static boolean hasEnemy(int[][] simulateMap) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(simulateMap[i][j]==1) return true; 
			}
		}
		return false;
	}

	private static int[][] copyMap(int[][] map) {
		int[][] copy = new int[N][M];
		for(int i=0; i<N; i++) {
			copy[i] = map[i].clone();
		}
		return copy;
	}

	private static void input() throws IOException{
		st = new StringTokenizer(br.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		// 원본 배열 선언 
		map = new int[N][M];
		for(int r=0; r<N; r++) {
			st= new StringTokenizer(br.readLine().trim()," ");
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
        }
	}
}