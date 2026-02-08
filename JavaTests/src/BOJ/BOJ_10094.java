package BOJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 10093.숫자
 * @author neogul02
 * 두 양의 정수가 주어졌을 때, 두 수 사이에 있는 정수를 모두 출력하는 프로그램을 작성하시오.
 */
public class BOJ_10094 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        
        if (A > B) {
            long temp = A;
            A = B;
            B = temp;
        }
        
        if (A == B) {
            System.out.println(0);
            return;
        }
        sb.append(B - A - 1).append("\n");
        
        for(long i = A + 1; i < B; i++) {
            sb.append(i).append(" ");
        }
        
        System.out.println(sb);                    
    }
}