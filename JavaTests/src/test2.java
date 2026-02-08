import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test2 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static int n;
    
	static int[][] grid;
	
	static int coin = Integer.MIN_VALUE;
    
    public static void main(String[] args) throws IOException{
    	br = new BufferedReader(new InputStreamReader(System.in));
    	
    	n = Integer.parseInt(br.readLine());
    	grid = new int[n][n];
    	
    	for(int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine().trim());
    		for (int j = 0; j < n; j++) {
    			  grid[i][j] = Integer.parseInt(st.nextToken());
            }	
    	}
        searchMaxSum();
        System.out.println(coin);
    }

	public static void searchMaxSum() {
		for(int i=0; i<=n-3; i++) {
			for(int j=0; j<=n-3; j++) {
				int currentSum = 0;
				for(int r = 0; r<3; r++) {
					for(int c =0; c<3; c++) {
						currentSum += grid[i+r][j+c];
					}
				}
				if(currentSum >coin) coin = currentSum;
			}
		}
	}
}