
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11328. Strfry
 * @author neogul02
 * 
 * 각 알파벳이 나온 빈도수를 체크해서 최종적으로 맞지 않으면 Impossible
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static String[][] arr;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            String a = arr[i][0];
            String b = arr[i][1];

            if (isPossible(a, b)) {
                sb.append("Possible\n");
            } else {
                sb.append("Impossible\n");
            }
        }
        System.out.print(sb);
    }

    private static boolean isPossible(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        int[] count = new int[26];
        for (int i = 0; i < a.length(); i++) {
            count[a.charAt(i) - 'a']++;
            count[b.charAt(i) - 'a']--;
            // System.out.println(Arrays.toString(count));
        }

        for (int value : count) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        arr = new String[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            String A = st.nextToken();
            String B = st.nextToken();

            arr[i] = new String[] { A, B };
        }
    }
}
