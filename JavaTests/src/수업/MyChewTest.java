package 수업;
import java.util.ArrayDeque;
import java.util.Queue;

public class MyChewTest {
	public static void main(String[] args) {
		
		int N = 20;
		int person = 1;
		
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {person, 1});
		
		while(N>0) {
			int[] p = queue.poll();
			int availCnt = (N>=p[1])?p[1]:N;
			N -= availCnt;
			if(N==0) {
				System.out.println(p[0]+"번 사람이 마지막 마이쮸를 가져갑니다 가져간 개수 : "+ availCnt);
				
			}else {
				System.out.println(p[0]+"번 사람이 마이쮸를 가져갑니다 가져간 개수 : "+ availCnt);
				++p[1];
				queue.offer(p);
				queue.offer(new int[] {++person, 1});
			}
		}
	}

}
