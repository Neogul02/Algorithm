import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[]args) throws IOException {

        int max = Integer.MIN_VALUE;
        int[] maxIndex = new int[2];
        int[][] map = new int[9][9];

        for(int i=0; i<9; i++){
            st =new StringTokenizer(br.readLine().trim());
            for(int j=0; j<9; j++){
                map[i][j] = Integer.parseInt(st.nextToken());                
                
                if(max < map[i][j]){
                    max = map[i][j];
                    maxIndex[0] = i+1;
                    maxIndex[1] = j+1;
                }
            }
        }

        sb.append(max).append('\n').append(maxIndex[0]).append(' ').append(maxIndex[1]);
        System.out.print(sb);
    }
}