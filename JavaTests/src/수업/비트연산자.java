package 수업;


/**
 * 
 * @author SSAFY
 * 비트 단위로 AND 연산을 한다. num1 & num2 
 *
 */
public class 비트연산자 {

	public static void subsetBitmask() {
	    char[] nums = {'A', 'B', 'C', 'D', 'E'};
	    int n = nums.length;
	    
	    for(int i=0; i<(1<<n); i++) { // 0~2^5승까지 0~31까지
	    	System.out.print(i+"번 조합:");
	    	for(int j=0; j<n; j++) {
	    		if((i&(1<<j)) !=0) {
	    			System.out.print(nums[j]+" ");
	    		}
	    	}
	    	System.out.println();
	    }

	}
	public static void main(String[] args) {
		subsetBitmask();
	}
}
