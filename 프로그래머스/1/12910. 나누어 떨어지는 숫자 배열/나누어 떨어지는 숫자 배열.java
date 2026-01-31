import java.util.Arrays;
import java.util.ArrayList;

public class Solution {
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
}