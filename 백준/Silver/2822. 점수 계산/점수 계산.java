import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[]args) throws IOException {
        // 8문제를 푼다
        ArrayList<int[]> arr = new ArrayList<>();
        for(int num=1; num<9; num++){
            int score = Integer.parseInt(br.readLine().trim());
            arr.add(new int[] {score, num});
        }

        arr.sort((A,B)->B[0]-A[0]);

        int sum = 0;
        ArrayList<Integer> idx = new ArrayList<>();
        for(int i=0; i<5; i++){
            sum += arr.get(i)[0];
            idx.add(arr.get(i)[1]);
        }

        sb.append(sum).append('\n');

        idx.sort((A,B)-> A-B);
        for(int i=0; i<5; i++){
            sb.append(idx.get(i)).append(" ");
        }
        System.out.print(sb);
    }
}