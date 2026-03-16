import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[]args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        int[] h = new int[N];

        st = new StringTokenizer(br.readLine().trim());
        for(int i=0; i<N; i++){
            h[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        int start = h[0];

        for (int i = 1; i < N; i++) {
            if (h[i] > h[i - 1]) {
                // 오르막길
                max = Math.max(max, h[i] - start);
            } else {
                // 내리막, 평지
                start = h[i];
            }
        }
        sb.append(max);

        System.out.print(sb);
    }
}