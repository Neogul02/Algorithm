import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, S;

    static int[] intArr;

    static int result;
    public static void main(String[]args) throws IOException {
        input();
        subset(0, 0, 0); 

        System.out.print(result);
    }

    public static void subset(int depth, int currentSum, int count){
        // 기저조건

        if(depth == N){
            if(currentSum == S && count>0) result++;
            return;
        }

       // 수행로직
       subset(depth+1, currentSum+intArr[depth], count+1); // 선택하는 경우 (덧셈해서 넘기기)

       subset(depth+1, currentSum, count); // 선택 안하는경우 (덧셈 안함)

    }

    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        intArr = new int[N];
        // isSelected = new boolean[N];

        st = new StringTokenizer(br.readLine().trim(), " ");
        for(int i=0; i<N; i++){
            intArr[i] = Integer.parseInt(st.nextToken());
        }

        // System.out.println(Arrays.toString(intArr));
    }
}