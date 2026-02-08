
public class 비트마스킹연습 {
	
	public static void main(String[] args) {
		int personA = 0;
		int personB = 0;
		
		personA |= (1 << 1);
        personA |= (1 << 3);
        personA |= (1 << 5);
        personA |= (1 << 10);
        
        personB |= (1 << 3);
        personB |= (1 << 5);
        personB |= (1 << 7);
        personB |= (1 << 10);
        
        int mutualFriends = personA & personB;
			
        System.out.println("A의 친구 비트열: " + Integer.toBinaryString(personA));
        System.out.println("B의 친구 비트열: " + Integer.toBinaryString(personB));
        System.out.println("겹지인 비트열 : " + Integer.toBinaryString(mutualFriends));
        System.out.println("--------------------------------");

        // 3. 결과 해석 (겹지인 ID 출력)
        System.out.print("겹지인 ID 목록: ");
        printSetBits(mutualFriends);
        
        // 4. 겹지인 수 (Population Count)
        System.out.println("\n겹지인 수: " + Integer.bitCount(mutualFriends) + "명");
	}
	private static void printSetBits(int mask) {
        for (int i = 0; i < 32; i++) {
            // i번째 비트가 1인지 확인 (AND 연산 결과가 0이 아니면 1)
            if ((mask & (1 << i)) != 0) {
                System.out.print(i + " ");
            }
        }
    }
}
