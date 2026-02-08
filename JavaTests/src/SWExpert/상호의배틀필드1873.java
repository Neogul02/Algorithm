package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 1873. 상호의 배틀필드
 * @author neogul02
 * - line 1 TC
 * - line 2 H W = H*W 크기 격자판
 * - line 3 ~ H까지 길이가 W인 문자열
 * - 입력 개수 N
 * - 문자열 공백없이 입력됨
 * 
 * .	평지(전차가 들어갈 수 있다.)
 * *	벽돌로 만들어진 벽
 * #	강철로 만들어진 벽
 * -	물(전차는 들어갈 수 없다.)
 */
public class 상호의배틀필드1873 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int H, W;
	static char[][] map;
	static int tankR, tankC, tankDir; // 전차 행, 열, 방향(0:상, 1:하, 2:좌, 3:우)
	
	static int[] dr = {-1, 1, 0, 0}; 
	static int[] dc = {0, 0, -1, 1};
	static char[] tankShapes = {'^', 'v', '<', '>'}; // 방향별 전차 모양
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			// H, W 공백 기준 입력
			st = new StringTokenizer(br.readLine().trim());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			// map 입력받기 H*W
			for(int h=0; h<H; h++) {
				String line = br.readLine(); // 한 줄 통째로 읽기
				for(int w=0; w<W; w++) {
					map[h][w] = line.charAt(w);
					
					// 전차 찾기
					if(map[h][w] == '^') { tankR=h; tankC=w; tankDir=0; }
					else if(map[h][w] == 'v') { tankR=h; tankC=w; tankDir=1; }
					else if(map[h][w] == '<') { tankR=h; tankC=w; tankDir=2; }
					else if(map[h][w] == '>') { tankR=h; tankC=w; tankDir=3; }
				}
			}
			
			// N 입력받기
			int N = Integer.parseInt(br.readLine());
			
			// char[] 로 나누기
			String cmds = br.readLine().trim(); 
			int cnt=0;
			for(int i=0; i<N; i++) {
				char cmd = cmds.charAt(i);
				action(cmd);
			}
			
			
			sb.append("#").append(tc).append(" ");
			for(int h=0; h<H; h++) {
				for(int w=0; w<W; w++) {
					sb.append(map[h][w]);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
	public static void action(char cmd) {
		// shoot
		if(cmd == 'S') {
			
			int tempR = tankR;
			int tempC = tankC;
			
			while(true) {
				// 탱크 방향쪽으로 발사 ->> 계속 진행
				tempR += dr[tankDir];
				tempC += dc[tankDir];
				// 범위 안이 아니면
				if(tempR<0 || tempR >=H || tempC<0 || tempC>=W) {
					break;
				}
				
				// 강철벽은 못뚫음
				if(map[tempR][tempC] == '#'){
					break;
				}
				
				// 일반 벽이면 부수고 '.' 처리
				if(map[tempR][tempC] == '*') {
					map[tempR][tempC] ='.'; 
					break;
				}
			}

		}else { // move
			if(cmd == 'U') tankDir = 0;
			else if(cmd == 'D') tankDir = 1;
			else if(cmd == 'L') tankDir = 2;
			else if(cmd == 'R') tankDir = 3;
			
			map[tankR][tankC] = tankShapes[tankDir];
			
			int nr = tankR + dr[tankDir];
			int nc = tankC + dc[tankDir];
			
			// 배열 안이고 평지면 이동 가능, 기존 땅은 평지로 변환
			if(nr>=0 && nr<H && nc>=0 && nc<W && map[nr][nc]=='.') {
				map[tankR][tankC] = '.';
				tankR = nr;
				tankC = nc;
				map[nr][nc] = tankShapes[tankDir];
			}
		}
	}
}
	
	

