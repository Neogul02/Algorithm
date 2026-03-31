import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/**
 * 1926. 간단한 369게임
 * @author neogul02 
 * 
 * 1. 1~N까지 숫자 N을 입력받는다.
 * 2. 반복문을 1부터 N까지 돌면서 3, 6, 9가 몇 번 나오는지 카운트한다.
 * 3. 3, 6, 9가 나온 횟수만큼 '-'를 출력한다.
 * 4. 3, 6, 9가 나오지 않은 경우에는 숫자를 그대로 출력한다.
 * 
 * + N은 최대 10~1000이므로 최대 --- 까지
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
 
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        
        for(int i=1; i<=N; i++){
            int cnt = 0;
            String numStr = Integer.toString(i);
            for(char c : numStr.toCharArray()){
                if(c == '3' || c == '6' || c == '9'){
                    cnt++;
                }
            }

            if(cnt>0){
                for(int c=0; c<cnt; c++){
                    sb.append("-");
                }
            }else{
                sb.append(i);
            }
            sb.append(" ");
        }
        System.out.print(sb);
    }
}