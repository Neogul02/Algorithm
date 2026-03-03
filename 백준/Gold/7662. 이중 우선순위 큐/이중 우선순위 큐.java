import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * 7662. 이중 우선순위 큐
 * @author neogul02
 * 
 * 1. 테스트케이스 T 를 입력받고 T번 반복
 * 2. 테스트케이스 시작 -> 몇개의 명령어를 받을것인가? cnt
 * 3. cnt개의 명령어 입력받기 -> 명령어는 I num 또는 D num
 *  3-1. 트리맵 하나를 초기화해서 큐처럼 사용하기
 *  3-2. I num : num을 트리맵에 삽입
 *   3-3-1. D 1 : 트리맵에서 최댓값을 삭제 = treeMap.lastKey() = <최댓값, 개수>
 *   3-3-2. D -1 : 트리맵에서 최솟값을 삭제 = treeMap.firstKey() = <최솟값, 개수>
 * 4. cnt개의 명령어가 끝나면 트리맵이 비어있는지 확인 -> 비어있으면 EMPTY 출력
 * 5. 트리맵이 비어있지 않으면 최댓값과 최솟값 출력
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static TreeMap<Integer, Integer> treeMap;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 수

        for (int tc = 0; tc < T; tc++) {
            int cnt = Integer.parseInt(br.readLine().trim()); // 연산의 개수
            treeMap = new TreeMap<>(); // 트리맵 초기화

            for (int i = 0; i < cnt; i++) {
                st = new StringTokenizer(br.readLine().trim(), " ");
                char cmd = st.nextToken().trim().charAt(0); // 명령어 D 랑 I
                int num = Integer.parseInt(st.nextToken()); // 숫자

                if (cmd == 'I') { // I num : num을 Q에 삽입
                    treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
                } else if (cmd == 'D') {
                    if (treeMap.isEmpty())
                        continue; // Q가 비어있는데 D 연산이 들어오면 무시

                    if (num == 1) { // D 1 : queue에서 최댓값을 삭제

                        int maxKey = treeMap.lastKey(); // 최댓값 키 가져오기

                        if (treeMap.get(maxKey) == 1) { // 최댓값의 개수가 1개인 경우
                            treeMap.remove(maxKey); // 최댓값 제거
                        } else {
                            treeMap.put(maxKey, treeMap.get(maxKey) - 1); // 최댓값 개수 감소
                        }

                    } else if (num == -1) { // D -1 : queue에서 최솟값을 삭제

                        int minKey = treeMap.firstKey(); // 최솟값 키 가져오기

                        if (treeMap.get(minKey) == 1) { // 최솟값의 개수가 1개인 경우
                            treeMap.remove(minKey); // 최솟값 제거
                        } else {
                            treeMap.put(minKey, treeMap.get(minKey) - 1); // 최솟값 개수 감소
                        }
                    }
                }
            }
            
            if (treeMap.isEmpty()){ // 연산 후 큐가 비어있으면 EMPTY
                sb.append("EMPTY\n");
            }
            else { // 큐가 비어있지 않으면 최댓값과 최솟값 출력
                sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
            }
        }
        System.out.print(sb);
    }
}