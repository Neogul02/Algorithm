import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
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