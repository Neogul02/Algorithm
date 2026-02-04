import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * @author neogul02
 * 4130. 특이한 자석
 *
 * 자석의 개수는 4 개이며, 각 자석은 8 개의 날을 가지고 있다.
 * 자석을 회전시키는 방향은 시계방향이 1 로, 반시계방향이 -1 로 주어진다.
 * ex) 1 1 -> 1번째 자석을 시계방향으로 회전 / 3 -1 -> 3번째 자석을 반시계 방향으로 회전
 * 
 * 날의 자성은 N 극이 0 으로, S 극이 1 로 주어진다.
 * 중요 ! 하나의 자석이 1 칸 회전될 때, 
 * 붙어 있는 자석은 서로 붙어 있는 날의 자성이 다를 경우에만 반대 방향으로 1 칸 회전....??????
 * 
 * 1. 테스트 케이스의 개수 T 입력받아 반복하기 - line 1
 * 	1-1. 회전의 횟수 K를 입력받는다 - line 2 
 * 	1-2. line 3~ line 6 까지 공백기준 8개 4줄을 LinkedList에 넣는다
 *  	-> LinkedList<Integer>[] [5] 선언하고 1,2,3,4번 인덱스만 쓴다 -1 방지
 * 	1-3. 회전 횟수 K만큼 반복한다.
 * 	 1-3-1. 공백 기준 회전할 자석 int와 회전 방향 1, -1 을 입력받아 변수로 저장 - line 7 ~ K+7
 *   1-3-2. dir[5] 만들어서 2번째 6번째 인덱스 값을 비교한다 같은 극일때와 다른극일때 방향을 dir에 저장
 *   1-3-3. dir 배열의 방향대로 rotate 함수에 넣어 각 자석을 회전, 회전 방향이 있는경우에만 회전!! -1 or 1
 *  1-4. 각 배열의 0번째 인덱스를 돌며 1,2,4,8 점수를 합산 +=
 *  1-5. StringBuilder sb 에 저장
 * 2. sb 출력!
 * 
 * [0,1,'2',3,4,5,'6',7]
 * 1번째 자석 -> 2번 인덱스
 * 2번째 자석 -> 6번 인덱스, 2번 인덱스
 * 3번째 자석 -> 6번 인덱스, 2번 인덱스
 * 4번째 자석 -> 2번 인덱스
 * -> 2번과 6번을 비교해줘야함!!!
 * 
 * 1-a. rotate 함수 구현 -> static magnet의 인덱스와 회전 방향을 main에서 파라미터로 받는다?
 * 돌아가는걸 어떻게 구현하지? -> 0번 인덱스가 빨간 화살표니까 0번과 마지막 인덱스의 위치를 바꾸는 느낌으로 deque?
 * 시계방향 돌리기 -> 자석 하나의 자성 정보를 deque에 전부 넣어두고 pollLast() 해서 addFirst();
 * 반시계방향 돌리기 -> poll() 해서 addLast(); 앞에서 뽑아서 뒤에다 넣기
 *  
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static LinkedList<Integer>[] magnets;
	
	public static void rotate(int idx, int dir) {
        if(dir == 1) { // 시계 방향: 뒤에서 빼서 앞으로
            magnets[idx].addFirst(magnets[idx].pollLast());
        } else { // 반시계 방향: 앞에서 빼서 뒤로
            magnets[idx].addLast(magnets[idx].pollFirst());
        }
    }
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			int K = Integer.parseInt(st.nextToken());
			
			magnets = new LinkedList[5];
			// 4개 자석 Deque 초기화
			for(int i=1; i<5; i++) {
                magnets[i] = new LinkedList<>();
                st = new StringTokenizer(br.readLine().trim());
                for(int j=0; j<8; j++) {
                    magnets[i].add(Integer.parseInt(st.nextToken()));
                }
            }
			
			// K 번 회전
			for(int k=0; k<K; k++) {
				st = new StringTokenizer(br.readLine().trim());
				int targetNum = Integer.parseInt(st.nextToken());
				int targetDir = Integer.parseInt(st.nextToken());
				
				int[] dir = new int[5];
				dir[targetNum] = targetDir; // 사용자가 정한 방향 저장
				
				// 미리 다른 자석들이 어느방향으로 돌아야할지 생각해놔야함
				// target보다 작은쪽 <- 방향
				for(int i = targetNum; i>1; i--) {
					if (magnets[i].get(6) != magnets[i-1].get(2)) {
						dir[i-1] = -dir[i]; // 극이 다르면 반대로 회전 
					}else {
						break; // 극이 같을때 N-N, S-S 
					}
				}
				
				// target보다 큰쪽 -> 방향
				for(int i = targetNum; i<4; i++) {
					if(magnets[i].get(2) != magnets[i+1].get(6)) {
						dir[i+1] = -dir[i];
					}else {
						break; // 극이 같을때 N-N, S-S
					}
				}
				
				for(int i=1; i<=4; i++) {
					if(dir[i] != 0) {
                        rotate(i, dir[i]);
                    }
				}	
			}
			int score = 0;
			if(magnets[1].get(0)==1) score+=1;
			if(magnets[2].get(0)==1) score+=2;
			if(magnets[3].get(0)==1) score+=4;
			if(magnets[4].get(0)==1) score+=8;
			
			sb.append("#").append(tc).append(" ").append(score).append("\n");	
		}
		System.out.println(sb);
	}
}