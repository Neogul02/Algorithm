import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 13300. 수학여행
 * @author neogul02
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();


    static int N; // 학생 수
    static int K; // 한 방에 배정할 학생 수
    static int[][] students;
    
    static int roomCnt = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.print(sb);
    }
    
    private static void solve(){
        for (int i = 0; i < 6; i++) { // 1학년부터 6학년까지 수 세기
            int girlCnt = students[i][0];
            int manCnt = students[i][1];

            if (girlCnt != 0) { // 여학생이 0이 아니면
                roomCnt = roomCnt + (int) (girlCnt / K);
            }
            if (girlCnt % K > 0) {
                roomCnt = roomCnt + 1;
            }

            if (manCnt != 0) { // 나눈 나머지가 0이 아니면
                roomCnt = roomCnt + (int) (manCnt / K);
            }
            if (manCnt % K > 0) {
                roomCnt = roomCnt + 1;
            }
        }
        sb.append(roomCnt);
    }

    private static void input() throws IOException {
        // 입력 처리
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // [0][0] = 1학년 여학생 수, [0][1] = 1학년 남학생 수
        students = new int[6][2]; 
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");

            int sex = Integer.parseInt(st.nextToken());
            int grade = Integer.parseInt(st.nextToken());

            if (sex == 0) {
                students[grade - 1][0]++;
            } else {
                students[grade - 1][1]++;
            }
        }
        // System.out.println(Arrays.deepToString(students));
    }
}