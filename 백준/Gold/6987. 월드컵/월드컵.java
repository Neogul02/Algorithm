import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 6987. 월드컵
 * @author neogul02
 *
 * IDEA
 * 승/무/패 개수를 하나씩 소진해가면서, 15경기 끝났을 때 딱 0이 되면 성공.. dfs 백트래킹
 * 
 * 1. 4번의 테스트케이스로 반복문 돌리기
 * 2. 6개 팀의 모든 경기 조합 생성: C(6,2) = 15경기
 * 3. [6][3] int 배열로 저장 입력값 저장
 * 4. DFS + 백트래킹으로 15경기를 하나씩 시뮬레이션 해보면?
 * 	4-0. 15경기를 무사히 진행했다? == 모든 카운터가 0이다 == 남는 카운터가 없다
 * 
 * 	4-1. 왼쪽팀이 이기는경우
 *  4-2. 무승부
 *  4-3. 오른쪽팀이 이기는경우
 * 
 * = 3^15 = 14,348,907
 */
public class Main {

    static int[] team1 = new int[15];  // 각 경기의 첫 번째 팀
    static int[] team2 = new int[15];  // 각 경기의 두 번째 팀
    
	static boolean possible;
	static int[][] result;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc=0; tc<4; tc++) {
			result = new int[6][3];
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			for(int j=0; j<6; j++) {
				for(int k=0; k<3; k++) {
					result[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 6개 팀의 모든 경기 조합 생성: C(6,2) = 15경기
	        int idx = 0;
	        for (int i = 0; i < 6; i++) {
	            for (int j = i + 1; j < 6; j++) {
	                team1[idx] = i;
	                team2[idx] = j;
	                idx++;
	            }
	        }
	        
	        // 백트래킹 전, 기본 검증 
	        if (!isValid()) {
                sb.append("0 ");
                continue;
            }
	        
	        // 백트래킹 시작
	        possible = false;
            dfs(0);
            
            sb.append(possible ? 1 : 0).append(" ");
		}
		System.out.print(sb.toString().trim());
	}

	// 1. 각 팀은 정확히 5경기를 해야함
	// 2. 전체 승수 == 전체 패수
	// 3. 전체 무승부의 수는 짝수여야하고 2팀이 함께해야함
	static boolean isValid() {
        int totalWin = 0;
        int totalDraw = 0;
        int totalLose = 0;
        
        for (int i = 0; i < 6; i++) {
            int games = result[i][0] + result[i][1] + result[i][2];
            
            // 각 팀은 정확히 5경기
            if (games != 5) {
                return false;
            }
            
            totalWin += result[i][0];
            totalDraw += result[i][1];
            totalLose += result[i][2];
        }
        
        // 승수 = 패수
        if (totalWin != totalLose) {
            return false;
        }
        
        // 무승부는 짝수
        if (totalDraw % 2 != 0) {
            return false;
        }
        
        return true;
    }
	
	public static void dfs(int gameNum) {
		// 기저 조건
		if (possible) return;  // 이미 찾았으면 종료
        
        // 15경기 모두 완료
        if (gameNum == 15) {
            possible = true;
            return;
        }
        
        // 수행 로직
        int t1 = team1[gameNum];
        int t2 = team2[gameNum];
        
        // 경우 1: t1 승, t2 패
        if (result[t1][0] > 0 && result[t2][2] > 0) {
            result[t1][0]--;
            result[t2][2]--;
            dfs(gameNum + 1);
            result[t1][0]++;
            result[t2][2]++;
        }
        
        // 경우 2: 무승부
        if (result[t1][1] > 0 && result[t2][1] > 0) {
            result[t1][1]--;
            result[t2][1]--;
            dfs(gameNum + 1);
            result[t1][1]++;
            result[t2][1]++;
        }
        
        // 경우 3: t1 패, t2 승
        if (result[t1][2] > 0 && result[t2][0] > 0) {
            result[t1][2]--;
            result[t2][0]--;
            dfs(gameNum + 1);
            result[t1][2]++;
            result[t2][0]++;
        }
	}
}