package MST;
/**
 * 서로소 집합 (Disjoint Set) 이란?
 * 서로소 집합은 서로 겹치지 않는 집합들의 모임을 나타내는 자료구조
 * {1,2,3} 이랑 {4,5} 는 서로소 집합
 */
public class DisJointSetTree {
    static int[] parent;

    public static void main(String[] args) {
        init(5);
        union(1, 2);
        union(3, 4);
        union(2, 3);
        System.out.println(find(1)==find(2));
        System.out.println(find(4)==find(5)); // Output: 1 
    }

    // 자기 자신이 부모로 초기화
    static void init(int n) {
        parent = new int[n + 1]; // 1-based Array
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }
    
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }
}
