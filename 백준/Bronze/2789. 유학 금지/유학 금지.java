import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[]args) throws IOException {
        char[] cambridge = new char[] {'C', 'A', 'M', 'B', 'R', 'I', 'D', 'G', 'E'};

        char[] word = br.readLine().trim().toCharArray();
        boolean[] delete = new boolean[word.length];
        
        for(int i=0; i<word.length; i++){
            for(int j=0; j<cambridge.length; j++){
                if(word[i]==cambridge[j])  delete[i] = true;
            }
        }

        for(int i=0; i<word.length; i++){
            if(delete[i]==true) continue;

            sb.append(word[i]);
        }

        System.out.print(sb);
    }
}