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
        String answer = br.readLine().trim();

        char[] adrianPattern = {'A', 'B', 'C'};  // A, B, C, A, B, C 상근, Adrian
        char[] brunoPattern = {'B', 'A', 'B', 'C'}; // B, A, B, C, B, A, B, C 창영, Bruno
        char[] goranPattern = {'C', 'C', 'A', 'A', 'B', 'B'}; // C, C, A, A, B, B 현진, Goran

        int[] scores = new int[3];

        for (int i = 0; i < N; i++) {
            char target = answer.charAt(i);
            
            if (target == adrianPattern[i % 3]) scores[0]++;
            if (target == brunoPattern[i % 4]) scores[1]++;
            if (target == goranPattern[i % 6]) scores[2]++;
        }

        int maxScore = Math.max(scores[0], Math.max(scores[1], scores[2]));

        sb.append(maxScore).append('\n');
        
        String[] names = {"Adrian", "Bruno", "Goran"};
        for (int i = 0; i < 3; i++) {
            if (scores[i] == maxScore) {
                sb.append(names[i]).append('\n');
            }
        }
        System.out.print(sb);
    }
}