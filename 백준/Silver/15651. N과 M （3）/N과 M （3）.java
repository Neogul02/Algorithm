import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] selected;
    
    public static void main(String[]args) throws IOException {
        input();
        permutation_repeat(0);

        System.out.print(sb);
    }

    public static void permutation_repeat(int depth){
        // 기저조건
        if(depth == M){
            for(int i=0; i<M; i++){
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        // 수행 로직
        for(int i=0; i<N; i++){
            selected[depth] = i+1;
            permutation_repeat(depth+1);
            
        }
    }

    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[N];

    }
}