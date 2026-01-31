import java.util.Arrays;

public class Array_recursion {
	static int arr[] = {10,20,30};
	public static void main(String[] args) {
		printArray1(0);
		
	}
	public static void printArray1(int idx) {
		if(idx<arr.length) {
			System.out.println(arr[idx++]);
			printArray1(idx);
		}
		
	}
}
