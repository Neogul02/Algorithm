import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] intArr;
    static int[] selected;
    
    static ArrayList<int[]> arr;
    public static void main(String[]args) throws IOException {
        input();
        combination(0, 0);

        System.out.print(sb);
    }

    public static void combination(int depth, int start){
        // 기저조건
        if(depth == M){
            for(int i=0; i<M; i++){
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        // 수행 로직
        for(int i=start; i<N; i++){
            selected[depth] = intArr[i];
            combination(depth+1, i+1);
            
        }
    }

    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        intArr = new int[N];
        selected = new int[N];
        arr = new ArrayList<>();

        st = new StringTokenizer(br.readLine().trim());
        for(int i=0; i<N; i++){
            intArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(intArr);
    }
}