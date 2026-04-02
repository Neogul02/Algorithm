import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] dollors = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
 
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine().trim());

        for(int tc=1; tc<=T; tc++){
            int N = Integer.parseInt(br.readLine().trim());

            int[] results = new int[8];

            for(int i=0; i<8; i++){
                results[i] = N/dollors[i];
                N %= dollors[i];
                
            }
            
            sb.append("#").append(tc).append("\n");
            for(int i=0; i<8; i++){
                sb.append(results[i]).append(" ");
            }
            sb.append("\n");

        }
        System.out.print(sb);
    }
}