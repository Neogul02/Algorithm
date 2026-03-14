import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        String S = br.readLine().trim();

        String target = "eagle";
        int answer = Integer.MAX_VALUE;

        for (int start = 0; start <= N - 5; start++) {
            int changes = 0;
            for (int i = 0; i < target.length(); i++) {
                if (S.charAt(start + i) != target.charAt(i)) {
                    changes++;
                }
            }
            answer = Math.min(answer, changes);
        }

        System.out.print(answer);
    }
}