class Solution {
    public int[] solution(int[] num_list) {
        int len = num_list.length;
        int lastNum = num_list[len-1];
        int num = num_list[len-2];
        
        
        int[] newArr = new int[len+1];
        int target = 0;
        if(lastNum>num){
            target = lastNum - num;
        }else{
            target = 2*lastNum;
        }
        
        for(int i=0; i<newArr.length; i++){
            if(i==newArr.length-1){
                newArr[i] = target;
                continue;
            }
            newArr[i] = num_list[i];
        }
        
        return newArr;
    }
}