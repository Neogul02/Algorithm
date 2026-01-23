import java.util.*;

class Solution {
    public String[] solution(String myStr) {
        String[] answer;
        ArrayList<String> strList = new ArrayList<>();

        for(int i=0; i<myStr.length(); i++){
            if(myStr.charAt(i)=='a' || myStr.charAt(i)=='b' || myStr.charAt(i)=='c'){
                myStr = myStr.replace(myStr.charAt(i), ' ');

            }
        }
        String[] temp = myStr.split(" ");
        for(String s : temp){
            if(!s.equals("")){
                strList.add(s);
            }
        }
        if(strList.size()==0){
            answer = new String[1];
            answer[0] = "EMPTY";
        } else {
            answer = new String[strList.size()];
            for(int i=0; i<strList.size(); i++){
                answer[i] = strList.get(i);
            }
        }


        return answer;
    }
}