package codeTests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Copy {

	StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int arr[] = new int[1005];
	static int dp[][] = new int[1005][2];
	static int N;
	static int M;
	static int tmp[] = new int[1005];
	
	static void merge(int left, int mid, int right) {
		int i=left,j=mid+1,k=left;
		while(i<=mid&&j<=right) {
			if(arr[i] <= arr[j]) tmp[k++] = arr[i++];
			else tmp[k++] = arr[j++];
		}
		
		while(i<=mid) tmp[k++] = arr[i++];
		while(j<=right) tmp[k++] = arr[j++];
		
		for(int e=left;e<=right;e++) arr[e] = tmp[e];
	}
	
	static void mergeSort(int left, int right) {
		if(left >= right) return;
		int mid = left + (right - left) / 2;
		
		mergeSort(left, mid);
		mergeSort(mid+1,right);
		merge(left,mid,right);
	}
	
	static int findMaxItem(int cur) {
		int res = -1;
		int left = 0, right = cur - 1;
		while(left <= right) {
			int mid = (left + right) / 2;			
			if(arr[mid] + arr[cur] > M) right = mid - 1;
			else {
				res = mid;
				left = mid + 1;
			}
		}		
		return res;
	}
	
	public static void main(String[] args) throws IOException{
		int test_case = readInt();
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=test_case;tc++) {
			N = readInt();
			M = readInt();
			for(int i=0;i<N;i++) arr[i] = readInt();
			
			mergeSort(0,N-1);
			
			int ans = -1;
			for(int i=1;i<N;i++) {
				int f = findMaxItem(i);
				if(f != -1) ans = Math.max(ans, arr[i] + arr[f]);
			}
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.print(sb);
	}
	
	static int readInt() throws IOException{
		int n = 0;
		int c = br.read();
		while(c <= 32) {if(c == -1) break; c = br.read(); }
		while('0' <= c && c <= '9') {
			n = n * 10 + (c - '0');
			c = br.read();
		}
		return n;
	}
}
