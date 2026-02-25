import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 1759. 암호 만들기
 * @author neogul02
 * 
 * 암호는 서로 다른 L개의 알파벳 소문자로 구성 (a, e, i, o, u 중에서 최소 1개 포함, 자음은 최소 2개 포함)
 * 암호는 모음1+자음2 > 최소 3글자임
 * 
 * 1. 주어진 C 개의 문자중에서 L 만큼의 depth의 조합을 만들어야함 -> 조합이니까 dfs로 구현
 * 2. 조합 전에 C 문자중에서 모음과 자음이 몇개인지 체크해서 조합을 만들 때 개수를 체크하면서 조합을 만들어야할듯
 * 3. 조합이 완성되면 오름차순으로 정렬해서 출력해야하니까 ArrayList에 add 해두고 sort해서 출력?
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int L; // 만들 수 있는 암호의 길이
    static int C; // 암호로 사용할 수 있는 문자 종류의 수

    static ArrayList<Character> charList = new ArrayList<>();; // 암호로 사용할 수 있는 문자 리스트
    static ArrayList<String> answerList = new ArrayList<>();; // 조건에 맞는 암호를 저장할 리스트

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, new char[L], 0, 0, 0); // depth, 조합을 저장할 배열, 모음 개수, 자음 개수

        for (String ans : answerList) {
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }

    /**
     * 모든 조합을 구해서 모음 개수와 자음 개수를 체크하면서 조건에 맞는 조합을 answerList에 추가
     * startIdx를 이용해서 조합 생성, 중복 조합 방지
     */
    private static void dfs(int depth, char[] combination, int moCnt, int jaCnt, int startIdx) {
        // 기저 조건: depth가 L의 길이와 같아지면 완성된 조합
        if (depth == L) { // 조합이 완성된 경우
            if (moCnt >= 1 && jaCnt >= 2) { // 모음과 자음 개수 체크
                answerList.add(new String(combination)); // 조건에 맞는 조합을 리스트에 추가
            }
            return;
        }

        for (int i = startIdx; i < C; i++) {
            char currentChar = charList.get(i);
            combination[depth] = currentChar; // 현재 문자를 조합 배열에 추가

            if (isMo(currentChar) == true) { // 모음인 경우
                dfs(depth + 1, combination, moCnt + 1, jaCnt, i + 1); // 다음 depth로 이동, 모음 개수 증가, 자음 개수는 그대로, startIdx는 i + 1로 해서 중복 조합 방지
            } else { // 자음인 경우
                dfs(depth + 1, combination, moCnt, jaCnt + 1, i + 1); // 다음 depth로 이동, 모음 개수는 그대로, 자음 개수 증가, startIdx는 i + 1로 해서 중복 조합 방지
            }
        }
    }
    
    private static boolean isMo(char c) {
        char[] moArr = {'a', 'e', 'i', 'o', 'u'};
        for (char mo : moArr) {
            if (c == mo) return true;
        }
        return false; // 모음이 아니면
    }
    
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim(), " ");
        for (int i = 0; i < C; i++) {
            charList.add(st.nextToken().charAt(0));
        }
        charList.sort(null); // 오름차순 정렬
    }
}
