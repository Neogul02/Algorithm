import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 6808. 규영이인영이의 카드도박
 * @author neogul02
 * 
 * -> 18장으로 게임하는데, 9장 9장 서로 나눠가지고 9번 게임함
 * -> 서로 낸 숫자가 같으면 무승부, 높은사람은 낸 카드 두개 더한값 +=, 낮은사람은 바보
 * 
 * 1. 테스트 케이스의 개수 T 입력받아 반복하기 - line 1
 *  1-1. 규영이의 카드 9개를 입력받아 배열로 저장해둔다. -> 저장한 값의 숫자인덱스를 true로 설정해둔다.
 *  1-2. 인영이의 카드 9개를 18개 카드 중 규영이가 뽑지않은 카드들로 고정해둔다.
 *  -> GyuCards(고정) vs InCards (순서 섞기) 준비
 *  1-3. 순열 생성-> 인영이 카드를 어떤순서로 낼지 결정해야함
 *  1-4. DFS 재귀로 풀건데 perm(라운드 수, 규영점수, 인영점수)로 구현
 * 
 * 9! = 362880
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int[] GyuCards, InCards; 
	static boolean[] isSelected; // 인영이가 낸 카드 체크용 (중복 방지)
	static int winCnt, loseCnt;  // 승/패 카운트
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			GyuCards = new int[9];
			InCards = new int[9];
			boolean[] isGyuCard = new boolean[19]; // 1~18 숫자
			

			// 규영 카드 입력
			for(int i=0; i<9; i++) {
				GyuCards[i] = Integer.parseInt(st.nextToken());
				isGyuCard[GyuCards[i]] = true;
			}

			// 인영 카드 채우기 (규영이 없는 숫자들)
			int idx = 0; 
			
			for(int i=1; i<=18; i++) {
				if(!isGyuCard[i]) { 
					InCards[idx++] = i;
				}
			}

			winCnt = 0;
			loseCnt = 0;
			isSelected = new boolean[9]; // 인영 9장 카드 사용 여부 체크
			
			// (0번째 판, 규영점수 0, 인영점수 0)
			perm(0, 0, 0);
			
			sb.append("#").append(tc).append(" ")
			  .append(winCnt).append(" ").append(loseCnt)
			  .append("\n");
		}
		System.out.println(sb);
	}
	
	// cnt: 현재 진행 중인 라운드 (0 ~ 8)
	// gyuSum: 규영이의 현재 총점
	// inSum: 인영이의 현재 총점
	public static void perm(int cnt, int gyuSum, int inSum) {
		// 9판 다 한 경우 ++
		if(cnt == 9) {
			if(gyuSum > inSum) winCnt++;
			else if(gyuSum < inSum) loseCnt++;
			return; // 끝
		}
		
		// 인영이가 낼 수 있는 카드 9장
		for(int i=0; i<9; i++) {
			// 이미 이전에 낸 카드면 패스
			if(isSelected[i]) continue; 
			
			// 1. 선택함
			isSelected[i] = true; 
			
			// 이번 라운드 카드 비교
			int gyuCard = GyuCards[cnt]; // 규영 순서대로
			int inCard = InCards[i];     // 인영 i번째
			
			int nextGyuSum = gyuSum;
			int nextInSum = inSum;
			
			if(gyuCard > inCard) nextGyuSum += (gyuCard + inCard);
			else if(gyuCard < inCard) nextInSum += (gyuCard + inCard);
			
			// 다음 판
			perm(cnt + 1, nextGyuSum, nextInSum);
			
			// 돌아옴 -> 선택 취소 (체크 해제)
			isSelected[i] = false; 
		}
	}
}