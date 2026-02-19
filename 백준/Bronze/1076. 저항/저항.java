import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String first = br.readLine().trim();
        String second = br.readLine().trim();
        String third = br.readLine().trim();
        
        String[] colors = {
            "black","brown","red","orange","yellow",
            "green","blue","violet","grey","white"
        };
        
        int a = 0, b = 0;
        long mul = 1;
        
        for (int i = 0; i < 10; i++) {
            if (colors[i].equals(first)) a = i;
            if (colors[i].equals(second)) b = i;
            if (colors[i].equals(third)) mul = (long) Math.pow(10, i);
        }
        
        long result = ((a*10)+b) * mul;
        System.out.println(result);
    }
}