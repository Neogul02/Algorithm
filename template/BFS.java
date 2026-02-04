package template;

import java.util.*;

public class BFS {
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
    
    public static void bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[N][M];
        
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            
            System.out.println("방문: (" + x + ", " + y + ")");
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                // 범위 체크
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                // 방문 체크
                if(visited[nx][ny]) continue;
                // 벽 체크
                if(map[nx][ny] == 0) continue;
                
                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny});
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== BFS 탐색 시작 ===");
        bfs(0, 0);
    }
}