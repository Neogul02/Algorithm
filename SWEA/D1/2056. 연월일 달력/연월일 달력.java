import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine().trim());

		for(int tc=1; tc<=T; tc++){
			sb.append("#").append(tc).append(" ");
			String temp = br.readLine().trim();

			String year = temp.substring(0,4);
			String month = temp.substring(4,6);
			String day = temp.substring(6,8);

			int intMonth = Integer.parseInt(month);
			int intDay = Integer.parseInt(day);

			int[] month30 = new int[] {4,6,9,11};
			int[] month31 = new int[] {1,3,5,7,8,10,12};

			boolean isValid = true;

			if(intMonth<1 || intMonth>12) {
				sb.append(-1).append("\n");
				continue;
			}

			if(intMonth == 2){
				if(intDay>28 || intDay<1){
					sb.append(-1).append("\n");
					continue;
				}
			}

			for(int i=0; i<month30.length; i++){
				if(intMonth == month30[i]){
					if(intDay<0 || intDay>30){
						sb.append(-1).append("\n");
						isValid=false;
						break;
					}
				}
			}

			if(isValid==true){
				for(int i=0; i<month31.length; i++){
					if(intMonth == month31[i]){
						if(intDay<0 || intDay>31){
							sb.append(-1).append("\n");
							isValid=false;
							break;
						}
					}
				}
			}
			
			if(isValid==true){
				sb.append(year).append("/").append(month).append("/").append(day).append("\n");
			}

		}

		System.out.print(sb);
	}
}