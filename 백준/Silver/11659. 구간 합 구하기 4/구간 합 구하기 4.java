import java.io.*;
import java.util.*;
/**
 * 11659. 구간합4
 * @author neogul02
 * 
 * 1. 사용자에게 수의 개수 N과 M을 입력받는다.
 * 2. 숫자 N개를 입력받는다.
 * 3. N 길이보다 1칸 더 크게 누적합을 저장해 둘 배열 sumArr를 저장해두고 누적합을 계산해서 저장한다.
 * 4. i와 j 구간을 입력받아 누적합 배열 j인덱스에서 i인덱스를 빼면 i~j 사이의 합이 나올것이다.
 */
public class Main {
    static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 1. 사용자에게 수의 개수 N과 M을 입력받는다.
		st = new StringTokenizer(br.readLine().trim(), " ");
		int N = Integer.parseInt(st.nextToken()); // 수의 개수 N
		int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수 M

		st = new StringTokenizer(br.readLine().trim(), " ");
		int[] sumArr = new int[N+1]; // 첫번째 칸은 0으로 비워두기 
		
		// 2. 숫자 N개를 입력받는다.
		for(int i=1; i<=N; i++) {
			// 3. N 길이보다 1칸 더 크게 누적합을 저장해 둘 배열 sumArr를 저장해두고 누적합을 계산해서 저장한다.
			sumArr[i] = sumArr[i - 1] + Integer.parseInt(st.nextToken());
		}
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			// sumArr[3] 은 sumArr[1]~sumArr[3]까지의 합이니까 여기서 i-1번 인덱스를 빼면 합이 계산될것임 
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			sb.append(sumArr[j]-sumArr[i-1]).append("\n");
		}
		System.out.println(sb);
	}
}