import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author SSAFY
 * N*N개의 벌통이 정사각형 모양으로 배치
 * 
 * 
 * 1. 테스트케이스 개수 T 입력받기
 * 2. 각 테스트 케이스마다~
 *  2-1. N*N 맵크기, 채취할 수 있는 벌집개수, 채취할 수 있는 최대 꿀량
 *  2-2. 벌꿀 정보를 입력 받는다 -> 2차원배열 안쓰고 1차원으로 활용
 *  
 * 3. 작업자가 채취할 수 있는 경우를 살펴본다. (순서가 중요한가? x -> 조합)
 *  3-1. 모든 작업자가 챙취할 위치를 선정했으면 -> 계산해야함
 *   3-1-1. 각 작업자마다 채취를 시작한다.
 *    3-1-1-1. 채취할때 인덱스를 벗어나면 안됨 -> 
 *    3-1-1-2. 상품성이 떨어지면 안됨 = 작업자 둘이 범위가 겹치면 안됨
 *    3-1-1-3. 최대 채취량을 넘길 수 없다.
 *    3-1-1-4. 채취할 수 있다.-> 바로 채취 가능
 *   
 *  3-2. 모든 위치를 다 확인했으면 -> 종료해야함
 *  3-3. 아직 모든 작업자가 위치를 선택하지 않았거나, 모든 위치를 확인하지 않았으면 -> 계속 조합해야함
 *  
 *  N-1번 
 * 
 */
