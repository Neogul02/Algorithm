package template;

public class TwoPointer {
    // 연속된 부분 수열의 합
    public static int twoPointer(int[] arr, int target) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int count = 0;
        
        while(right < arr.length) {
            sum += arr[right];
            
            while(sum > target) {
                sum -= arr[left];
                left++;
            }
            
            if(sum == target) {
                count++;
            }
            
            right++;
        }
        
        return count;
    }
    
    // 두 수의 합 (정렬된 배열)
    public static boolean twoSum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while(left < right) {
            int sum = arr[left] + arr[right];
            
            if(sum == target) {
                System.out.println("찾음: " + arr[left] + " + " + arr[right]);
                return true;
            } else if(sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        System.out.println("=== 연속 부분 수열의 합 ===");
        System.out.println("합이 7인 경우의 수: " + twoPointer(arr1, 7));
        
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("\n=== 두 수의 합 ===");
        twoSum(arr2, 15);
    }
}