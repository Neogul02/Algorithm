package template;
import java.util.Arrays;

public class BinarySearch {
    // 정확한 값 찾기
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(arr[mid] == target) {
                return mid;
            } else if(arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    // 파라메트릭 서치 예제: 나무 자르기
    static int[] trees = {20, 15, 10, 17};
    static int M = 7; // 필요한 나무 길이
    
    public static boolean isPossible(int height) {
        long sum = 0;
        for(int tree : trees) {
            if(tree > height) {
                sum += tree - height;
            }
        }
        return sum >= M;
    }
    
    public static int parametricSearch() {
        int left = 0;
        int right = Arrays.stream(trees).max().getAsInt();
        int result = 0;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(isPossible(mid)) {
                result = mid;
                left = mid + 1; // 더 높은 높이도 가능한지 확인
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};
        
        System.out.println("=== 일반 이분 탐색 ===");
        System.out.println("7의 인덱스: " + binarySearch(arr, 7));
        System.out.println("10의 인덱스: " + binarySearch(arr, 10));
        
        System.out.println("\n=== 파라메트릭 서치 (나무 자르기) ===");
        System.out.println("최대 높이: " + parametricSearch());
    }
}