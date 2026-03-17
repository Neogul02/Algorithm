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
    static boolean[] isSelected;

    static int result;
    public static void main(String[]args) throws IOException {
        input();
        subset(0); 

        System.out.print(result);
    }

    public static void subset(int depth){
        // 기저 조건
        if(depth == N){
            int tempSum = 0;
            int trueCnt = 0;
            for(int i=0; i<N; i++){
                if(isSelected[i] == true){
                    trueCnt ++;
                    tempSum += intArr[i];
                    
                }
            }
            if(trueCnt==0) return;
            
            if(tempSum == S){
                result ++;
            }

            return;
        }

        // 수행 로직
        isSelected[depth] = true;
        subset(depth+1);

        isSelected[depth] = false;
        subset(depth+1);
    }

    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        intArr = new int[N];
        isSelected = new boolean[N];

        st = new StringTokenizer(br.readLine().trim(), " ");
        for(int i=0; i<N; i++){
            intArr[i] = Integer.parseInt(st.nextToken());
        }

        // System.out.println(Arrays.toString(intArr));
    }
}