import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1919. 애너그램 만들기
 * @author neogul02
 * 
 * 1. 소문자가 사용된 빈도수를 저장하는 int[26] 배열을 두 단어별로 만들어서 비교하면 될듯?
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] checkArr = new int[26];

    public static void main(String[] args) throws IOException {
        String A = br.readLine().trim();
        String B = br.readLine().trim();

        int[] cntA = new int[26];
        int[] cntB = new int[26];
        
        // A 문자열 개수 세기
        for (int i = 0; i < A.length(); i++) {
            cntA[A.charAt(i) - 'a']++;
        }
        
        // B 문자열 개수 세기
        for (int i = 0; i < B.length(); i++) {
            cntB[B.charAt(i) - 'a']++;
        }

        // 차이의 절댓값 합계
        int answer = 0;
        for (int i = 0; i < 26; i++) {
            answer += Math.abs(cntA[i] - cntB[i]);
        }
        System.out.print(answer);
    }
}