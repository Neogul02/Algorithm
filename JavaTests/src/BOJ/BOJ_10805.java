package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10805 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int[] arr = new int[21];
	
	static void reverse(int s, int e) {
        while (s < e) {
            int temp = arr[s];
            arr[s] = arr[e];
            arr[e] = temp;
            // 포인터 이동
            s++;
            e--;
        }
    }
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=1; i<=20; i++) {
			arr[i] = i;
		}

		for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            reverse(start, end);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 20; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
	}
}
