import java.util.Stack;

public class StackCalculatorTest {
	public static void main(String[] args) {
		String expression = "6528-*2/+";
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0, size=expression.length(); i<size; i++) {
			char temp = expression.charAt(i);
			int k = temp -'0';
			if(Character.isDigit(temp)) {
				stack.push(k);
			}else {
				int value2 = stack.pop();
				int value1 = stack.pop();
				int result = 0 ;
				switch (temp) {
				case '+':
					result = value1+value2;
					break;

				case '-':
					result = value1-value2;
					break;

				case '*':
					result = value1*value2;
					break;

				case '/':
					result = value1/value2;
					break;
				}
				stack.push(result);
			}
		}
		System.out.println(stack.pop());
	
	}
}
