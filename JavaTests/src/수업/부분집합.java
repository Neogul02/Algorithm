package 수업;

import java.util.Scanner;

/**
 * 
 * @author SSAFY
 * 2의 30승 = 10억
 * 2의 20승 = 100만
 * 2의 10승 = 1000
 */
public class 부분집합 {
	
	static int N;
	static int[] input;
	static int totalCnt =0;
	static boolean[] isSelected ;


	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N =sc.nextInt();
		int targetSum = sc.nextInt();
		input = new int[N];
		isSelected = new boolean[N];
		for(int i=0; i<N; i++) {
			input[i] = sc.nextInt();
		}
		
//		generateSubset(0);
		generateSubsetSum(0, 0, targetSum);
		System.out.println(totalCnt);
	}
	
	static void generateSubset(int depth) {
	    // 1. 종료 조건: 모든 사람(3명)에게 다 물어봤을 때
	    if (depth == N) {
	        for (int i = 0; i < N; i++) {
	            System.out.print(isSelected[i]?input[i]:"X"); 
	        }
	        System.out.println();
	        return;
	    }

	    // 2. 현재 사람(depth)을 포함하는 경우
	    isSelected[depth] = true;
	    generateSubset(depth + 1);

	    // 3. 현재 사람(depth)을 포함하지 않는 경우
	    isSelected[depth] = false;
	    generateSubset(depth + 1);
	}
	
	static void generateSubsetSum(int depth, int pickCnt, int targetSum) {
	    // 1. 종료 조건: 모든 사람(3명)에게 다 물어봤을 때
	    if (depth == N) {
	    	int sum = 0;
	    	for(int i =0; i<N; i++) {
	    		if(isSelected[i] == true) sum+=input[i];
	    	}
	    	if(pickCnt>0 && targetSum == sum ) ++totalCnt;
	        
	        return;
	    }

	    // 2. 현재 사람(depth)을 포함하는 경우
	    isSelected[depth] = true;
	    generateSubsetSum(depth + 1, pickCnt+1, targetSum);

	    // 3. 현재 사람(depth)을 포함하지 않는 경우
	    isSelected[depth] = false;
	    generateSubsetSum(depth + 1, pickCnt, targetSum);
	}

	static void generateSubsetSum2(int depth, int pickCnt, int sum, int targetSum) {
	    // 1. 종료 조건: 모든 사람(3명)에게 다 물어봤을 때
	    if(depth == N) {
	    	if(pickCnt>0 && targetSum == sum) ++totalCnt;
	    	return;
	    }

	    // 2. 현재 사람(depth)을 포함하는 경우
	    isSelected[depth] = true;
	    generateSubsetSum2(depth + 1, pickCnt+1, sum+input[depth], targetSum);

	    // 3. 현재 사람(depth)을 포함하지 않는 경우
	    isSelected[depth] = false;
	    generateSubsetSum2(depth + 1, pickCnt, sum,targetSum);
	}

}
