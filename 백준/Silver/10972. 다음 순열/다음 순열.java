import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
* [문제] 백준 10972. 다음 순열
* @author neogul02
* 
* 1. 입력 처리
* 	1-1. N 입력 (1 <= N <= 10,000)
*  	1-2. 순열 데이터 배열(arr)에 저장
* 
* 2. Next Permutation 외워야할듯?
* 	2-1. Step 1: 뒤쪽부터 탐색하여 오름차순이 깨지는 지점(꺾이는 지점) i를 찾음. (arr[i-1] < arr[i])
* 	2-2. Step 2: 만약 i가 0보다 작거나 같다면, 이미 내림차순(마지막 순열)이므로 -1 출력 후 종료.
* 	2-3. Step 3: 다시 뒤쪽부터 탐색하여 arr[i-1]보다 큰 첫 번째 값 j를 찾음.
* 	2-4. Step 4: arr[i-1]과 arr[j]를 교환(Swap).
*	2-5. Step 5: i 위치부터 끝까지의 배열을 뒤집음(Reverse) -> 오름차순 정렬 효과.
*
* 3. print
* 
 */
public class Main {
	
	static int N;
	static int[] numbers;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim()); // 숫자 길이 N
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		numbers = new int[N];
		for(int n=0; n<N; n++) {
			numbers[n] = Integer.parseInt(st.nextToken()); // N 길이만큼 입력받은 숫자 순열
		}
		
		StringBuilder sb = new StringBuilder();
		if(nextPermutation()) {
            for(int i=0; i<N; i++) {
                sb.append(numbers[i]).append(" ");
            }
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
	}

	public static boolean nextPermutation() {
		
		int peakIdx = N-1;
		while(peakIdx>0 && numbers[peakIdx-1] >= numbers[peakIdx]) {
			peakIdx--;
		}
		
		if(peakIdx==0) return false;
		
		int swapTargetIdx = N-1;
		
		while(numbers[peakIdx-1] >= numbers[swapTargetIdx]) {
			swapTargetIdx--;
		}
		
		swap(peakIdx-1,swapTargetIdx);
		
		int leftIdx = peakIdx;
		int rightIdx = N-1;
		
		while(leftIdx < rightIdx) {
			swap(leftIdx, rightIdx);
			leftIdx++;
			rightIdx--; 
		}
		return true;		
	}

	private static void swap(int a, int b) {
		// TODO Auto-generated method stub
		int temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;
	}
}