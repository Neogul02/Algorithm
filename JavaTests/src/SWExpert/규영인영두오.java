package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class 규영인영두오 {
	
	static int[] gArr, iArr;
	static int M = 9;
	static int winCnt, loseCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=TC; tc++) {
			gArr = new int[M];
			iArr = new int[M];
			
			boolean[] picked = new boolean[2*M+1];
			
			winCnt = loseCnt = 0;
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int i=0; i<M; i++) { // 규영이 카드 입력받기 (fix)
				gArr[i] = Integer.parseInt(st.nextToken());
				picked[gArr[i]] = true; // 사용됨
			}
			int idx=0;
			for(int i=1; i<19; i++) {
				if(picked[i]==false) {
					iArr[idx++] = i;
				}
			}
			
			permutation(0, new boolean[M], 0, 0);
			System.out.println("#"+tc+" "+ winCnt+" "+loseCnt);
			
//			System.out.println(Arrays.toString(gArr));
//			System.out.println(Arrays.toString(iArr));
		}
		
	}
	static int[] isSelected;
	static int[] numbers;
	
	public static void permutation(int cnt, boolean[] isSelected, int gScore, int iScore) {
		// 기저조건

		if(cnt==M) { // 9개 라운드 다 진행한거임
			if(gScore>iScore) ++winCnt;
			else ++loseCnt;
			return;
		}
		
		
		// 구현
		for(int i=0; i<M; i++) {
			if(isSelected[i] == true) continue;
			isSelected[i] = true;
			int sum = gArr[cnt] + iArr[i];
			int diff = gArr[cnt] -iArr[i];
			permutation(cnt+1, isSelected, gScore+(diff>0?sum:0), iScore+(diff>0?0:sum));
			isSelected[i] = false;
		}
	}

}
