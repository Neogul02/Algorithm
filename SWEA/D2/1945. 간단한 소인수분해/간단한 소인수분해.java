import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] prime = {2,3,5,7,11};
 
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine().trim());

        for(int tc=1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine().trim());
            int[] result = {0,0,0,0,0};

            for(int i=0; i<5; i++){
                int temp = prime[i];
                while(N%temp == 0){
                    result[i] += 1;
                    N /= temp;
                }
            }

            sb.append("#").append(tc).append(" ");
            for(int i=0; i<5; i++){
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            
        }
        System.out.print(sb);
    }
}