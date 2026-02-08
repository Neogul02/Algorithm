package codeTests;

public class SlidingWindow {
    public static int maxSubArraySum(int[] arr, int k) {
        int n = arr.length;
        
        // 예외 처리: 배열 길이가 k보다 작으면 계산 불가
        if (n < k) return -1;

        // 1. 첫 번째 윈도우(0 ~ k-1)의 합을 먼저 구함 (초기값 설정)
        int currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += arr[i];
        }

        // 현재까지의 최대 합을 첫 윈도우 합으로 설정
        int maxSum = currentSum;

        // 2. 윈도우를 한 칸씩 오른쪽으로 밀기 (인덱스 k부터 끝까지)
        // i는 '새로 들어오는 값'의 인덱스
        for (int i = k; i < n; i++) {
            // 핵심 로직: 기존 합 + (새로 들어온 값) - (빠져나가는 값)
            // 들어오는 값: arr[i]
            // 빠져나가는 값: arr[i - k] (윈도우의 맨 앞)
        	
            currentSum = currentSum + arr[i] - arr[i - k];
            System.out.println(currentSum);
            

            // 최대값 갱신
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        
        System.out.println("최대 합: " + maxSubArraySum(arr, k)); 
        // 출력: 9 (부분 수열 [5, 1, 3])
    }
}