import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * SW_1767. 프로세서 연결하기
 * @author neogul02
 * 
 * - N x N 맥시노스 프로세서의 코어(Core)를 전원(가장자리)에 연결.
 * - 전선은 직선만 가능, 교차 불가, 다른 코어/전선 통과 불가.
 * - 1순위) 최대 코어 연결, 2순위) 최소 전선 길이 합.
 * 
 * - DFS (깊이 우선 탐색): 모든 코어의 연결 상태를 확인하는 완전 탐색.
 * - Backtracking (백트래킹): 전선 설치 후 재귀 호출 복귀 시 전선 해제(원상복구).
 * - Pruning (가지치기): 남은 코어를 모두 연결해도 현재 최대 코어 수에 미치지 못하면 탐색 중단.
 * 
 * 각 코어마다 5가지 분기 시도:
 * 1~4. 상, 하, 좌, 우 방향으로 전선 설치 시도 (가능 시 map 업데이트 -> 재귀 -> 원상복구).
 * 해당 코어를 포기(Skip)하고 다음 코어로 넘어감.
 * 
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int[][] map;
	
	// 상, 하, 좌, 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static int maxCore; // 최대 연결 코어 수
	static int minLen;  // 최소 전선 길이 합
	
	
	
	static class Core{
		int r,c;
		public Core(int r, int c) {
			this.r = r;
			this.c =c;
		}
	}
	static ArrayList<Core> coreList = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			coreList.clear();
			
			maxCore =0;
			minLen = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim()," ");
		        for(int j=0; j<N; j++) {
		            map[i][j] = Integer.parseInt(st.nextToken());
		            if(map[i][j] == 1) {
		                // 핵심: 가장자리가 아닌 경우에만 리스트에 추가
		                if(i != 0 && i != N-1 && j != 0 && j != N-1) {
		                    coreList.add(new Core(i, j));
		                }
		            }
		        }
		    }
			
			dfs(0,0,0);
			
			sb.append('#').append(tc).append(' ')
			.append(minLen).append('\n');
			
			// debug
//			System.out.println("#" + tc + " Map");
//            for(int i = 0; i < N; i++) {
//                for(int j = 0; j < N; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
//
//            // debug: 리스트 출력
//            System.out.println("등록된 코어 목록: " + coreList);
//            System.out.println("등록된 코어 개수(테두리 제외): " + coreList.size());	
			
		}
		System.out.print(sb);
	}
	
	private static void dfs(int idx, int cnt, int len) {
		// 기저조건
		if(idx==coreList.size()) {
			if(cnt > maxCore) {
				maxCore = cnt;
				minLen = len;
			}else if( cnt == maxCore) {
				minLen = Math.min(minLen, len);
			}
			return;
		}
		
		// 수행
		if(cnt + (coreList.size()-idx) < maxCore) return;
		
		Core core = coreList.get(idx);
		
		
		for(int d=0; d<4; d++) {
			int wireLen =getWireLength(core.r,core.c,d);
			
			if(wireLen > 0) {
				setWire(core.r, core.c, d, 2); // 전선 설치
				dfs(idx+1, cnt+1, len+wireLen); // 재귀 시작
				setWire(core.r, core.c, d, 0); // 전선 복구, 백트래킹
			}
		}
		
		dfs(idx +1, cnt, len);
	}

	private static void setWire(int r, int c, int d, int value) {
		// TODO Auto-generated method stub
		int nr = r + dr[d];
        int nc = c + dc[d];

        while (true) {
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
            map[nr][nc] = value;
            nr += dr[d];
            nc += dc[d];
        }
    
		
		
	}

	private static int getWireLength(int r, int c, int d) {
		
		int cnt =0;
		int nr = r+ dr[d];
		int nc = c+ dc[d];
		
		while(true) {
			if(nr < 0 || nr>=N || nc<0 || nc>=N) return cnt; 
			if(map[nr][nc] !=0) return 0; // 장애물일때 1이거나 2일떄
			
			nr += dr[d];
			nc += dc[d];
			cnt ++;
		}
	}
}