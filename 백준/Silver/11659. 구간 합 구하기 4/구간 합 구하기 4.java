import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] sumArr; 

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();
        sumArr = new int[N + 1];
        
        for(int i = 1; i <= N; i++) {
            sumArr[i] = sumArr[i-1] + readInt();
        }
        
        for(int k = 0; k < M; k++) {
            int i = readInt();
            int j = readInt();
            sb.append(sumArr[j] - sumArr[i-1]).append('\n');
        }
        
        System.out.print(sb);
    }
    
    static int readInt() throws IOException {
        int n = 0;
        boolean isNegative = false;
        
        int c = br.read();
        while (c <= 32) {
            c = br.read();
        }
        
        if (c == '-') {
            isNegative = true;
            c = br.read();
        }
        
        while (c >= 48 && c <= 57) {
            n = (n * 10) + (c - 48);
            c = br.read();
        }
        return isNegative ? -n : n;
    }
}