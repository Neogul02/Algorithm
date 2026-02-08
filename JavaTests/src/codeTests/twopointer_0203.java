package codeTests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class twopointer_0203 {

	static BufferedReader br;
	
	public void 두수의합() throws IOException {
		// TODO Auto-generated method stub
		 br = new BufferedReader(new InputStreamReader(System.in));
		 
		 StringTokenizer st = new StringTokenizer(br.readLine()," ");
		 int N = Integer.parseInt(st.nextToken());
		 int target = Integer.parseInt(st.nextToken());
		 
		 int[] arr = new int[N];
		 st = new StringTokenizer(br.readLine()," ");
		 
		 for(int i=0; i<N; i++) {
			 arr[i] = Integer.parseInt(st.nextToken());
		 }
		 
		 Arrays.sort(arr); // natural sort
		 int s=0, e= N-1, ans=0;
		 while(s<e) {
			 int sum = arr[s] + arr[e];
			 if(sum == target) { // 더한 값이 목표값과 같음 
				 ++ans;
				 ++s;
				 --e;
			 }else if(sum<target) { // 더한 값이 목표값보다 작음: 값을 크게 만들어보자
				 ++s;
			 }else { // 더한 값이 목표값보다 크면?? : 값을 작게 만들어보자
				 --e;
			 }
		 }
		 System.out.println(ans);
		 br.close();
	}
	
	public int countSubarraySum() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		 
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		 
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine()," ");
		 
		 for(int i=0; i<N; i++) {
			 arr[i] = Integer.parseInt(st.nextToken());
		 }
	    int count = 0;
	    int s = 0, e = 0, sum = 0;
	    int n = arr.length;

	    while (true) {
	        if (sum >= target) {    // 합이 목표보다 크거나 같으면 s를 밀어서 구간 축소
	            sum -= arr[s++];
	        } else if (e == n) {    // e가 끝에 도달했는데 sum이 target보다 작으면 더이상 가망 없음
	            break;
	        } else {                // 합이 목표보다 작으면 e를 밀어서 구간 확장
	            sum += arr[e++];
	        }

	        if (sum == target) {    // 목표 합을 찾은 경우
	            count++;
	        }
	    }
	    System.out.println(count);
	    return count;
	}
	
	public static void main(String[] args) {
		twopointer_0203 t = new twopointer_0203();
		try {
//			t.두수의합();
			t.countSubarraySum();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
