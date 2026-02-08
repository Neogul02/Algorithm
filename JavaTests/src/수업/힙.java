package 수업;
import java.util.Collections;
/**
 * 
 * @author neogul02
 * 힙은 완전 이진 트리여야함
 * 루트부터 포화이진트리~~>
 *
 * 최대 힙 -> 키값이 가장 큰 노드를 찾기 위한 완전 이진 트리
 * 부모노드의 키 값 >= 자식 노드의 키 값
 * 루트노트 -> 존나 제일 큼 
 * 
 * 최소 힙 -> 키값이 가장 작은 노드를 찾기 위한 완전 이진 트리
 * 부모노드의 키 값 <= 자식 노드의 키 값
 * 루트노드 -> 제일 조빱
 * 
 * 힙에서는 루트노드의 원소만을 삭제 할 수 있다.
 * 
 * java.util.PriorityQueue<E> 
 */
import java.util.PriorityQueue;
public class 힙 {
	public static void main(String[] args) {
		// 기본은 최소힙임!! 
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		// 최대힙으로-> 내림차순으로 만들고싶으면 Collections.reverseOrder()
		PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
		
		pq.add(5);
		pq.add(1);
		pq.add(3);

		// 3. 삭제(꺼내기): O(log N) -> 가장 작은 1이 나옴
		pq.toArray();
		System.out.println(pq);
		
	}
	

}
