
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 5397. 키로거
 * @author neogul02
 * 
 * 1. 입력
 *  1-1. T 테스트 케이스 수 입력
 *  1-2. T개 줄에 걸쳐서 각 테스트 케이스 입력
 * 
 * 2. 시뮬레이션
 *  2-1. 현재 커서 위치를 포인터로 표현해서 구현?
 *  2-2. '<' '>' '-' 3가지 경우를 고려하고 이 세가지가 아니면 pure Text 
 * 
 * #1 실패이유 -> switch문으로 구현해서 시간초과 
 * -> 해결 StringBuilder left, right로 커서기준 왼쪽 오른쪽을 스택으로 구현
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            String input = br.readLine().trim();
            sb.append(solve(input))
            .append('\n');
        }
        System.out.print(sb);
    }

    private static String solve(String input) {
        StringBuilder left = new StringBuilder(); // 커서 왼쪽에 있는 문자열
        StringBuilder right = new StringBuilder(); // 커서 오른쪽에 있는 문자열

        for (int i = 0; i < input.length(); i++) { // 키를 누른 개수만큼 반복
            char temp = input.charAt(i);

            if (temp == '<') {
                if (left.length() > 0) {
                    right.append(left.charAt(left.length() - 1));
                    left.setLength(left.length() - 1);
                }
            } else if (temp == '>') {
                if (right.length() > 0) {
                    left.append(right.charAt(right.length() - 1));
                    right.setLength(right.length() - 1);
                }
            } else if (temp == '-') {
                if (left.length() > 0) {
                    left.setLength(left.length() - 1);
                }
            } else {
                left.append(temp);
            }
        }

        left.append(right.reverse());
        return left.toString();
    }
}