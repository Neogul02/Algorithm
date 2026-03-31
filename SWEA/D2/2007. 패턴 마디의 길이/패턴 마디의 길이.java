import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/**
 * 2007. 패턴 마디의 길이
 * @author neogul02
 * 
 * 1. 테스트 케이스를 입력받는다.
 * 2. 마디의 길이는 1부터 10까지이다.
 * 
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        
        for(int tc=1; tc<=T; tc++){
            sb.append("#").append(tc).append(" ");
            solve();
        }

        System.out.print(sb);
    }
    public static void solve() throws IOException{
       String allString = br.readLine().trim();

       for(int i=1; i<=10; i++){
            String pattern = allString.substring(0, i);
            String target = allString.substring(i,i+pattern.length());

            if(pattern.equals(target)==true){
                sb.append(pattern.length()).append("\n");
                return;
            }
       }
    }

}