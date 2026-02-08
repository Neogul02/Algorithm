package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 9229. 한빈이와 Spot Mart
 * @author neogul02
 * Main
 * 1. testCase Num : T 입력받기
 * 2. T만큼 반복
 * 2-1. 요소의 개수 N과 최대 M그램까지 들 수 있는 int 변수를 공백으로 입력받음
 * 2-2. N개의 요소를 입력받아 int[] arr 에 저장해두고 오름차순으로 정렬해두기
 * 3. findMax로 넘기기 
 *
 * findMax
 * 1. 투포인터로 양쪽에서 시작 배열 맨처음이 가장 가벼운거, 맨마지막이 가장 무거운거
 * 2. 두 과자를 골라야 하므로 left와 right가 만나기 전까지만
 * 3. 제한 이하라면 최대값 갱신 후, 더 큰 값을 위해 왼쪽을 오른쪽으로 이동
 * 4. 제한을 초과하면 값을 줄여야 하므로 오른쪽을 왼쪽으로 이동
 */
public class SWExpert_한빈이와SpotMart {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static int findMax(int[] arr, int N, int M) {
        int left = 0;
        int right = N - 1;
        int max = -1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum <= M) {
                max = Math.max(max, sum);
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 개수
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine().trim()," ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine().trim()," ");
			int [] arr = new int[N];
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr); // 정렬 오름차순
			
			sb.append("#").append(tc).append(" ")
			.append(findMax(arr, N, M)).append("\n");
		}
		System.out.println(sb);
	}
}
