package 수업;

import java.util.ArrayDeque;
import java.util.Queue;

public class CompleteBinaryTree<T> {
	
	private Object[] nodes;
	private final int SIZE;
	private int lastIndex =0;
	
	public CompleteBinaryTree(int size) {
		SIZE = size;
		nodes = new Object[size+1];
	}
	public boolean isEmpty() {
		return lastIndex == 0;
	}
	public boolean isFull() {
		return lastIndex == SIZE;
	}
	public void add(T e) {
		if(!isFull()) nodes[++lastIndex] = e;
	}
	
	public void bfs() {
		if(isEmpty()) return;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		// 첫번째로 탐색할 대상 큐에 넣기
		queue.offer(1);
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			System.out.println(nodes[current]);
			
			if(current*2 <= lastIndex) queue.offer(current*2);
			if(current*2+1 <=lastIndex) queue.offer(current*2+1);
		}
	}
	
//	 public void bfs2() {
//		 
//		 isEmpty()
//		 
//	 }
	public void bfs2() {
		
	}
	
	public void bfs3() {
		if(isEmpty()) return;
		
		int breadth = 0;
		
		// 큐에다 너비를 저장하지 않고 breadth로 관리
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(1);
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			while(--size>=0) {
				int current = queue.poll();
				
				System.out.println(nodes[current]+"/"+breadth);
				
				if(current*2 <= lastIndex) queue.offer(current*2);
				if(current*2+1 <=lastIndex) queue.offer(current*2+1);
				
				
			}
			++breadth;
		}
		
	}
	
	// x 노드를 루트로하는 트리탐색 ! 깊이 우선 트리 탐색
	public void dfsByPreOrder(int current) {
		
//		if(current>lastIndex) return;
//		dfsByPreOrder(current*2);
//		dfsByPreOrder(current*2+1);

		// 현재 노드 탐색
		System.out.println(nodes[current]);
		
		if(current*2 <= lastIndex) dfsByPreOrder(current*2);
		if(current*2+1 <=lastIndex) dfsByPreOrder(current*2+1);
		
		// 현재 노드의 자식 노드 탐색
		
	}
	// 중위순회
	public void dfsByInOrder(int current) {
		
//		if(current>lastIndex) return;
//		dfsByPreOrder(current*2);
//		dfsByPreOrder(current*2+1);

		// 현재 노드 탐색
				
		if(current*2 <= lastIndex) dfsByPreOrder(current*2);
		System.out.println(nodes[current]);
		if(current*2+1 <=lastIndex) dfsByPreOrder(current*2+1);
		
		// 현재 노드의 자식 노드 탐색
		
	}
	
	// 후위순회
	public void dfsByPostOrder(int current) {
		
//		if(current>lastIndex) return;
//		dfsByPreOrder(current*2);
//		dfsByPreOrder(current*2+1);

		// 현재 노드 탐색
		
		if(current*2 <= lastIndex) dfsByPreOrder(current*2);
		if(current*2+1 <=lastIndex) dfsByPreOrder(current*2+1);
		System.out.println(nodes[current]);
		// 현재 노드의 자식 노드 탐색
		
	}
	
	
}
