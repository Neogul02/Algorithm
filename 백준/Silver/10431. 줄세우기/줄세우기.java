import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int caseNumber;
    static int[] children;

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine().trim());

        for(int tc=1; tc<=TC; tc++){
            input();
            solve();
        }
        System.out.print(sb);
    }

    public static void solve(){
        int backCnt = 0;
        ArrayList<Integer> line = new ArrayList<>();

        for (int h : children) {
            int pos = line.size();
            for (int i = 0; i < line.size(); i++) {
                if (line.get(i) > h) {
                    pos = i;
                    break;
                }
            }

            backCnt += line.size() - pos;
            line.add(pos, h);
        }

        sb.append(caseNumber).append(" ").append(backCnt).append("\n");
    }


    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine().trim());
        caseNumber = Integer.parseInt(st.nextToken());

        children = new int[20];
        for(int i=0; i<20; i++){
            children[i] = Integer.parseInt(st.nextToken());
        }
 
    }
}