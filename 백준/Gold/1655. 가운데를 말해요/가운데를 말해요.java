import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 1655. 가운데를 말해요
 * @author neogul02
 * 
 * 각 숫자가 입력될 때마다 가운데 수가 무엇인지 출력하는 문제
 * 
 * 생각해야할거
 * 1. 입력되는 숫자 개수가 최대 100,000개이므로 O(N^2)으로 풀면 안됨
 * 2. 최대힙과 최소힙을 이용해서 가운데 수를 빠르게 찾자
 * 
 * 트리 힙은 자동 정렬이 되는 자료구조이므로 최대힙에는 가운데 수보다 작은 수들을 넣고, 
 * 최소힙에는 가운데 수보다 큰 수들을 넣어서 가운데 수 찾기
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine().trim()); // 입력되는 숫자 개수

        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder()); // 최대힙
        PriorityQueue<Integer> right = new PriorityQueue<>(); // 최소힙

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine().trim()); // 입력받은 숫자

            if (i == 0) { // 첫번째 수는 항상 가운데 수
                left.add(num);
                sb.append(num).append('\n');
            } 
            else { // 2번째 수 부터 비교
                if (num <= left.peek() ) { // num이 가운데 수보다 작거나 같으면 최대힙에 추가
                    left.add(num);
                } else { // num이 가운데 수보다 크면 최소힙에 추가
                    right.add(num);
                }

                // left와 right의 크기 차이가 1 이상이면 균형 맞추기
                if (left.size() > right.size() + 1) {
                    right.add(left.poll()); // left에서 가장 큰 수를 right로 이동
                } else if (right.size() > left.size()) {
                    left.add(right.poll()); // right에서 가장 작은 수를 left로 이동
                }
                
                // 가운데 수 출력
                sb.append(left.peek()).append('\n'); // left의 마지막 수가 가운데 수
            }
        }
        System.out.print(sb);
    }
}