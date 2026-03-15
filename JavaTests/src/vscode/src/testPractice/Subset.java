package testPractice;
import java.util.*;

public class Subset {
    static int n;
    static int[] arr;
    static boolean[] sel;

    static void subset(int idx) {
        if (idx == n) {
            // sel[] 기반으로 부분집합 사용
            ArrayList<Integer> s = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (sel[i]==true){
                    s.add(arr[i]);
                }
                    
            }
                
            System.out.println(s);
            return;
        }
        sel[idx] = false; 
        subset(idx + 1); // 미포함

        sel[idx] = true;  
        subset(idx + 1); // 포함
    }

    public static void main(String[] args) {
        arr = new int[]{1,2,3};
        n = arr.length;
        sel = new boolean[n];
        subset(0);
    }
}