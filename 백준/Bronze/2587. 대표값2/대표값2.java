import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		int[] arr = new int[5];
		for(int i =0; i<5; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		System.out.println(((int)Arrays.stream(arr).average().getAsDouble()));
		System.out.print(arr[2]);
	}
}
