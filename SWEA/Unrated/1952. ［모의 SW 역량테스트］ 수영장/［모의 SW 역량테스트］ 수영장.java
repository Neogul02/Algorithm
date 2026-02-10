import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1952. 수영장
 * @author neogul02
 * 
 * 1년동안 수영장을 가장 싸게 이용할 수 있는 가격을 출력하기
 * 이용권 종류 4개 - 1일, 1달, 3달, 1년
 * 
 * 모든 경우의 수를 계산해볼까? 1년권은 제외하고 + 
 * 1. 모두 케이스에 1일권만 쓰는 경우
 * 2. 모든 케이스에 1달권만 쓰는 경우
 * 3. 1일권과 1달권을 섞는 경우
 * 4. 1일권과 3달권을 섞는 경우
 * 5. 1달권과 3달권을 섞는 경우 
 * 6. 1일권 1달권 3달권 다 섞는경우
 * 7. 1년권으로 퉁치는 경우
 *  
 */
public class Solution {
	
	static int[] prices;
	static int[] plan;
	
	static int minCost; // 최소 비용
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
            prices = new int[4];
            for (int i = 0; i < 4; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }
            
            // 12개월 이용 계획 입력
            st = new StringTokenizer(br.readLine());
            plan = new int[13];  // 1~12월 (0번 인덱스 안 씀)
            for (int i = 1; i <= 12; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
            }
         
            minCost = prices[3];  // 1년권 가격으로 시작
            
//            System.out.println(Arrays.toString(prices));
//            System.out.println(Arrays.toString(plan));
            // DFS 시작 (1월부터, 비용 0)
            dfs(1, 0);
            
            sb.append("#").append(tc).append(" ").append(minCost).append("\n");
		}
		System.out.print(sb);
	}

	private static void dfs(int month, int cost) {
		// 기저조건
		if(month > 12) {
			minCost = Math.min(minCost, cost);
			return;
		}
		
		// 가지치기: 이미 최소값보다 크면 return
        if (cost >= minCost) {
            return;
        }
        
        if(plan[month] == 0) {
        	dfs(month+1,cost);
        	return;
        }
        
        int dailyCost = plan[month] * prices[0];  // 1일권으로 계산
        int monthlyCost = prices[1];               // 1달권
        int betterCost = Math.min(dailyCost, monthlyCost);
        
        dfs(month + 1, cost + betterCost);
        
        // 선택 2: 3달권 사용 (3개월 건너뛰기)
        dfs(month + 3, cost + prices[2]);
	}
}