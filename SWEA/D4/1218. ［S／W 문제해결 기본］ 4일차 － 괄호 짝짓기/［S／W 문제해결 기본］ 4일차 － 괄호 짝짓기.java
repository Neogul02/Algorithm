import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 1218. 괄호 짝짓기
 * @author neogul02
 * 
 * 괄호의 종류 '()', '[]', '{}', '<>'
 * 
 * 1. 10개의 테스트 케이스만큼 반복한다.
 * 2. 첫번째 줄에는 테스트 길이를 두번째 줄에는 문자열이 입력한다.
 * 3-a. [IDEA #1] charArr로 받고 모든 괄호의 수를 세서 비교하면 되지않을까?? 
 * -> 열고 닫는 순서가 중요하기때문에 x 안됨 
 * 3-b. [IDEA #2] 여는 괄호면 무조건 스택에 push, 닫는 괄호면 pop 해서 짝이 맞는지 확인하는 정석
 *  3-1. 여는 괄호면 스택에 담는다. ( { [ <
 *  3-2. 닫는 괄호가 나오면 pop 해서 여는괄호와 짝이 맞는지 검사를 한다.
 *  3-3. 닫는 괄호를 모두 찾았는데 스택이 isEmpty 하지 않다면 짝이 안맞는것
 * 4. # 부호와 번호, 유효성 여부를 1, 0으로 출력
 */
public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	
	static int N;
	static char[] bracketsArr;
	
	public static void inputTestCase() throws IOException{
		N = Integer.parseInt(br.readLine().trim());
		bracketsArr = br.readLine().trim().toCharArray();
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		for(int tc=1; tc<=10; tc++) {
			// 2. 첫번째 줄에는 테스트 길이를 두번째 줄에는 문자열이 입력한다.
			inputTestCase();
			
			// 3. [IDEA #2] 여는 괄호면 무조건 스택에 push, 
			// 닫는 괄호면 pop 해서 짝이 맞는지 확인하기
			int isBracketsState = bracketsTest();
			
			// 4. # 부호와 번호, 유효성 여부를 1, 0으로 출력
			sb.append("#").append(tc).append(" ")
			.append(isBracketsState)
			.append("\n");
		}
		
		System.out.print(sb);
	}

	public static int bracketsTest() {
	    Stack<Character> stack = new Stack<>();
	    
	    for(int i = 0; i < N; i++) {
	        char c = bracketsArr[i];
	        
	        // 3-1. 여는 괄호면 스택에 담는다. ( { [ <
	        if(c == '(' || c == '[' || c == '{' || c == '<') {
	            stack.push(c);
	        } 
	        // 3-2. 닫는 괄호가 나오면 pop 해서 여는 괄호와 짝이 맞는지 검사를 한다.
	        else {
	            if(stack.isEmpty()) return 0;
	            
	            char top = stack.pop();
	            
	            // 짝이 맞는지 확인
	            if(c == ')' && top != '(') return 0;
	            if(c == ']' && top != '[') return 0;
	            if(c == '}' && top != '{') return 0;
	            if(c == '>' && top != '<') return 0;
	        }
	    }
	    
	    // 3-3. 닫는 괄호를 모두 찾았는데 스택이 isEmpty 하지 않다면 짝이 안맞는것
	    if(!stack.isEmpty()) return 0;
	    
	    // 성공
	    return 1;
	}	
}