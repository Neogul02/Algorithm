import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int sum = 0;
        for(int i=0; i<4; i++){
            sum += Integer.parseInt(br.readLine().trim());
        }
        if(sum+300<=1800) System.out.println("Yes");
        else System.out.println("No");
    }
}