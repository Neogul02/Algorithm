import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 용태 - 한 손에 시작할 손가락 개수 A
 * 유진 - 한 손에 시작할 손가락 개수 B
 * 한 손만 써서 진행하고 
 * 용태가 이기면 yt, 유진이 이기면 yj를 출력
 */
public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken()); // 용태 시작 손가락
        int B = Integer.parseInt(st.nextToken()); // 유진 시작 손가락

        while (true) { 
            // 용태가 먼저 공격함
            B = B + A;
            if (B >= 5) {
                System.out.print("yt");
                break;
            }
            // 유진이 공격함
            A = A + B;
            if (A >= 5) {
                System.out.print("yj");
                break;
            }
        }
    }
}