import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.util.TreeSet;
 
/**
 * 5658. 보물상자 비밀번호
 * @author neogul02
 * 
 * - 뚜껑은 시계방향으로 돌아감, 그만큼 숫자도 같이 돌아감
 * - N번 돌리고, 4방향으로 숫자 1개씩 자르기 -> 총 4N개의 숫자
 * - 16진수로 표현된 숫자 -> 10진수로 변환해서 K 번째로 큰 수 구하기 Integer.parseInt("16진수 문자열", 16) -> 10진수로 변환
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
 
    static int N, K; // 숫자 개수, K 번째로 큰 수
    static ArrayDeque<Character> box; // 보물상자 정보 저장
 
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            // 1. 입력 처리
            input();
 
            // 2. 풀이
            solve(tc);
        }
        System.out.print(sb);
    }
 
    public static void solve(int tc) {
        // 큐에서 뒤에서 꺼내서 앞에 넣기 -> 시계방향으로 회전
        TreeSet<Integer> numbersSet = new TreeSet<>((a,b)-> b - a); // 내림차순 정렬
         
        int sideLength = N / 4; // 한 변의 길이
         
        // N/4번 회전이 모든 경우를 커버
        for (int i = 0; i < sideLength; i++) {
            // 현재 상태에서 4개 변의 숫자를 추출
            ArrayDeque<Character> tempBox = new ArrayDeque<>(box);
             
            for (int j = 0; j < 4; j++) {
                StringBuilder numSb = new StringBuilder();
                // 한 변의 숫자를 추출
                for (int k = 0; k < sideLength; k++) {
                    numSb.append(tempBox.peekFirst());
                    tempBox.addLast(tempBox.pollFirst());
                }
                // 16진수를 10진수로 변환해서 저장
                int decimalValue = Integer.parseInt(numSb.toString(), 16);
                numbersSet.add(decimalValue);
            }
             
            // 한 칸 시계방향으로 회전
            char last = box.pollLast();
            box.addFirst(last);
        }
         
        // Set을 리스트로 변환하고 내림차순 정렬
        Integer[] numbers = numbersSet.toArray(new Integer[0]);
         
        // K번째로 큰 수 출력
        sb.append('#').append(tc).append(' ')
        .append(numbers[K - 1]).append("\n");
    }
     
    public static void input() throws IOException {
        // Line 1 - 숫자 개수 N, K 번째로 큰 수 K
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
 
        box = new ArrayDeque<>();
 
        // Line 2 - 보물상자 정보, 공백 없음
        String line = br.readLine().trim();
        for (int i = 0; i < N; i++) {
            box.add(line.charAt(i));
        }
    }
}