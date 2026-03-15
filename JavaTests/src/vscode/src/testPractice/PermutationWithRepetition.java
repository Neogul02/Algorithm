package testPractice;

import java.util.*;

public class PermutationWithRepetition {
    static int n, r;
    static int[] arr;
    static int[] pick;

    static void permRep(int depth) {
        if (depth == r) {
            System.out.println(Arrays.toString(pick));
            return;
        }
        for (int i = 0; i < n; i++) {
            pick[depth] = arr[i];
            permRep(depth + 1);
        }
    }

    public static void main(String[] args) {
        arr = new int[]{0,1,2}; // 선택지
        n = arr.length; r = 4;
        pick = new int[r];
        permRep(0);
    }
}