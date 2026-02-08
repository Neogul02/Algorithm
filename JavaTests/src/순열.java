public class 순열 {

	static final int ELEMENT_COUNT =3;
	static final int SELECT_COUNT =2;
	
	static int[] ElementList = new int[] {1,2,3};
	static int[] numbers = new int[SELECT_COUNT];
	
	static boolean[] isSelected = new boolean[ELEMENT_COUNT];
	
	public static void main(String[] args) {
		permutation(0);
	}


	private static void permutation(int depth) {
		// 종료 조건.
		if(depth==SELECT_COUNT) {
			for(int i=0; i<SELECT_COUNT; i++) {
				System.out.print(numbers[i]+" ");
			}
			System.out.println();
			return; 
		}
		
		// 실행부분
		for(int i=0; i<ELEMENT_COUNT; i++) {
			if(isSelected[i] == true) continue;
			
			isSelected[i] = true;
			numbers[depth] = ElementList[i];
			
			permutation(depth+1);
			
			isSelected[i] = false;
		}
	}
}
