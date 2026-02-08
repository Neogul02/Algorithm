import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 도영이가만든맛있는음식2961_비트마스킹 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        
        int[] sArr = new int[N];
        int[] bArr = new int[N];
        
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            sArr[i] = Integer.parseInt(st.nextToken());
            bArr[i] = Integer.parseInt(st.nextToken());
        }
        
        int minDiff = Integer.MAX_VALUE;
        int subsetCount = 1 << N; // 2^N (부분집합의 총 개수)
        
        // 1. 모든 부분집합 케이스 순회 (i는 비트마스크)
        // 공집합(0)은 문제 조건(적어도 하나 사용)에 위배되므로 1부터 시작
        for(int i = 1; i < subsetCount; i++) {
            int totalSour = 1;
            int totalBitter = 0;
            
            // 2. 현재 부분집합(i)에서 선택된 재료 확인
            for(int j = 0; j < N; j++) {
                // j번째 재료가 선택되었는지 비트 확인
                if((i & (1 << j)) != 0) {
                    totalSour *= sArr[j];
                    totalBitter += bArr[j];
                }
            }
            
            minDiff = Math.min(minDiff, Math.abs(totalSour - totalBitter));
        }
        
        System.out.println(minDiff);
    }
}