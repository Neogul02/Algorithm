import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
		    String line = br.readLine();
		    if(line.equals("#")) break;
		    long result = 0; 
		    for(int i = 0; i < line.length(); i++) {
		        int val = 0;
		        char c = line.charAt(i);
		        
		        switch(c) {
		            case '-': val = 0; break;
		            case '\\': val = 1; break;
		            case '(': val = 2; break;
		            case '@': val = 3; break;
		            case '?': val = 4; break;
		            case '>': val = 5; break;
		            case '&': val = 6; break;
		            case '%': val = 7; break;
		            case '/': val = -1; break;
		        }
		        result = result * 8 + val;
		    }
		    System.out.println(result);
		}
	}
}