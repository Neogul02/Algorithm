package template;

public class DFS_map {
    static int N = 5, M = 5;
    static int[][] map = {
        {1, 1, 1, 1, 1},
        {1, 0, 0, 0, 1},
        {1, 1, 1, 0, 1},
        {1, 0, 1, 1, 1},
        {1, 1, 1, 1, 1}
    };
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    
    public static void dfs(int x, int y) {
        visited[x][y] = true;
        System.out.println("방문: (" + x + ", " + y + ")");
        
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if(visited[nx][ny]) continue;
            if(map[nx][ny] == 0) continue;
            
            dfs(nx, ny);
        }
    }
    
    public static void main(String[] args) {
        visited = new boolean[N][M];
        System.out.println("=== DFS 탐색 시작 ===");
        dfs(0, 0);
    }
}
