package testPractice;

import java.util.*;

public class Permutation {
    static int n, r;
    static int[] arr;     // 원본
    static int[] pick;    // 뽑은 결과
    static boolean[] used;

    static void perm(int depth) {
        if (depth == r) {
            // pick[] 사용
            System.out.println(Arrays.toString(pick));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            used[i] = true;
            pick[depth] = arr[i];
            perm(depth + 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        arr = new int[]{1,2,3,4};
        n = arr.length; r = 3;
        pick = new int[r];
        used = new boolean[n];
        perm(0);
    }
}