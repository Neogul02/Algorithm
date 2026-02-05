import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

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
			inputTestCase();
		
			int isBracketsState = bracketsTest();

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

	        if(c == '(' || c == '[' || c == '{' || c == '<') stack.push(c);
	        else {
	            if(stack.isEmpty()) return 0;
	            
	            char top = stack.pop();
	            
	            if(c == ')' && top != '(') return 0;
	            if(c == ']' && top != '[') return 0;
	            if(c == '}' && top != '{') return 0;
	            if(c == '>' && top != '<') return 0;
	        }
	    }
	    if(!stack.isEmpty()) return 0;
	    return 1;
	}	
}