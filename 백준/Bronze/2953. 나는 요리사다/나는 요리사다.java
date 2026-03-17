import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[]args) throws IOException {
        int maxSum = -1;
        int maxIdx = -1;

        for(int i=0; i<5; i++){
            st = new StringTokenizer(br.readLine().trim());
            int sum = 0;
            for(int j=0; j<4; j++){
                sum += Integer.parseInt(st.nextToken());
            }

            if(maxSum<sum){
                maxIdx = i+1;
                maxSum = sum;
            }
        }
        sb.append(maxIdx).append(' ').append(maxSum);
        System.out.print(sb);
    }
}