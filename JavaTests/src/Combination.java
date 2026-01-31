
/**
 * 
 * @author choe jin hyeong
 * 1.테스트 케이스 넘버를 입력받는다: T
 * 2.숫자 8개를 입력받아 배열에 저장한다 : numArr 
 *
 */
public class Combination {
	
	public static int combination(int n, int r) {
		if(n<r) throw new IllegalArgumentException();
		if(r==0 || n == r) return 1;
		
		
		return combination(n-1, r-1)+combination(n-1,r);
	}
	
	public static void main(String[] args) {
		System.out.println(combination(4, 1));
		System.out.println(combination(4, 2));
		System.out.println(combination(4, 3));
		System.out.println(combination(4, 4));
	}

}
