import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str = br.readLine();
        Stack<Character> leftSt = new Stack<>();
        Stack<Character> rightSt = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            leftSt.push(str.charAt(i));
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            String command = br.readLine();
            char c = command.charAt(0);

            switch (c) {
                case 'L': // 커서 왼쪽 이동: 왼쪽 스택 pop -> 오른쪽 스택 push
                    if (!leftSt.isEmpty()) rightSt.push(leftSt.pop());
                    break;
                case 'D': // 커서 오른쪽 이동: 오른쪽 스택 pop -> 왼쪽 스택 push
                    if (!rightSt.isEmpty()) leftSt.push(rightSt.pop());
                    break;
                case 'B': // 삭제: 커서 왼쪽 문자 삭제 (왼쪽 스택 pop)
                    if (!leftSt.isEmpty()) leftSt.pop();
                    break;
                case 'P': // 추가: 커서 왼쪽에 추가 (왼쪽 스택 push)
                    char t = command.charAt(2); // "P x" 형태이므로 인덱스 2
                    leftSt.push(t);
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : leftSt) { 
            sb.append(c);
        }
        
        while (!rightSt.isEmpty()) {
            sb.append(rightSt.pop());
        }
        System.out.println(sb);
    }
}