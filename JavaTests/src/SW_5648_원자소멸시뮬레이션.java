import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 5648. 원자 소멸 시뮬레이션 - 좌표 이동 방식
 * @author neogul02 
 * 
 * [Solve IDEA]
 * 1. 입력 좌표 -1000~1000을 0~2000으로 이동 +1000 해서 최소를 0,0으로 잡기
 * 2. 0.5 단위 때문에 맵 좌표 스케일을 두배로 늘리기 -> 최종 좌표: 0~4000 범위 (모두 양수!)
 * 3. 입력을 받고 클래스화 해서 구조를 잡고 ArrayList에 원자들을 담아두기
 * 
 * -> 4. 시뮬레이션: 매 시간마다 원자 이동 -> 충돌 검사 -> 에너지 계산
 * 
 * 4000*N 
 */
public class SW_5648_원자소멸시뮬레이션 {
    static class Atom {
       
        int x, y;
        int dir;
        int power;
        
        /**
         * @param inputX 입력 X 좌표 (-1000 ~ 1000)
         * @param inputY 입력 Y 좌표 (-1000 ~ 1000)
         * @param dir 이동 방향 (0:상, 1:하, 2:좌, 3:우)
         * @param power 원자 보유 에너지
         */
        public Atom(int inputX, int inputY, int dir, int power) {
            
            // 1. 좌표이동 + 2배 스케일 적용 (0~2000 -> 0~4000) 0.5 구간
            this.x = (inputX + 1000) * 2;
            this.y = (inputY + 1000) * 2;
            
            this.dir = dir;
            this.power = power;
        }
    }
  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 테스트케이스 개수 입력
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= T; tc++) {
            // 원자 개수 N 입력
            int N = Integer.parseInt(br.readLine().trim());
            
            // 원자 리스트 atoms 초기화
            ArrayList<Atom> atoms = new ArrayList<>();
            
            // N개의 원자 정보 입력받기
            for (int i = 0; i < N; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine().trim());
                int x = Integer.parseInt(st.nextToken());      // X 좌표
                int y = Integer.parseInt(st.nextToken());      // Y 좌표
                int dir = Integer.parseInt(st.nextToken());    // 방향
                int power = Integer.parseInt(st.nextToken());  // 에너지
                
                // 원자 객체 생성 및 리스트에 추가
                atoms.add(new Atom(x, y, dir, power));
            }
            int totalEnergy = simulate(atoms);
            sb.append("#").append(tc).append(" ").append(totalEnergy).append("\n");
        }
        System.out.print(sb);
    }
    
    static int[] dx = {0, 0, -1, 1};  // 상하는 X 이동 없음, 좌우는 ±1
    static int[] dy = {1, -1, 0, 0};  // 상(+1), 하(-1), 좌우는 Y 이동 없음
    
    /**
     * 원자 소멸 시뮬레이션 실행
     * 
     * [시뮬레이션 단계]
     * 1. 모든 생존 원자를 방향에 따라 이동
     * 2. 맵 밖으로 나간 원자 제거
     * 3. 같은 위치에 있는 원자들을 HashMap으로 그룹화
     * 4. 2개 이상 모인 위치의 원자들을 충돌 처리 (에너지 합산, 소멸 처리)
     * 5. 모든 원자가 소멸했는지 확인
     * 
     * @param atoms 원자 리스트
     * @return 총 방출된 에너지
     */
    static int simulate(ArrayList<Atom> atoms) {
        int totalEnergy = 0;  // 총 방출 에너지
        final int MAX_TIME = 4000;  // 최대 시뮬레이션 시간 (맵 크기 기준)
        
        // 매 시간마다 시뮬레이션 진행
        for (int time = 0; time < MAX_TIME; time++) {
        	
        	if (atoms.isEmpty()) break;
            
            // 1. 모든 원자 이동
            for (Atom a : atoms) {
                // 방향에 따라 좌표 이동 (2칸씩 = 실제 0.5칸)
                a.x += dx[a.dir];
                a.y += dy[a.dir];
            }
            
            // 위치별 원자 그룹화 
            // Key: "x,y" 형태의 좌표 문자열, Value: 해당 위치의 원자 리스트
            HashMap<Integer, ArrayList<Atom>> positionMap = new HashMap<>();
            
            for (Atom a : atoms) {
                // 3단계: 맵 밖으로 나간 원자 제거
                if (a.x < 0 || a.x > 4000 || 
                    a.y < 0 || a.y > 4000) {
                    continue;
                }
                
                // 위치별로 그룹화 (key 값을 해시??? 처리하면 시간초과
                int key = (a.x << 12) | a.y;
                positionMap.putIfAbsent(key, new ArrayList<>());
                positionMap.get(key).add(a);
            }
            
            ArrayList<Atom> survivors = new ArrayList<>();
            
            // 충돌 분기 검사
            for (ArrayList<Atom> group : positionMap.values()) {
                if (group.size() == 1) {
                    survivors.add(group.get(0));
                } else {
                    // 충돌 - 에너지 합산
                    for (Atom a : group) {
                        totalEnergy += a.power;
                    }
                }
            }
            
            atoms = survivors;
            
        }
        return totalEnergy;
    }
}