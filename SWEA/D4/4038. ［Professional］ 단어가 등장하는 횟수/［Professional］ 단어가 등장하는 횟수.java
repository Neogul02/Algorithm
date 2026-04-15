import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 4038. 단어가 등장하는 횟수
 * 
 * @author neogul02
 * 
 *         책의 내용 B가 문자열로 주어지고, 특정 단어 S가 주어질때, S가 B에 몇 번 등장하는지 구하는 문제
 *         B= "ABABA", S="ABA" -> 2개로 간주
 * 
 *         1. 테스트 케이스만큼 반복하기
 *         2. B와 S 입력받기
 *         3. S가 B에 몇 번 등장하는지 구하기
 * 
 *         test 1 = subString으로 완탐 -> 시간 초과
 *         ->
 * 
 *         test 2 = KMP 알고리즘
 * 
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static String B;
    static String S;

    static int result;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            input();
            solve();
            sb.append("#").append(tc).append(" ")
                    .append(result).append("\n");
        }
        System.out.print(sb);
    }

    public static void solve() {
        result = 0;
        // test 1 = subString으로 완탐 -> 시간 초과
        // for (int i = 0; i < B.length() - S.length() + 1; i++) {
        // if (B.substring(i, i + S.length()).equals(S)) {
        // result++;
        // }
        // }

        // test 2 = KMP 알고리즘
        int[] pi = new int[S.length()];
        // pi 배열 계산
        for (int i = 1, j = 0; i < S.length(); i++) {

            while (j > 0 && S.charAt(i) != S.charAt(j)) {
                j = pi[j - 1];
            }
            if (S.charAt(i) == S.charAt(j)) {
                pi[i] = ++j;
            }
        }

        // KMP 검색
        for (int i = 0, j = 0; i < B.length(); i++) {
            while (j > 0 && B.charAt(i) != S.charAt(j)) {
                j = pi[j - 1];
            }
            if (B.charAt(i) == S.charAt(j)) {
                if (j == S.length() - 1) {
                    result++;
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }

    }

    public static void input() throws IOException {
        B = br.readLine();
        S = br.readLine();
    }
}