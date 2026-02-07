import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		int N = readInt(), M = readInt();
        int [][] sumMap = new int[N+1][N+1];
		
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				int temp = readInt();
			    sumMap[r][c] = sumMap[r-1][c] + sumMap[r][c-1] - sumMap[r-1][c-1] + temp;
			}
		}
		
		for(int i=0; i<M; i++) {
            int x1 = readInt(), y1 = readInt();
            int x2 = readInt(), y2 = readInt();
            int result = sumMap[x2][y2] - sumMap[x1-1][y2] - sumMap[x2][y1-1] + sumMap[x1-1][y1-1];
            sb.append(result).append("\n");
        }
		System.out.println(sb);
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