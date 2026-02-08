package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;;

/**
 * 11659. 구간합5
 * @author neogul02
 * 
 * 2차원 누적합 배열을 만들고, 직사각형으로 묶는다는 느낌으로 합을 구해서 그려보자
 * 입력으로 2 2 3 4 가 들어오면 2,2부터 3,4까지 더한거 구해라~ 인데
 * -> 누적합 배열에서 (3,4) - (1,4) - (2,1) + (1,1) 
 * = 왼쪽칸 + 위쪽칸 - 2번겹친 왼쪽 대각선칸 한 번 빼주기 + 현위치값 하면 직사각형 범위로 합 구해짐! 
 * 
 * 1. 사용자에게 수의 개수 N과 M을 입력받는다.
 * 2. 숫자 N개를 입력받는다. ->N*N 크기의 표에 채움
 * 3. N 길이보다 1칸 더 크게 누적합을 저장해 둘 배열 sumArr를 저장해두고 누적합을 계산해서 저장한다.
 *  3-1. 2차원 배열 누적합 공식 = 왼쪽과 위 값을 더하고 대각선 값을 뺀거에 현재 자리를 더하면 직사각형의 누적합
 * 4. M 만큼의 라인을 입력받고 각 x, y 좌표를 입력받는다.
 *  4-1. x1,y1 부터 x2,y2 도 누적합과 같이 대각선을 빼주고 겹치는 부분을 더해주면 된다.
 */
public class S1_11660_구간합5_readInt {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		// 1. 사용자에게 수의 개수 N과 M을 입력받는다.
		int N = readInt(), M = readInt(); // 맵의 크기 N 합을 구해야 하는 횟수 M

		// 3. N 길이보다 1칸 더 크게 저장, 추후 -1 인덱스 오버를 방지할 수 있을듯 
		// 누적합을 저장해 둘 배열 sumArr를 저장해두고 누적합을 계산해서 저장한다.
		int [][] sumMap = new int[N+1][N+1];
		
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				int temp = readInt();
				// 3-1. 2차원 배열 누적합 공식 = 왼쪽과 위 값을 더하고 대각선 값을 뺀 값에, 현 위치 값을 더하면 직사각형의 누적합
				sumMap[r][c] = sumMap[r-1][c] + sumMap[r][c-1] - sumMap[r-1][c-1] + temp;
			}
		}
		
		for(int i=0; i<M; i++) {
			// 4. M 만큼의 라인을 입력받고 각 x, y 좌표를 입력받는다.
            int x1 = readInt(), y1 = readInt();
            int x2 = readInt(), y2 = readInt();
            // 4-1. x1,y1 부터 x2,y2 도 누적합과 같이 대각선을 빼주고 겹치는 부분을 더해주면 된다.
            int result = sumMap[x2][y2] - sumMap[x1-1][y2] - sumMap[x2][y1-1] + sumMap[x1-1][y1-1];
            sb.append(result).append("\n");
        }

		System.out.println(sb);
	}
	
	static int readInt() throws IOException {
        int n = 0;
        boolean isNegative = false;
        
        int c = br.read();
        while (c <= 32) {
            c = br.read();
        }
        
        if (c == '-') {
            isNegative = true;
            c = br.read();
        }
        
        while (c >= 48 && c <= 57) {
            n = (n * 10) + (c - 48);
            c = br.read();
        }
        return isNegative ? -n : n;
    }
}
