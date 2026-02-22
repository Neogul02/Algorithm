import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 16435. 스네이크버드
 * @author neogul02
 * 
 * 과일 하나를 먹으면 길이가 1만큼 늘어남, 작거나 같은 높이에 있는 과일만 먹을 수 있음
 * 과일을 먹었을때 최댓값을 구하면 되니까  현재 길이랑 가장 비슷한 걸 먹어야함 
 * -> 현재 길이보다 같거나 작은걸 찾으면 바로 먹어도 됨 
 * 가장 작은거부터 먹으려면 정렬을 한 번 시키고 앞에서부터 돌면 좋을거같음
 */
public class S1_16435_스네이크버드 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N; // 과일의 개수
	static int L; // 스네이크버드 초기 길이
	
	static int[] fruits;
	
	public static void main(String[] args) throws IOException{
		input();
		solve();
		System.out.print(L);
	}

	/**
	 * @solve
	 * 1. 입력받은 fruits 배열을 오름차순으로 정렬
	 * 2. 첫번째 과일부터 현재 L 길이와 비교하며
	 * 3. L보다 작거나 같다면 먹고 크기 1 증가
	 */
	private static void solve() {
		Arrays.sort(fruits); // 오름차순으로 정렬
		for(int fruit : fruits) {
			if(fruit <= L) {
				L++; // 크기 1 증가
			}else {
				break; // 더 이상 못먹으면 루프 종료
			}
		}
	}

	private static void input() throws IOException{
		st = new StringTokenizer(br.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		fruits = new int[N];

		st = new StringTokenizer(br.readLine().trim()," ");
		for(int i=0; i<N; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}
	}
}