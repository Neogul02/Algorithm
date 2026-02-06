import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2961. 도영이가 만든 맛있는 음식
 * @author neogul02
 * 
 * ** 신맛은 곱, 쓴맛은 합 **
 * 1. 재료의 개수 N을 입력받는다.
 * 2. N번 반복~
 * 	2-1. S 신맛, B 쓴맛 공백으로 입력받기
 * 3. 각각 신맛의 부분집합, 쓴맛의 부분집합을 구한다.
 * 4. 3.에서의 신맛 값을 곱하고, 쓴맛값은 더해 바로바로 비교해 최솟값을 갱신시킨다.
 *
 */
public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    
    static int N;
    static int[] sArr, bArr; // 신맛, 쓴맛 저장 arr
    
    static int[] numbers;
    static boolean[] isSelected;
    
    static int minVal = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException{
    	br = new BufferedReader(new InputStreamReader(System.in));

    	N = Integer.parseInt(br.readLine().trim());
    	sArr = new int[N];
    	bArr = new int[N];
    	isSelected = new boolean[N];

    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine().trim());
    		sArr[i] = Integer.parseInt(st.nextToken());
    		bArr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	generateSubset(0);
    	
    	System.out.println(minVal);
	}

	public static void generateSubset(int depth) {
		// 종료조건
		if(depth == N) {
			int totalSour = 1; // 곱
			int totalBitter = 0; // 합
			int cnt =0;
			for(int i=0; i<N; i++) {
				if(isSelected[i]==true) {
					totalSour *= sArr[i];
					totalBitter += bArr[i];
					cnt++;
				}
			}
			if(cnt > 0) {
                int diff = Math.abs(totalSour - totalBitter);
//                System.out.println(String.format("totalSour: %d, totalBitter: %d", totalSour,totalBitter));
                // 최소값 갱신
                if(diff < minVal) {
                    minVal = diff;
                }
            }
			return;
		}
	
		// 포함하는 경우
		isSelected[depth] = true;
		generateSubset(depth+1);
		
		// 포함하지 않는 경우
		isSelected[depth] = false;
		generateSubset(depth+1);
	}
}