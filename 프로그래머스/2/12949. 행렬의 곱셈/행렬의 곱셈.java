class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int M = arr1.length;
        int K = arr1[0].length;
        int N = arr2[0].length;

        int[][] result = new int[M][N];

        for (int i = 0; i < M; i++) { 
            for (int j = 0; j < N; j++) { 
                for (int k = 0; k < K; k++) {
                    result[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        return result;
    }
}