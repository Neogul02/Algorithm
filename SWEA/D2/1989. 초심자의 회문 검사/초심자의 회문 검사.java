import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Solution{
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int T = Integer.parseInt(br.readLine().trim());
    for(int tc=1; tc<=T; tc++){
      String str = br.readLine().trim();
      char[] arr = str.toCharArray();

      int leftPointer = 0;
      int RightPointer = arr.length-1;

      boolean isPalindrome = true;
      while(true){
        if(leftPointer>=RightPointer){
          break;
        }

        if(arr[leftPointer] != arr[RightPointer]){
          isPalindrome = false;
          break;
        }
      
        leftPointer++;
        RightPointer--;
      }
      
      sb.append("#").append(tc).append(" ");

      if(isPalindrome==true) sb.append(1).append("\n");
      else sb.append(0).append("\n");

    }
    System.out.print(sb);
  }
}
