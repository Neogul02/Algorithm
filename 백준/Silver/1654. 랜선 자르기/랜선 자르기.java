import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1654. 랜선 자르기
 * @author neogul02
 * 
 * K 개의 랜선을 가지고있음, < 길이가 다 다름
 * 모두 N개의 같은 길이의 랜선으로 만들어야함 
 * K==N 은 안됨, 손실 없음, 정수 단위로 자름, N개보다 많이 만들어도 됨. 
 * = 가장 긴 길이로 N개 이상의 랜선을 만드는 maxLen 을 구하면 될듯
 * 
 * 1. K, N을 입력받는다
 * 2. 입력받은 K 길이의 랜선 중 최대 길이 랜선을 찾는다
 * 3. 최대길이의 랜선을 mid 길이로 잘랐을 때 만들 수 있는 개수 count 계산
 *  3-1. count >= N 이면 → 더 길게 잘라도 되는지 확인 (low = mid+1)
 *  3-2. count < N 이면 → 길이를 줄여야 함 (high = mid-1)
 */
public class Main {
	
	public static void main(String[] args) throws IOException{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine().trim()," ");
		 
		 int K = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선 개수 K
		 int N = Integer.parseInt(st.nextToken()); // 만들고 싶은 랜선 개수 N
		 
		 int[] K_Arr = new int[K];
		 int maxLen = Integer.MIN_VALUE;
		 
		 for(int i=0; i<K; i++) {
			 K_Arr[i] = Integer.parseInt(br.readLine().trim());
			 maxLen = Math.max(maxLen, K_Arr[i]);
		 }
		 
		 long low = 1;
	        long high = maxLen;
	        long ans = 0;

	        while (low <= high) {
	            long mid = (low + high)/2; 

	            long cnt = 0;
	            for (int x : K_Arr) {
	                cnt += (x / mid);
	                if (cnt >= N) break; // 조기 종료
	            }

	            if (cnt >= N) {
	                ans = mid;
	                low = mid + 1;
	            } else {
	                high = mid - 1;
	            }
	        }

	        System.out.print(ans);
	}
}
