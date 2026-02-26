import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine().trim(), " ");
        int[] rightPieces = { 1, 1, 2, 2, 2, 8 };
        for (int i = 0; i < 6; i++) {
            sb.append(rightPieces[i] - Integer.parseInt(st.nextToken())).append(' ');   
        }
        System.out.print(sb);
    }
}