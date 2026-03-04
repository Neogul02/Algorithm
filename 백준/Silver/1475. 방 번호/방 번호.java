import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1475. 방 번호
 * @author neogul02
 * 
 * - 방 번호 N 번호를 입력받는다.
 * - 필요한 번호를 0부터 9까지 적어둔다.
 * - 6이랑 9를 합쳐서 2가 넘는다면 ..? 6은 9가 될 수 있고 9는 6이 될 수 있다.
 * - need배열의 최대값을 구하면 필요한 세트 수
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] need = new int[10]; // 0, 1, 2, 3... 9

    public static void main(String[] args) throws IOException{
        input();
        solve();
    }

    private static void solve() {
        // 6과 9를 합쳐서 처리 (뒤집으면 서로 되기 때문)
        int sixNineCnt = need[6] + need[9];
        
        need[6] = (sixNineCnt + 1) / 2;
        need[9] = 0; // 9는 6으로 통일
        int answer = 0;
        for (int i = 0; i <= 9; i++) {
            answer = Math.max(answer, need[i]);
        } 
        System.out.print(answer);
    }

    private static void input() throws IOException {
        char[] arr = br.readLine().trim().toCharArray();

        // 길이만큼 반복해서 필요한 숫자를 각 need 배열에 저장
        for (int i = 0; i < arr.length; i++) {
            need[arr[i] - '0']++;
        }
    }
}