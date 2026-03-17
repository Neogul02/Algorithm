import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        String[] arr = new String[5];
        for (int i=0; i<5; i++){
            arr[i] = br.readLine().trim();
        }

        boolean isFBI = false;
        for(int i=0; i<5; i++){
            String temp = arr[i];

            if(temp.contains("FBI")){
                isFBI =true;
                sb.append(i+1).append(" ");
                continue;  
            }
        }
        if(isFBI==true) System.out.print(sb);
        else System.out.print("HE GOT AWAY!");  
    }
}
