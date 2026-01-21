class Solution {
    public int solution(int a, int b) {

        String a_str = Integer.toString(a);
        String b_str = Integer.toString(b);

        String ab_concat = a_str + b_str;
        String ba_concat = b_str + a_str;

        int ab_int = Integer.parseInt(ab_concat);
        int ba_int = Integer.parseInt(ba_concat);

        return Math.max(ab_int, a*b*2);

    }
}