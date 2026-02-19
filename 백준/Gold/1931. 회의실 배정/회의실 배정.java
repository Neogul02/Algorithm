import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1931.회의실배정
 * @author neogul02
 * 
 * 
 * 활동 선택 문제..? 
 * -> 끝나는 시간을 기준으로 정렬해줘야한다고 기억함 
 * 
 * 1. 갯수 N을 입력받는다
 * 2. 타임블럭을 2차원 배열로 N 열 만큼 반복해서 초기화 해둔다
 * 3. 받은 타임블럭들을 정렬해야하는데
 *  3-1. 끝나는시간이 빠른순으로 정렬할거임
 *   3-1-1. 끝나는 시간이 다르면 끝나는 시간이 빠른순으로 정렬
 *   3-1-2. 끝나는 시간이 같다면 시작하는 시간을 비교해서 시작시간이 짧은 순으로 정렬
 * 4. 끝나는 시간을 핸들링하면서 배열에서 블럭을 가져올때마다 cnt++;
 * 5. cnt 를 반환 
 * 
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine().trim());
        int[][] meetings = new int[N][2];
        
        // 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            meetings[i][0] = Integer.parseInt(st.nextToken()); // 시작
            meetings[i][1] = Integer.parseInt(st.nextToken()); // 종료
        }
        
        // 정렬: 종료 시간 우선, 같으면 시작 시간
        Arrays.sort(meetings, (a, b) -> {
        	// 끝나는 시간이 다를때
            if (a[1] != b[1]) {
                return a[1] - b[1]; // 음수면 a가 b보다 앞에, 0이면 유지 양수면 b가  a보다 앞에 위치
            }
            // 끝나는 시간이 같을때는 시작시간이 짧은순으로 정렬,
            return a[0] - b[0]; // 시작 시간 오름차순
        });
        
        // 그리디
        int cnt = 0;
        int lastEndTime = 0;
        
        for (int i = 0; i < N; i++) {
            if (meetings[i][0] >= lastEndTime) { // 0부터 시작 종료시간 '이상'인 다음 블럭 탐색
                cnt++;
                lastEndTime = meetings[i][1]; // 선택한 
//                System.out.println(Arrays.toString(meetings[i]));
            }
        }
        System.out.println(cnt);
	}
}
