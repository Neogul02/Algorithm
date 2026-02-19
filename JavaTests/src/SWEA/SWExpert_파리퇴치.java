package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * @author ChoeJinHyeong
 * 2001. 파리퇴치
 * 
 * 1. testCase T 입력받아 for문 루프
 * 	1-1. 입력받는 즉시 누적합을 계산하여 저장, (N+1) x (N+1) 크기의 배열 사용.
 * 2. fly_map[i][j] = 위쪽(i-1, j) + 왼쪽(i, j-1) - 대각선중복(i-1, j-1) + 현재값
 *  2-1. (1, 1)부터 (i, j)까지의 직사각형 영역 내 모든 파리 수의 합을 저장.
 * 3. 공식: 전체(i, j) - 위쪽제외(i-M, j) - 왼쪽제외(i, j-M) + 중복제거복구(i-M, j-M)
 * 해 M x M 크기의 부분 합을 뽑고 최대값 리턴.
 *
 */
public class SWExpert_파리퇴치 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] fly_map = new int[N+1][N+1];
			
			for(int i=1; i<N+1; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=1; j<N+1; j++) {
					int currentFly = Integer.parseInt(st.nextToken());
                    
                    // 2. 누적합 구하기 (현재 좌표까지의 박스 전체 합)
                    // 공식: 위 + 왼쪽 - 대각선위(중복) + 현재값
					fly_map[i][j] = fly_map[i-1][j] + fly_map[i][j-1] - fly_map[i-1][j-1] + currentFly;
				}
			}
			
			int max =0;
			
			for(int i=M; i<=N; i++) {
                for(int j=M; j<=N; j++) {
                    // 4. 구간 합 구하기
                    // 공식: 전체(S[i][j]) - 위쪽날리기 - 왼쪽날리기 + 두번빠진거복구
                    int sum = fly_map[i][j] - fly_map[i-M][j] - fly_map[i][j-M] + fly_map[i-M][j-M];
                    
                    if(sum > max) max = sum;
                }
            }
			
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
}
