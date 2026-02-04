package template;

import java.util.Arrays;

public class DP {
    // LIS (최장 증가 부분 수열)
    public static int lis(int[] arr) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        
        for(int i = 1; i < arr.length; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        return Arrays.stream(dp).max().getAsInt();
    }
    
    // 배낭 문제 (Knapsack)
    public static int knapsack(int[] weight, int[] value, int capacity) {
        int n = weight.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        for(int i = 1; i <= n; i++) {
            for(int w = 1; w <= capacity; w++) {
                if(weight[i-1] <= w) {
                    dp[i][w] = Math.max(
                        dp[i-1][w],
                        dp[i-1][w - weight[i-1]] + value[i-1]
                    );
                } else {
                    dp[i][w] = dp[i-1][w];
                }
            }
        }
        
        return dp[n][capacity];
    }
    
    // 피보나치
    public static int fibonacci(int n) {
        if(n <= 1) return n;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        
        return dp[n];
    }
    
    public static void main(String[] args) {
        System.out.println("=== LIS (최장 증가 부분 수열) ===");
        int[] arr1 = {10, 20, 10, 30, 20, 50};
        System.out.println("LIS 길이: " + lis(arr1));
        
        System.out.println("\n=== 배낭 문제 ===");
        int[] weight = {2, 1, 3, 2};
        int[] value = {3, 2, 4, 2};
        int capacity = 5;
        System.out.println("최대 가치: " + knapsack(weight, value, capacity));
        
        System.out.println("\n=== 피보나치 ===");
        System.out.println("F(10) = " + fibonacci(10));
    }
}