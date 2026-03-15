package testPractice;
import java.util.*;

public class Combination {
    static int n, r;
    static int[] arr;
    static int[] pick;

    static void comb(int depth, int start) {
        if (depth == r) {
            System.out.println(Arrays.toString(pick));
            return;
        }
        for (int i = start; i < n; i++) {
            pick[depth] = arr[i];
            comb(depth + 1, i + 1);
        }
    }

    public static void main(String[] args) {
        arr = new int[]{1,2,3,4};
        n = arr.length; r = 3;
        pick = new int[r];
        comb(0, 0);
    }
}