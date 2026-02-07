import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11659. 구간합5
 * @author neogul02
 * 
 * (2,2) ~ (3,4) 의 2차원 배열 합은 누적합 배열을 만들고
 * 누적합 배열의 (3,4) - (1,4) - (2,1) + (1,1) 인데 이거를 맵의 끝
 * 
 * 1. 사용자에게 수의 개수 N과 M을 입력받는다.
 * 2. 숫자 N개를 입력받는다. ->N*N 크기의 표에 채움
 * 3. N 길이보다 1칸 더 크게 누적합을 저장해 둘 배열 sumArr를 저장해두고 누적합을 계산해서 저장한다.
 *  3-1. 2차원 배열 누적합 공식 = 왼쪽과 위 값을 더하고 대각선 값을 뺀거에 현재 자리를 더하면 직사각형의 누적합
 * 4. M 만큼의 라인을 입력받고 각 x, y 좌표를 입력받는다.
 *  4-1. x1,y1 부터 x2,y2 도 누적합과 같이 대각선을 빼주고 겹치는 부분을 더해주면 된다.
 */
public class Main {
    static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 1. 사용자에게 수의 개수 N과 M을 입력받는다.
		st = new StringTokenizer(br.readLine().trim(), " ");
		int N = Integer.parseInt(st.nextToken()); // 표의 크기 N
		int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수 M

		// 3. N 길이보다 1칸 더 크게 누적합을 저장해 둘 배열 sumArr를 저장해두고 누적합을 계산해서 저장한다.
		int [][] sumMap = new int[N+1][N+1];
		
		for(int r=1; r<=N; r++) {
			st = new StringTokenizer(br.readLine().trim()," ");
			for(int c=1; c<=N; c++) {
				int temp = Integer.parseInt(st.nextToken());
				// 3-1. 2차원 배열 누적합 공식 = 왼쪽과 위 값을 더하고 대각선 값을 뺀거에 현재 자리를 더하면 직사각형의 누적합
				sumMap[r][c] = sumMap[r-1][c] + sumMap[r][c-1] - sumMap[r-1][c-1] + temp;
			}
		}
		
		for(int i=0; i<M; i++) {
			// 4. M 만큼의 라인을 입력받고 각 x, y 좌표를 입력받는다.
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            // 4-1. x1,y1 부터 x2,y2 도 누적합과 같이 대각선을 빼주고 겹치는 부분을 더해주면 된다.
            int result = sumMap[x2][y2] - sumMap[x1-1][y2] - sumMap[x2][y1-1] + sumMap[x1-1][y1-1];
            sb.append(result).append("\n");
        }

		System.out.println(sb);
	}
}