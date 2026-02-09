import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 5215. 햄버거 다이어트 _ nextPermutation ver
 * @author neogul02
 * 
 * 
 * 1. 테스트케이스의 개수 T를 입력받는다. -> T번 반복
 * 2. 재료의 개수 N과 최대 칼로리 L을 입력받는다.
 * 3. N번 만큼 반복하며 각 맛과 칼로리의 정보를 입력받아 배열에 저장해둔다.
 * 4. 부분집합 생성 
 *  4-1. 재료를 1개 뽑는 경우(r=1)부터 N개 뽑는 경우(r=N)까지 반복한다.
 *  4-2. p 배열: 0과 1로 구성된 플래그 배열 (Next Permutation용)
 *
 */
public class Solution {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N,L;
	
	static int[] scores;
	static int[] cals;
	
	static int maxScore;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			scores = new int[N];
			cals = new int[N];
			
			for (int i = 0; i < N; i++) {
	            st = new StringTokenizer(br.readLine().trim());
	            scores[i] = Integer.parseInt(st.nextToken());
	            cals[i] = Integer.parseInt(st.nextToken());
	        }
			maxScore = 0;
			
			for(int r=1; r<=N; r++) {
				
				int[] p = new int[N]; // 플래그 배열
				
//				for(int i= N-1; i>=N-r; i--) {
//					p[i] = 1; 
//				}
				for(int i = 0; i<r; i++) {
					p[i] = 1; // 10000 .. 11000.. 11100
				}
				Arrays.sort(p); // -> 00001, 00011..
				
				// 3. 플래그로 각 자리수 위치 확인
				do {
                    int sumScore = 0;
                    int sumCal = 0;

                    for (int i = 0; i < N; i++) {
                        if (p[i] == 1) { // 1인 위치의 재료 선택
                            sumScore += scores[i];
                            sumCal += cals[i];
                            
                        }
                    }
                    
                    // 칼로리 제한 이하일 때만 최대 점수 갱신
                    if (sumCal <= L) {
                        maxScore = Math.max(maxScore, sumScore);
                        // System.out.print("Arr p: "+Arrays.toString(p));
                        // System.out.print("sumScore: "+sumScore);
                        // System.out.println(" sumCal: "+sumCal);
                    }

                } while (nextPermutation(p));
			}
			sb.append('#').append(tc).append(' ')
			.append(maxScore).append('\n');
			
		}
		
		System.out.print(sb);
			
	}

	public static boolean nextPermutation(int[] arr) {
		
		final int ELEMENT_COUNT = arr.length;
		
		// 뒤에서 부터 시작하기위한 peakIdx 
		int peakIdx = ELEMENT_COUNT-1;
		
		// -1이 아니고, 배열 전칸이 현재 peakIdx 보다 크거나 같으면
		while(peakIdx >0 && (arr[peakIdx-1] >= arr[peakIdx])) { 
			peakIdx--;
		}
		
		// 가장 큰 값이 첫번째 칸에 오면 마지막 순열
		if(peakIdx==0) return false;
		
		int swapTargetIdx = ELEMENT_COUNT - 1;
		
		while(arr[peakIdx-1] >= arr[swapTargetIdx]) {
			swapTargetIdx--;
		}
		// 두 원소를 교환한다.
		swap(arr, peakIdx-1, swapTargetIdx);
		
		// peakIdx 부터 끝까지 뒤집기 (오름차순 정렬)
		int leftIdx = peakIdx;
		int rightIdx = ELEMENT_COUNT-1;
		while(leftIdx<rightIdx) {
			swap(arr, leftIdx, rightIdx);
			leftIdx++;
			rightIdx--;
		}
		return true;
	}

	public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}