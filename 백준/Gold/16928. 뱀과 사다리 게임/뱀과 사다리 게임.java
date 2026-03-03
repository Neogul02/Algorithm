import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 16928. 뱀과 사다리 게임
 * @author neogul02
 * 
 * - 1~6까지 수의 정육면체 주사위 사용
 * - 크기 10 x 10 보드, 100칸 보드판, 보드판에는 1~100까지 번호있음
 * - 주사위를 굴려서 나온 수만큼 이동해야함 -> 100번칸을 넘어가면 이동 x
 * 
 * 제약 1. 유저 위치는 1~100 이다, 100을 넘길 수 없음
 * 제약 2. 도착한 칸이 사다리라면 사다리를 타고 위로 올라간다. 번호 늘어남 +
 * 제약 3. 도착한 칸이 뱀이라면 뱀을 타고 아래로 내려간다. 번호 줄어듬 -
 * 제약 4. 사다리와 뱀의 위치는 겹치지 않음
 * 
 * 목표는 100번째 칸에 최소로 주사위를 굴려 도착하는것이니까 = 최단경로 bfs 탐색
 * -> 6번 반복하며 각 주사위 경우의수를 모두 탐색 하다가 사다리나 뱀을 만나면 점프하고 큐에넣기?
 * -> 기저조건은 100번칸에 도착하면 종료하고 주사위 횟수 return, 100번칸을 넘어가면 이동 x
 * 
 * 출력 1. 주사위를 굴리는 횟수의 최솟값
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M; // 사다리 개수, 뱀 개수
    static int[] board; // 보드판, 1~100까지 번호, 0은 사용안함
    static boolean[] visited; // 방문 체크

    static int diceCnt;

    public static void main(String[] args) throws IOException {
        input();
        bfs();
        System.out.print(sb);
    }

    private static void bfs() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(1); // 시작위치 큐에 넣고
        visited[1] = true; // 방문처리

        diceCnt = 0; // 주사위 굴린 횟수

        while (queue.isEmpty() != true) {
            int size = queue.size(); // 현재 큐에 있는 위치의 개수
            diceCnt++; // 주사위 굴린 횟수 증가

            for (int s = 0; s < size; s++) { // 현재 큐에 있는 위치들에 대해서 주사위 굴리는 경우의 수 탐색
                
                int temp = queue.poll(); // 큐에서 하나 꺼내서

                for (int i = 1; i <= 6; i++) { // 1~6까지 반복하며 주사위 굴리는 경우의 수 탐색
                    int next = temp + i; // 다음 위치

                    if (next > 100)
                        continue; // 100번칸을 넘어가면 이동 x

                    if (next == 100) { // 다음 위치가 100번칸이면 종료
                        sb.append(diceCnt).append('\n');
                        return;
                    }

                    if (board[next] != 0) { // 사다리나 뱀을 만나면 점프하기
                        next = board[next];
                    }

                    if (next == 100) { // 이동 후 100번칸에 도착하면 종료
                        sb.append(diceCnt).append('\n');
                        return;
                    }

                    if (visited[next] == false) { // 방문하지 않은 칸이면 큐에 넣기
                        visited[next] = true; // 방문처리
                        queue.add(next); // 큐에 넣기
                    }
                }

            }
        }
        
    }
    
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken()); // 사다리 개수
        M = Integer.parseInt(st.nextToken()); // 뱀 개수

        board = new int[101]; // 보드판, 1~100까지 번호, 0은 사용안함
        visited = new boolean[101];

        // 뱀이랑 사다리 구분 x -> 시작 칸에 도착 칸 저장하기
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            int from = Integer.parseInt(st.nextToken()); // 시작 칸
            int to = Integer.parseInt(st.nextToken()); // 도착 칸

            board[from] = to; // 사다리나 뱀의 시작 칸에 도착 칸 저장
            // 12 98 => board[12] = 98
        }
    }
}
