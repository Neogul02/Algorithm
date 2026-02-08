package codeTests;

public class tri_star {
	public static void main(String[] args) {
		printStar(1);
		
	}
	public static void printStar(int cnt) {
		if(cnt<10) {
			for(int i=0; i<cnt; i++) {
				System.out.print("* ");
			}
			System.out.println();
			printStar(++cnt);
		}
	}

}
