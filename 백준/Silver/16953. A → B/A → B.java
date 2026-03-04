import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 16953. A → B
 * @author neogul02
 * 
 * - 정수 A를 B로 바꾸는데 가능한 연산 2가지
 * - 1. 2를 곱하는 연산
 * - 2. 수의 가장 오른쪽에 1을 추가하는 연산
 * 
 * - 초기 A부터 깊이를 0으로 생각하고 각 연산을 진행한 값을 큐에 넣으며 BFS로 탐색
 * - BFS로 탐색하면서 B가 나오는 순간의 깊이+1 가 A에서 B로 바꿀 수 있는 연산 최솟값이 된다
 * - 각 큐에 1, 2 연산을 진행한값 + 연산횟수(depth)도 +1 해서 같이 add
 * 
 * 출력. A에서 B로 바꿀 수 있는 연산 최솟값에 1을 더한 값을 출력
 * 
 * #1 틀린이유 - A랑 B가 10^9 = 10억인데 오른쪽에 1을 추가하면 100억1 이됨, = int overflow 
 * #1 solution - A, B를 long으로 선언
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long A, B; 

    public static void main(String[] args) throws IOException {
        input();
        bfs();
        System.out.print(sb);
    }

    private static void bfs() {
        ArrayDeque<long[]> queue = new ArrayDeque<>();
        queue.add(new long[] {A, 0}); // 초기값 A, depth는 0부터 시작

        while (queue.isEmpty() != true) {
            long[] temp = queue.poll();
            long num = temp[0];
            long depth = temp[1];

            if (num == B) { // hit 
                sb.append(depth + 1); // 최솟값에 +1 한 값을 출력
                return;
            }

            if (num > B)
                continue; // B보다 크면 더 이상 탐색할 필요 없음

            queue.add(new long[]{num*2, depth+1}); // 2를 곱하는 연산
            queue.add(new long[] {num*10+1, depth+1}); // 수의 가장 오른쪽에 1을 추가하는 연산
        }
        // 큐를 다 돌았는데도, B가 나오지 않았다면? -1
        sb.append(-1); 
    }
    
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
    }
}
