import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 14510. 나무높이
 * @author neogul02
 *
 * *** 하루에 한 나무에만 물을 줄 수 있음 *** 
 * 
 * 홀수날에 물을 주면 1만큼 자라고
 * 짝수날에 물을 주면 2만큼 자람
 * 
 * 결국엔 날을 쉬지않고 계속해서 채우는게 이득임
 * 홀수 - 짝수 - 홀수 - 짝수를 연속해서 가는게 좋다고 생각함.
 * 
 * 
 * 짝수 하나를 홀수 2개로 바꿀 수 있다?
 * 
 * 
 * 
 * 8 = 2+2+2+2 -> evenCnt 4개
 * 7 = 2+2+2+1 -> evenCnt 3개, oddCnt 1개
 * ...
 */
public class Solution {
    
    static int[] treeArr;
    static int maxTreeLen;
    static int oddCnt;
    static int evenCnt;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim()); 
            treeArr = new int[N];
            maxTreeLen = 0;

            st = new StringTokenizer(br.readLine().trim(), " ");
            for (int i = 0; i < N; i++) {
                treeArr[i] = Integer.parseInt(st.nextToken());
                maxTreeLen = Math.max(treeArr[i], maxTreeLen);
            }
            
            oddCnt = 0;
            evenCnt = 0;
            
            for (int i = 0; i < N; i++) {
                int diff = maxTreeLen - treeArr[i];
                if (diff == 0) continue;
                
                evenCnt += diff / 2;
                oddCnt += diff % 2;
            }
            
            sb.append("#").append(tc).append(" ").append(simulation()).append("\n");
        }
        System.out.print(sb);
    }

    public static int simulation() {
        // Greedy Adjustment: 2(짝수날 성장량)를 1+1(홀수날 2회 성장량)로 변환하여 균형을 맞춤
        // 홀수 날(1)과 짝수 날(2)의 비율이 1:1에 가까울 때 최소 일수가 나옴
        while (evenCnt > oddCnt + 1) {
            evenCnt--;
            oddCnt ++;
            oddCnt ++;
        }
        
        if (oddCnt > evenCnt) {
            // 홀수 날이 더 많은 경우: 1, 0, 1, 0, 1 ... 순서 (마지막은 홀수 날)
            return oddCnt * 2 - 1;
        } else if (evenCnt > oddCnt) {
            // 짝수 날이 더 많은 경우: 0, 2, 0, 2, 0, 2 ... 순서 (마지막은 짝수 날)
            return evenCnt * 2;
        } else {
            // 홀수 날과 짝수 날 개수가 같은 경우: 1, 2, 1, 2 ... 순서
            return evenCnt * 2;
        }
    }
}