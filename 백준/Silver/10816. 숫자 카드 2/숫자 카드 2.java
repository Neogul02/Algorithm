import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 10816. 숫자카드2
 * @author neogul02
 * 
 * 1. 숫자카드 개수 N 을 입력받는다.
 * 2. N개만큼 숫자카드를 입력받는다. 해시맵에 키를 카드넘버로, 밸류를 카운팅해준다.
 * 3. 구해야할 카드 개수 M개를 입력받는다.  
 * 4. M개만큼 숫자카드를 입력받는다
 * 5. 찾는 카드를 키로 해서 해시맵에서 밸류를 가져오고, 키가 없으면 0을 리턴
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 1. 숫자카드 개수 N 을 입력받는다.
		int N = Integer.parseInt(br.readLine().trim());
		
		HashMap<Integer, Integer> map = new HashMap<>();
		
		// 2. N개만큼 숫자카드를 입력받는다. 해시맵에 카운팅
		st = new StringTokenizer(br.readLine().trim(), " ");
		for(int i=0; i<N; i++) {
			int cardNum = Integer.parseInt(st.nextToken());
			// 키가 없으면 0으로 설정하고 있으면 기존값 반환
			map.put(cardNum,map.getOrDefault(cardNum, 0)+1);
			
		}
		
		// 3. 구해야할 카드 개수 M개를 입력받는다.  
		int M = Integer.parseInt(br.readLine().trim());
		
		// 4. M개만큼 숫자카드를 입력받는다
		st = new StringTokenizer(br.readLine().trim(), " ");
		
		// 5. 입력받은 값을 키로 해서 해시맵에서 값를 가져오고, 찾는 키가 없으면 0을 출력
		for(int i=0; i<M; i++) {
			int searchKey = Integer.parseInt(st.nextToken());
			sb.append(map.getOrDefault(searchKey, 0)).append(" ");
		}
		System.out.print(sb);
	}
}