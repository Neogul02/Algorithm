import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 10번 루프 안에서 동작 ->
 * 1. 테스트 케이스 번호 입력받기 : int T
 * 2. 8개의 숫자를 입력받아 Deque에 넣어두기 : numQ 
 * 3. 사이클 안에서 1부터 5까지 굴리기
 * 4. 출력하기
 */ 
public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 10개의 테스트 케이스 루프
        for (int i = 0; i < 10; i++) {
            String line = br.readLine().trim();
            if (line == null) break; // 입력이 끝났을 경우 대비
            
            int T = Integer.parseInt(line);
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            Deque<Integer> numQ = new ArrayDeque<>();
            // 2. 8개의 숫자를 Deque에 넣기
            for (int j = 0; j < 8; j++) {
                numQ.addLast(Integer.parseInt(st.nextToken()));
            }
            
            // 3. 암호 생성 로직
            boolean isFinished = false;
            while (!isFinished) {
                // 한 사이클: 1부터 5까지 순환
                for (int d = 1; d <= 5; d++) {
                    int first = numQ.pollFirst() - d;
                    
                    if (first <= 0) {
                        first = 0;
                        numQ.addLast(first);
                        isFinished = true;
                        break;
                    }
                    numQ.addLast(first);
                }
            }
            
            sb.append("#").append(T).append(" ");
            for (int num : numQ) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
	
}
