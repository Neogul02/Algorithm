import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Solution{
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int T = Integer.parseInt(br.readLine().trim());
    String[] grade = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};

    for(int tc=1; tc<=T; tc++){
      st = new StringTokenizer(br.readLine().trim());
      int N = Integer.parseInt(st.nextToken()); // 학생 수
      int K = Integer.parseInt(st.nextToken()); // 알고싶은 학생의 번호

      double[][] scoreArr = new double[N][2];
      // N 번 반복하면서 학생 점수 입력받아서 넣기
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine().trim());

        int middle = Integer.parseInt(st.nextToken());
        int last = Integer.parseInt(st.nextToken());
        int homework = Integer.parseInt(st.nextToken());

        scoreArr[i][0] = i + 1;
        scoreArr[i][1] = (middle*35)/100 + (last*45)/100 + (homework*20)/100;
      }

      Arrays.sort(scoreArr, (a, b) -> Double.compare(b[1], a[1]));
      
      String result;
      for(int i=0; i<N; i++){
        if ((int)scoreArr[i][0] == K){
          result = grade[i/(N/10)];
          sb.append("#").append(tc).append(" ").append(result).append("\n");
          break;
        }
      }
    }

    System.out.print(sb);
  }
}
