
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 집합_비트마스킹 { // 백준 제출용 클래스명은 Main
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // ArrayList 대신 int 변수 하나로 집합 표현!
    static int bitSet = 0; 
    
    static String method;
    static int x;
    
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine().trim());
        
        for(int m=0; m<M; m++) {
            st = new StringTokenizer(br.readLine()," ");
            method = st.nextToken();
            
            // all, empty는 x 입력이 없음
            if(st.hasMoreTokens()) {
                x = Integer.parseInt(st.nextToken());
            }
            order();
        }
        System.out.println(sb);
    }
    
    public static void order() {
        // 문제에서 x는 1~20. 비트 이동은 0부터 시작하므로 (x-1)을 사용
        switch (method) {
            case "add": {
                // x번째 비트를 켠다 (OR 연산)
                bitSet |= (1 << (x - 1));
                break;
            }
            case "remove": {
                // x번째 비트를 끈다 (NOT + AND 연산)
                bitSet &= ~(1 << (x - 1));
                break;
            }
            case "check": {
                // x번째 비트가 1인지 확인 (AND 연산)
                if ((bitSet & (1 << (x - 1))) != 0) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
                break;
            }
            case "toggle": {
                // x번째 비트를 뒤집는다 (XOR 연산)
                bitSet ^= (1 << (x - 1));
                break;
            }
            case "all": {
                // 1~20번째 비트를 모두 1로 만듦
                // (1 << 20) - 1 하면 하위 20개 비트가 111...1
                bitSet = (1 << 20) - 1;
                break;
            }
            case "empty": {
                // 모든 비트를 0으로 초기화
                bitSet = 0;
                break;
            }
        }
    }
}