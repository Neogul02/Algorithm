package SWEA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 1952. 수영장
 * @author neogul02
 * 
 * 수영장 이용권: 1일권, 1달권, 3달권, 1년권
 * 12개월 이용 계획이 주어질 때, 최소 비용 구하기
 * 
 * [IDEA]
 * 1. DFS + 백트래킹 방식
 *    - 각 달마다 3가지 선택:
 *      1. 1일권으로 계산 (이용일수 × 1일권 가격)
 *      2. 1달권 사용
 *      3. 3달권 사용 (현재 달부터 3개월)
 *    - 1년권은 처음에 최솟값으로 설정해두고 갱신하기
 * 
 * 2.dfs(month, cost): month월부터 12월까지의 최소 비용 계산
 * 
 * 1년권은 처음에 비교만 하면 될듯
 * 
 * - 1일권 vs 1달권: min(일수 × 1일권, 1달권)
 * - 3달권: 3개월치를 한 번에 처리
 * - 1년권: 전체 비용과 비교
 * - 이용 안 하는 달(0일): 바로 다음 달로 넘어감
 */
public class SWEA_1952_수영장 {
    
    static int[] prices; // 1일권, 1달권, 3달권, 1년권
    static int[] plan; // 각 달의 이용 계획 (1~12월)
    
    static int minCost; 
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= T; tc++) {
            // 이용권 가격 입력
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
            
            // 1년권과 비교하기 위해 충분히 큰 값으로 초기화
            minCost = prices[3];  // 1년권 가격으로 시작
            
            // DFS 시작 (1월부터, 비용 0)
            dfs(1, 0);
            
            sb.append("#").append(tc).append(" ").append(minCost).append("\n");
        }
        
        System.out.print(sb);
    }
    
    /**
     * DFS로 최소 비용 계산
     * 
     * 1일권 5장(50원) vs 1달권(40원) → 40원 선택, dfs(2, 40)
     * 3달권(100원) 사용, dfs(4, 100)
     * 
     * @param month 현재 월 (1~12)
     * @param cost 현재까지 누적 비용
     */
    static void dfs(int month, int cost) {
        // 기저조건 month가 12월까지 다 돌았을때 종료
    	if(month==12) {
    		minCost = Math.min(cost, minCost);
    		return; 
    	}
    	
    	// 현재 가격이 최소가격을 이미 넘겨버렸다면? 가지치기
    	if(cost>=minCost) return; 
    	
    	// 수영장 이용을 안하는 달 -> 0 일대
    	if(plan[month] == 0) {
    		dfs(month+1, cost);
    		return;
    	}
        
        // 1일권을 사용했을때랑 1달권 중 저렴한 것
        int betterCost = Math.min(plan[month] * prices[0], prices[1]); 
        
        dfs(month + 1, cost + betterCost);
        
        // 3달권 사용 (3개월 건너뛰기)
        dfs(month + 3, cost + prices[2]);
    }
}