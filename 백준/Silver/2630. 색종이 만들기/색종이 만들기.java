import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2630. 색종이 만들기
 * @author neogul02
 * 
 * 분할정복?
 * 
 * N * N 크기 종이에 파란색 1 이랑 흰색 0 으로 칠해져있음 
 * 전체 종이 = 한 칸이 같은색으로 칠해져 있지 앉으면 4등분을 해서 자른다.
 * 
 * 1. N을 입력받는다
 * 2. N * N 크기의 종이의 색깔을 배열로 저장해둔다.
 * 3. 재귀적으로 검사하는데
 *  3-1. 현재 종이의 모든 칸이 같은 색인지 검사한다. -> @check
 *  3-2. 모든 칸이 같은 색이면 -> 해당 색의 색종이 개수 + 1
 *  3-3. 모든 칸이 같은 색이 아니면 -> 색종이를 4등분한다고 가정하고 x, y 값을 절반으로 나누어서 재귀적으로 검사한다. -> @divide
 * 4. 3번에서 모든 재귀가 끝나면 흰색 색종이 개수와 파란색 색종이 개수를 출력한다.
 * 
 */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N; 

    static int[][] map;
    
    static int whiteCnt = 0;
    static int blueCnt = 0;


    public static void main(String[] args) throws IOException {

        input();
        divide(0, 0, N);

        sb.append(whiteCnt).append("\n").append(blueCnt);
        System.out.print(sb);
    }
    
    private static boolean check(int x, int y, int size) {
        int color = map[x][y]; // 1 이면 블루, 0 이면 화이트

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[i][j] != color) {
                    return false; // 모든 칸이 같은 색이 아니면 false
                }
            }
        }
        return true; // 모든 칸이 같으면 true
    }

    
    private static void divide(int x, int y, int size) {
        if (check(x, y, size)) {
            if (map[x][y] == 1) { // 같은 칸이 파란색일때
                blueCnt++;
            } else { // 흰색일때
                whiteCnt++;
            }
            return;
        }

        // 모든 칸이 같지 않은 경우 4등분 하기

        int newSize = size / 2;
        divide(x, y, newSize); // 왼쪽 위
        divide(x, y + newSize, newSize); // 오른쪽 위
        divide(x + newSize, y, newSize); // 왼쪽 아래
        divide(x + newSize, y + newSize, newSize); // 오른쪽 아래
    }

	private static void input() throws IOException {
       
        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];
        
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
