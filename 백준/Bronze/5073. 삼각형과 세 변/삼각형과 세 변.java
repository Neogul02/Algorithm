import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (true) { 
            st = new StringTokenizer(br.readLine().trim());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if(A == 0 && B == 0 && C == 0) break;

            // Invalid :  세 변의 길이가 삼각형을 이루지 못하는 경우
            if(A+B<=C || A+C<=B || B+C<=A) {
                sb.append("Invalid").append("\n");
                continue;
            }

            // Equilateral :  세 변의 길이가 모두 같은 경우
            if(A==B && B==C) sb.append("Equilateral").append("\n");
             
            // Isosceles :  세 변의 길이 중에서 두 변의 길이가 같은 경우
            else if(A==B || B==C || A==C) sb.append("Isosceles").append("\n");
            
            // Scalene :  세 변의 길이가 모두 다른 경우
            else sb.append("Scalene").append("\n");
            
        }
        
        System.out.print(sb);
    }
}