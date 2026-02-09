import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 17281. 야구
 *
 * 1. 1번 선수를 4번 타자에 고정.
 * 2. 나머지 8명 선수의 타순을 순열로 결정.
 * 3. 결정된 타순으로 야구 시뮬레이션 돌려서 최대 점수 갱신. 
 * 
 */
public class Main {
	
	static int N;
	
    static int[][] inningArr; // [이닝][선수번호]
    
    static boolean[] selected; // 순열 체크용
    static int[] lineup; // 결정된 타순 (선수 번호가 들어감)
    
    static int maxScore = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine().trim()); // 이닝 수 N
		
		inningArr = new int[N][9];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0; j<9; j++) {
				inningArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		lineup = new int[9];
        selected = new boolean[9];
		
		lineup[3] = 0; // 1. 1번 선수를 4번 타자에 고정.
		selected[0] = true; // 1번 선수는 고정 (0번 idx)
		
		permutation(0);
		
		System.out.println(maxScore);
	}

	public static void permutation(int depth) {
		// 기저 조건
		if(depth==9) {
			simulation();
			return;
		}
		if(depth==3) { // 4번타자 자리는 1번 선수로 고정 -> 넘기기
			permutation(depth+1);
			return;
		}
		
		// 수행 로직
		for(int i=1; i<=8; i++) { // 1번선수는 제외, 2번 선수부터 ~
			if(selected[i]==true) continue; // 선택된 선수는 제외
			
			selected[i] =true;
			lineup[depth] = i;
			permutation(depth+1);
			selected[i] =false;

		}

	}

	private static void simulation() {
		int score = 0;
		int start = 0; // 첫 이닝은 1번타자부터
		
		for(int i=0; i<N; i++) { // 이닝 반복
            int outCount = 0;
            boolean[] bases = new boolean[4]; // 1루, 2루, 3루 (0번 인덱스 안씀)
            
            // 3아웃 될 때까지 진행
            while(outCount < 3) {
                int batter = lineup[start]; // 현재 타석에 선 선수 번호
                int result = inningArr[i][batter]; // 그 선수의 결과
                
                start = (start + 1) % 9; // 다음 타자 준비 (미리 증가)
                
                if(result == 0) {
                    outCount++;
                } else {
                    // 안타, 2루타, 3루타, 홈런 처리
                    // 주자들 이동 및 득점 계산
                    
                    // 3루부터 1루까지 역순으로 확인해야 꼬이지 않음
                    for(int b=3; b>=1; b--) {
                        if(bases[b]) {
                            int nextBase = b + result; // 진루할 위치
                            
                            if(nextBase >= 4) {
                                score++; // 홈인
                            } else {
                                bases[nextBase] = true; // 진루
                            }
                            bases[b] = false; // 원래 있던 자리는 비움
                        }
                    }
                    
                    // 타자 이동
                    if(result == 4) {
                        score++; // 홈런이면 타자도 득점
                    } else {
                        bases[result] = true; // 타자가 N루에 나감
                    }
                }
            }
        }
        
        maxScore = Math.max(maxScore, score);
	}
}
