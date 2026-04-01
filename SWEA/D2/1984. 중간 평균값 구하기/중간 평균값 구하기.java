import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Solution{
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  public static void main(String[] args) throws IOException{
    int T = Integer.parseInt(br.readLine().trim());
    for(int tc=1; tc<=T; tc++){

      ArrayList<Integer> arr =new ArrayList<>();

      st = new StringTokenizer(br.readLine().trim());

      for(int i=0; i<10; i++) 
        arr.add(Integer.parseInt(st.nextToken()));
      
      arr.sort((A,B)->A-B);

      arr.remove(arr.size()-1);
      arr.remove(0);

      double sum = 0;
      for(int i=0; i<arr.size(); i++)
        sum += arr.get(i);
      

      sb.append("#").append(tc).append(" ")
      .append(Math.round(sum/arr.size())).append("\n");
    }
    System.out.print(sb);
  }
}