import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/**
 * 1974. 스도쿠 검증
 * @author neogul02 
 * 
 * 1. tc를 입력받는다
 * 2. 9x9 스도쿠 판을 초기화하고 입력받는다.
 * 3. 가로줄이 1~9까지 모두 있는지 검증한다.
 * 4. 세로줄이 1~9까지 모두 있는지 검증한다.
 * 5. 3x3 정사각형이 1~9까지 모두 있는지 검증한다.
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[][] map;
 
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        
        for(int tc=1; tc<=T; tc++){
            sb.append("#").append(tc).append(" ");
            input();
            solve(tc);
           
        }
        System.out.print(sb);
        
    }
    public static void solve(int tc){
        // 가로줄
        for(int i=0; i<9; i++){
            int[] check = new int[10];
            for(int j=0; j<9; j++){
                check[map[i][j]]++;
            }
            for(int k=1; k<=9; k++){
                if(check[k] != 1){
                    sb.append("0\n");
                    return;
                }
            }
        }
        // 세로줄
        for(int i=0; i<9; i++){
            int[] check = new int[10];
            for(int j=0; j<9; j++){
                check[map[j][i]]++;
            }
            for(int k=1; k<=9; k++){
                if(check[k] != 1){
                    sb.append("0\n");
                    return;
                }
            }
        }
        
        // 3x3 정사각형 9개
        for(int i=0; i<9 ; i+=3){
            for(int j=0; j<9; j+=3){

                int[] check = new int[10];
                for(int k=0; k<3; k++){
                    for(int l=0; l<3; l++){
                        check[map[i+k][j+l]]++;
                    }
                }

                for(int m=1; m<=9; m++){
                    if(check[m] != 1){
                        sb.append("0\n");
                        return;
                    }
                }
            }
        }

        // 검증 완료
        sb.append("1\n");

    }

    public static void input() throws IOException{
        map = new int[9][9];

        for(int i=0; i<9; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j=0; j<9; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
    }
}