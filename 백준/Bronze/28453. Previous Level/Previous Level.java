import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim(), " ");

        for(int i = 0; i < N; i++){
            int tempLevel = Integer.parseInt(st.nextToken());
            if (tempLevel == 300) sb.append(1).append(" ");
            else if (tempLevel >= 275) sb.append(2).append(" ");
            else if (tempLevel >= 250) sb.append(3).append(" ");
            else sb.append(4).append(" ");
        }
        System.out.print(sb);
    }
}