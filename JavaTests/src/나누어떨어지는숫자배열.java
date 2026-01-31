import java.util.ArrayList;
import java.util.Arrays;

/**
 * 1. 받은 배열 arr를 for문으로 하나씩 확인한다.
 * 	1-1. 요소 하나가 divisor 로 나눴을때 나머지가 0이면 나누어 떨어지는 숫자다
 *  1-2. answer 배열에 담는다
 * 2. answer 배열의 길이가 1보다 작으면 -1을 넣고 리턴한다.
 */
public class 나누어떨어지는숫자배열 {
	public static int[] solution(int[] arr, int divisor) {
        ArrayList<Integer> answerArray = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
        	if(arr[i]%divisor==0) {
        		answerArray.add(arr[i]);
        	}
        }
        if(answerArray.size()<1) {
        	answerArray.add(-1);
        }
        int[] result = new int[answerArray.size()];
        
        for(int i=0; i<answerArray.size(); i++) {
        	result[i] = answerArray.get(i);
        }
        
        return Arrays.stream(result).sorted().toArray();
    }

    public static void main(String args[]){
    	int[] test_arr = {5,9,7,10}; 
        System.out.println(Arrays.toString(solution(test_arr,5)));
    }
}