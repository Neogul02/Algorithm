import java.io.*;
import java.util.StringTokenizer;

public class Main {
 public static void main(String[] args) throws IOException {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  StringTokenizer st = new StringTokenizer(br.readLine());
  
  int m = Integer.parseInt(st.nextToken());
  int n = Integer.parseInt(st.nextToken());
  
  if (m == 0) {
   System.out.println("0");
   return;
  }
  
  StringBuilder sb = new StringBuilder();
  
  while(m > 0) {
   int temp = m % n;
   if(temp < 10) sb.append(temp);
   else sb.append((char)('A' + temp - 10));
   m /= n;
  }
  
  System.out.println(sb.reverse());
 }
}
