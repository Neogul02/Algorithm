import java.util.Stack;

/**
 * 
 */

public class Solution {
	
	 public static boolean solution(String s) {	    
	        Stack<String> stack = new Stack<String>();
	        boolean answer = true;

	        for(int i=0; i<s.length(); i++) {
	        	char c = s.charAt(i);
	        	if(c == '('){
	        		stack.push(s);
	        	}else if(c==')') {
	        		if(stack.isEmpty()) {
	        			answer = false;
	        			break;
	        		}
	        		stack.pop();
	        	}
	        }
	        if(!stack.isEmpty()) {
    			answer = false;
    		}
	        
	        
	        return answer;
	    }

    public static void main(String args[]){
        System.out.println(solution("()()"));
    }
}