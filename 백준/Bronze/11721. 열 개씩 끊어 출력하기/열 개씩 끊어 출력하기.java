import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		
		int cnt = 0;
		for (int i=0; i<str.length(); i++) {
			System.out.print(str.charAt(i));
			cnt ++;
			if(cnt%10==0) {
				System.out.println();
			}
		}
		sc.close();
	}
}
