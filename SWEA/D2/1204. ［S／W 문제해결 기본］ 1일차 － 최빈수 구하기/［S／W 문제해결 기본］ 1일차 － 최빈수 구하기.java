import java.util.Scanner;

class Solution {
	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++) {
            int caseId = sc.nextInt();
            int[] counts = new int[101];
			for(int i = 0; i < 1000; i++) {
				int num = sc.nextInt();
                counts[num] = counts[num] + 1;
			}

            int mode = 0;
            int maxFreq = 0;
            for (int score = 0; score < counts.length; score++) {
                if (counts[score] > maxFreq || (counts[score] == maxFreq && score > mode)) {
                    maxFreq = counts[score];
                    mode = score;
                }
            }

			System.out.println("#" + caseId + " " + mode);
		}
        sc.close(); 
	}
}