import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author SSAFY
 *
 * 1. 8개의 LED 1은 켜져있음, 0은 꺼져있음 
 * 2. 학생 몇명에게 1이상 8이하 자연수를 나눠줌
 * 3. 성별과 받은 숫자에 따라 LED를 조작함
 * 
 * * 남학생은 LED 번호가 자기가 받은 수의 배수이면 On/Off 함
 * 3을 받으면 3,6번 LED를 키고, 끈다
 * 01010001 -> 01110101
 * * 여학생은 받은 번호를 중심으로 좌우대칭을 검사함 -> 루프 돌면서 검사
 * 3을 받으면
 * 01110101 -> 10001101 
 *
 */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	// 받은 숫자 저장
	static int N;
	static int[] LED;
	
	// 학생 수
	static int studentNum;
	static int[][] students; 
	
	
	public static void main(String[] args) throws IOException{
		input();
		solve();
		print();
	}

	
	// LED 사이에 빈 칸 두기
	private static void print() {
		
		// 한 줄에 20개씩 출력한다.
		for(int i=1; i<N+1; i++) {
			// 21번 LED는 둘째 줄 맨앞,
			sb.append(LED[i]).append(' ');
			
			// 첫번째 인덱스는 제외
			if(i!=1 && (i)%20 == 0) sb.append('\n'); // 21, 41, 61, 81
		}
		System.out.print(sb);
	}

	private static void solve() {
		// 0. 시뮬레이션을 몇번 수행할가? 받은 학생의 수만큼 반복한다.
		for(int tc=0; tc<studentNum; tc++) {
			// 1. 남자인 경우 == 1
			if(students[tc][0] == 1) {
				man(students[tc][1]); // 넘겨줄때 받은 숫자도 같이 넘겨주자
			}
			
			// 2. 여자인 경우 == 1
			if(students[tc][0]==2) {
				girl(students[tc][1]);
			}
			
//			System.out.println("테스트 후 : "+Arrays.toString(LED));
		}
	}
	
	private static void man(int targetNum) {
//		System.out.println("남자가 받은 숫자: "+targetNum);
		
		for(int n=1; n<N+1; n++) { // LED 갯수만큼 반복하면서
			// LED번호가 받은 숫자의 배수이면
			if(n%targetNum == 0) {
				// 숫자 반전
				if(LED[n]==1) LED[n] = 0;
				else LED[n] = 1;
			}
		}
	}


	private static void girl(int targetNum) {
//		System.out.println("여자가 받은 숫자: "+targetNum);
		
		int leftPointer = targetNum;
		int rightPointer = targetNum;
		
		// targetNum은 우선 반전 해두기
		if(LED[targetNum]==0) LED[targetNum] =1;
		else LED[targetNum] =0;
		
		while(true) {
			leftPointer = leftPointer - 1;
			rightPointer = rightPointer + 1;
			
			// 탈출조건 1 left포인터가 처음 idx를 가르키거나
			if (leftPointer == 0)
				break;
			
			// 탈출조건 2 right포인터가 마지막 idx를 가르키거나
			if (rightPointer > N)
				break;
			
			// 탈출조건 2 대칭으로 같지 않다면 out
			if (LED[leftPointer] != LED[rightPointer]) {
				break;
			}
			
			// 대칭으로 같다면
			if(LED[leftPointer] == LED[rightPointer]) {
				// 왼쪽 반전
				if(LED[leftPointer]==1) LED[leftPointer] = 0;
				else LED[leftPointer] =1;
				// 오른쪽 반전
				if(LED[rightPointer]==1) LED[rightPointer] = 0;
				else LED[rightPointer] =1;
			}
		}	
	}

	private static void input() throws IOException{
		
		// 첫째줄에는 LED의 개수 <=100
		N = Integer.parseInt(br.readLine().trim());
		
		// 둘째줄에는 LED 상태 주어짐
		st = new StringTokenizer(br.readLine().trim()," ");
		LED = new int[N+1]; // 1칸 더 크게 설정  0번째 인덱스 안쓰게
		
		for(int i=1; i<=N; i++) { // 1칸 더 크게
			LED[i] = Integer.parseInt(st.nextToken());
		}
		
		// 셋째줄에는 학생 수
		studentNum = Integer.parseInt(br.readLine().trim());
		
		// 넷째줄에는 성별, 받은 숫자 제공
		
		students = new int[studentNum][2];
		for(int i=0; i<studentNum; i++) {
			st = new StringTokenizer(br.readLine().trim()," ");
			int sex = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			students[i] = new int[] {sex, num};
		}
		
		
		// testPrint 
//		System.out.println(Arrays.toString(LED));
//		System.out.println(Arrays.deepToString(students));
	}
}