public class 꿀통2115 {
	private static final int NOT_SELECTED = 0;
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int mapSize, collectHoneyCombCount, maxHoneyCount;
	static int[] honeyMap;
	
	
	public static void inputTestCase() throws IOException{
		// 2-1. N*N 맵크기, 채취할 수 있는 벌집개수, 채취할 수 있는 최대 꿀량
		st = new StringTokenizer(br.readLine().trim());
		int mapSize = Integer.parseInt(st.nextToken()); // 벌통 크기
		int collectHoneyCombCount = Integer.parseInt(st.nextToken()); // 선택할 벌통 개수
		int maxHoneyCount = Integer.parseInt(st.nextToken()); // 최대 꿀 채취량

		// 2-2. 벌꿀 정보를 입력 받는다 -> 2차원배열 안쓰고 1차원으로 활용
		honeyMap = new int[mapSize*mapSize];
		
		int tempIndex = 0;
		for(int rowIndex=0; rowIndex<mapSize; rowIndex++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int columnIndex =0; columnIndex<mapSize; columnIndex++) {
				honeyMap[tempIndex] = Integer.parseInt(st.nextToken());
				tempIndex++;
			}
		}
	}
	
	public static boolean isOverRange(int workerIndex) {
		// 채취하려고하는 위치
		int collectRowIndex = startCollectIndexArray[workerIndex] / mapSize; // 행 위치
		int oneDimensionalIndex = startCollectIndexArray[workerIndex];
		
		// 채취할 위치부터 채취할 칸을 확인함.
		// 행의 위치가 바뀌면 안됨
		for(int nextHoneyCombIndex = oneDimensionalIndex+1; nextHoneyCombIndex< oneDimensionalIndex; nextHoneyCombIndex++) {
			
			// 다음 줄로 넘어감.
			if(collectRowIndex != (nextHoneyCombIndex/mapSize)) {
				return true;
			}
		}
		// 한 줄에 있음 
		return false;
	}
	
	static boolean[] isCollected;
	public static boolean isDuplicated(int currentWorkerindex) {
		// TODO Auto-generated method stub
		int startCollectIndex = startCollectIndexArray[currentWorkerindex];
		for(int honeyCombIndex = startCollectIndex; honeyCombIndex<startCollectIndex; honeyCombIndex++) {
			if(isCollected[honeyCombIndex]) {
				return true;
			}
			isCollected[honeyCombIndex] = true;
		}
		return false;
	}
	
	private static boolean isOverCollected(int currentWorkerindex) {
		// TODO Auto-generated method stub
		int sumHoneyCount =0;
		int startCollectIndex = startCollectIndexArray[currentWorkerindex];
		for(int honeyCombIndex = startCollectIndex; honeyCombIndex<startCollectIndex+1; honeyCombIndex++) {
			sumHoneyCount += honeyMap[honeyCombIndex];
			
		}
		if(sumHoneyCount > maxHoneyCount) {
			return true;
		}
		return false;
	}
	
	static boolean[] isSelected;
	static int totalMaxProfit;
	
	public static void collectHoney(int workerIndex, int honeyCombIndex) {
		
		// 3-1. 모든 작업자가 챙취할 위치를 선정했으면 -> 계산해야함
		if(workerIndex == WORKER_COUNT) {
			isSelected = new boolean[mapSize * mapSize];
			maxProfitByWorker = new int[WORKER_COUNT];
			
			// 3-1-1. 각 작업자마다 채취를 시작한다.
			for(int currentWorkerindex=0; currentWorkerindex< WORKER_COUNT; currentWorkerindex++) {
				// 3-1-1-1. 채취할때 인덱스를 벗어나면 안됨 -> 
				if(isOverRange(currentWorkerindex)) {
					return;
				}
				
				// 3-1-1-2. 상품성이 떨어지면 안됨 = 작업자 둘이 범위가 겹치면 안됨
				if(isDuplicated(currentWorkerindex)) {
					return;
				}
				
				// 3-1-1-3. 최대 채취량을 넘길 수 없다.
				if(isOverCollected(currentWorkerindex)) {
					// 그 중에서 최대로 얻을 수 있는 만큼 얻어야함. (부분집합)
					isSelected = new boolean[collectHoneyCombCount];
					calculateProfit(0, currentWorkerindex);
					continue;
				}

				// 3-1-1-4. 채취할 수 있다.-> 바로 채취 가능
				int tempProfit = 0;
				int startCollectIndex =startCollectIndexArray[workerIndex];
				
				for(int i= startCollectIndex; i< startCollectIndex+ collectHoneyCombCount; i++) {
					tempProfit += (honeyMap[i] * honeyMap[i]);
				}
				// 기존에 찾아본 이익보다 더 큰 이익이면
				if(maxProfitByWorker[currentWorkerindex]<tempProfit) {
					maxProfitByWorker[currentWorkerindex] = tempProfit;
				}
			}
			
			if(totalMaxProfit < maxProfitByWorker[0]+maxProfitByWorker[1]) {
				totalMaxProfit = maxProfitByWorker[0]+maxProfitByWorker[1];
			}

			return;
		}
		// 3-2. 모든 위치를 다 확인했으면 -> 종료해야함
		if(honeyCombIndex == mapSize*mapSize) {
			return;
		}
		// 3-3. 아직 모든 작업자가 위치를 선택하지 않았거나, 모든 위치를 확인하지 않았으면 -> 계속 조합해야함
		startCollectIndexArray[workerIndex] = honeyCombIndex;
		collectHoney(workerIndex+1, honeyCombIndex+1); // 선택을 했다.
		
		// 선택하지 않았으니 다음 위치로 이동
		startCollectIndexArray[workerIndex] = NOT_SELECTED;
		collectHoney(workerIndex, honeyCombIndex+1); // 선택을 했다.
		
	}
	
	static int[] maxProfitByWorker;
	private static void calculateProfit(int selectCombIndex, int workerIndex) {
		// TODO Auto-generated method stub
		if(selectCombIndex == collectHoneyCombCount) {
			// 이익 계산
			int sumHoneyCount = 0;
			int sumProfit = 0;
			for(int i=0; i< collectHoneyCombCount; i++) {
				if(isSelected[i]) {
					int honey = honeyMap[startCollectIndexArray[workerIndex]];
					sumHoneyCount += honey;
					sumProfit += (honey * honey);
				}
			}
			
			// 계산을 다 했으면
			if(sumHoneyCount <= maxHoneyCount && maxProfitByWorker[workerIndex] < sumProfit) {
				maxProfitByWorker[workerIndex] = sumProfit;
			}
			return ;
		}
		isSelected[selectCombIndex] = true;
		calculateProfit(selectCombIndex+1, workerIndex);
		
		isSelected[selectCombIndex] =false;
		
	}

	static final int WORKER_COUNT = 2; // 작업자의 수
	static int[] startCollectIndexArray; // 각 작업자가 채취할 시작 위치
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 테스트 케이스 개수를 입력 받는다.
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			totalMaxProfit =0;
			
			// 2. 각 테스트 케이스마다.
			inputTestCase();
			
			// 3. 작업자가 채취할 수 있는 경우
			startCollectIndexArray = new int[WORKER_COUNT];
			collectHoney(0,0);
			
			
			// 4. 최대 이익을 출력 
			sb.append("#").append(tc).append(" ").append(totalMaxProfit).append("\n");

		}
		System.out.println(sb);
	}
}
