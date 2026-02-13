import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1992. 쿼드트리
 * @author neogul02
 *
 * 1. N을 입력받는다.
 * 2. N*N 2차원 배열을 초기화 하고 입력받은 값을 저장해둔다.
 *
 * 3. 분할정복 재귀 시작
 * 사각형을 4등분한다. 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 순서로 탐색
 * 압축이 가능하다? = 모든 칸이 같은 숫자다 -> 압축하고 종료(return)
 * 압축이 불가능하다? = 모든 칸이 같은 숫자가 아니다.
 * -> 불가능하다면? -> 다시 4등분을 하고 압축이 가능한지 확인한다.
 * 
 */
public class Main {
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int[][] arr;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1. N을 입력받는다.
		N = Integer.parseInt(br.readLine().trim());
		
		// 2. N*N 2차원 배열을 초기화 하고 입력받은 값을 저장해둔다.
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			// 공백없이! 
			String temp = br.readLine().trim();
			for(int j=0; j<N; j++) {
				// 배열에 저장해두기 -'0' 을 하면 int형이 됨
				arr[i][j] = temp.charAt(j)-'0'; 
			}
		}

		quadTree(0,0,N);
		
		System.out.println(sb);
	}


	private static void quadTree(int row, int col, int size) {
		// 종료 조건, 현재 영역이 모두 같은 값이면 해당 숫자를 출력하고 종료
		if(isCompress(row,col,size)==true) {
			sb.append(arr[row][col]);
			return;
		}
		
		// 4등분용 사이즈 절반 줄이기
		int quadSize = size/2; 
		
		// 수행 로직 , 
		sb.append('('); // 괄호를 열고 시작
		quadTree(row, col, quadSize); // 왼쪽 위
		quadTree(row, col+quadSize, quadSize); // 오른쪽 위
		quadTree(row+quadSize, col, quadSize); // 왼쪽 아래
		quadTree(row+quadSize, col+quadSize, quadSize); // 오른쪽 위
		sb.append(')'); // 압축이 끝나면 괄호닫기	
	}
	
	private static boolean isCompress(int row, int col, int size) {
		
		int quadValue = arr[row][col];
		
		 for(int i = row; i < row + size; i++) {
	            for(int j = col; j < col + size; j++) {
	                if(arr[i][j] != quadValue) {
	                    return false;
	                }
	            }
	        }
		return true;
	}
}