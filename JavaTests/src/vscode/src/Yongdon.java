
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 6236. 용돈 관리
 * @author neogul02
 * 
 * @see input
 * Line 1: N(N일) M(인출 횟수)
 * Line 2~N+1 : N번째 날에 사용할 돈
 */
public class Yongdon {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;


    public static void main(String[] args) throws IOException{

        input();
        
    }

    public static void input() throws IOException{
        // Line 1: N(N일) M(인출 횟수)
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // Line 2~N+1 : N번째 날에 사용할 돈
        for(int i=0; i<N; i++){
            int money = Integer.parseInt(br.readLine().trim());
        }
    }
}
