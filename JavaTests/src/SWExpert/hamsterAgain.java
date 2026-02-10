package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class hamsterAgain {
    static int N;  // 우리 개수
    static int X;  // 각 우리에 최대 몇 마리까지 가능한지
    static int M;  // 조건(기록) 개수
    
    static int[][] conditions;  // M개의 조건 저장 [l, r, s]
    static int[] current;       // 현재 탐색 중인 배치
    static int[] best;          // 지금까지 찾은 최적 답
    static int maxSum;          // 최적 답의 총 햄스터 수
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            // 조건 입력받기
            conditions = new int[M][3];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                conditions[i][0] = Integer.parseInt(st.nextToken());  // l
                conditions[i][1] = Integer.parseInt(st.nextToken());  // r
                conditions[i][2] = Integer.parseInt(st.nextToken());  // s
            }
            
            current = new int[N]; // 현재 탐색 중인 배치
            
            
            dfs(0); // 0번째 우리부터
        }
        
    }

	public static void dfs(int depth) {
		
		// 기저조건
		// 모든 우리(N개)에 햄스터를 다 배치했으면 종료하고 리터
		if(depth==N) {
			if(isVaild()) {
				updateBest();
			}
			return;
		}
		
		// 수행 로직
		
	}
}
