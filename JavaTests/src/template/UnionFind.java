package template;

public class UnionFind {
    static int[] parent;
    
    // 초기화
    public static void makeSet(int n) {
        parent = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }
    
    // Find (경로 압축)
    public static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    // Union
    public static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if(rootA == rootB) return false;
        
        parent[rootB] = rootA;
        return true;
    }
    
    public static void main(String[] args) {
        int n = 5;
        makeSet(n);
        
        System.out.println("1과 2 연결: " + union(1, 2));
        System.out.println("2와 3 연결: " + union(2, 3));
        System.out.println("4와 5 연결: " + union(4, 5));
        
        System.out.println("1과 3이 같은 그룹? " + (find(1) == find(3)));
        System.out.println("1과 4가 같은 그룹? " + (find(1) == find(4)));
        
        System.out.println("1과 5 연결: " + union(1, 5));
        System.out.println("1과 4가 같은 그룹? " + (find(1) == find(4)));
    }
}
