package template;
import java.util.Arrays;

public class Backtracking {
    static int N = 4, R = 3;
    static int[] arr = {1, 2, 3, 4};
    static boolean[] visited;
    static int[] selected;
    
    // 순열
    public static void permutation(int depth) {
        if(depth == R) {
            System.out.println(Arrays.toString(selected));
            return;
        }
        
        for(int i = 0; i < N; i++) {
            if(visited[i]) continue;
            
            visited[i] = true;
            selected[depth] = arr[i];
            permutation(depth + 1);
            visited[i] = false;
        }
    }
    
    // 조합
    public static void combination(int depth, int start) {
        if(depth == R) {
            System.out.println(Arrays.toString(selected));
            return;
        }
        
        for(int i = start; i < N; i++) {
            selected[depth] = arr[i];
            combination(depth + 1, i + 1);
        }
    }
    
    // 중복 순열
    public static void permutationWithRepetition(int depth) {
        if(depth == R) {
            System.out.println(Arrays.toString(selected));
            return;
        }
        
        for(int i = 0; i < N; i++) {
            selected[depth] = arr[i];
            permutationWithRepetition(depth + 1);
        }
    }
    
    // 중복 조합
    public static void combinationWithRepetition(int depth, int start) {
        if(depth == R) {
            System.out.println(Arrays.toString(selected));
            return;
        }
        
        for(int i = start; i < N; i++) {
            selected[depth] = arr[i];
            combinationWithRepetition(depth + 1, i);
        }
    }
    
    public static void main(String[] args) {
        selected = new int[R];
        
        System.out.println("=== 순열 P(4,3) ===");
        visited = new boolean[N];
        permutation(0);
        
        System.out.println("\n=== 조합 C(4,3) ===");
        combination(0, 0);
        
        System.out.println("\n=== 중복 순열 ===");
        permutationWithRepetition(0);
        
        System.out.println("\n=== 중복 조합 ===");
        combinationWithRepetition(0, 0);
    }
}