package testPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class OhMyKamisama {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    static int startX, startY;
    static ArrayDeque<int[]> devilQueue;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine().trim());
        for(int tc = 1; tc <= T; tc++) {
            // 1. input()
            input();

            // 2. solve()
            solve(tc);

        }
        System.out.print(sb);
    }

    public static void solve(int tc){
        ArrayDeque<int[]> SuyeonQueue = new ArrayDeque<>();
        SuyeonQueue.add(new int[] {startX, startY}); // x좌표, y좌표, 이동 횟수
        visited[startX][startY] = true;

        int time =0;

        sb.append('#').append(tc).append(' ');

        while(SuyeonQueue.isEmpty()!= true){
            time ++;

            // 1. 악마 먼저 이동
            int devilSize = devilQueue.size();
            for(int i = 0; i < devilSize; i++) {
                int[] tempdevil = devilQueue.poll();
                int devilX = tempdevil[0];
                int devilY = tempdevil[1];

                // 4방향 탐색
                for(int d=0; d<4; d++){
                    int nx = devilX + dx[d];
                    int ny = devilY + dy[d];

                    if(nx<0 || ny<0 || nx>=N || ny>=M) continue;

                    if(map[nx][ny]=='D' || map[nx][ny]=='X' || map[nx][ny]=='*') continue;

                    map[nx][ny] = '*';
                    devilQueue.add(new int[] {nx, ny});

                }
            }

            // 2. 수연 이동
            int suyeonSize = SuyeonQueue.size();
            for(int i=0; i< suyeonSize; i++){
                int[] tempSuyeon = SuyeonQueue.poll();
                int suyeonX = tempSuyeon[0];
                int suyeonY = tempSuyeon[1];

                for(int d=0; d<4; d++){
                    int nx = suyeonX + dx[d];
                    int ny = suyeonY + dy[d];

                    if(nx<0 || ny<0 || nx>=N || ny>=M) continue; 

                    if(map[nx][ny] == 'D'){
                        sb.append(time).append('\n');
                        return;
                    }
                    
                    if(visited[nx][ny] == true) continue;

                    if(map[nx][ny] == '*' || map[nx][ny] =='X') continue;

                    visited[nx][ny] = true;
                    SuyeonQueue.add(new int[] {nx, ny});
                    
                }
            }
        }

        // 모든 위치 끝인데 return 안됨 = gameover 
        sb.append("GAME OVER").append('\n');
        return;
    }

    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine().trim(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];

        devilQueue = new ArrayDeque<>();

        for(int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);

                if(map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                } else if(map[i][j] == '*') {
                    devilQueue.add(new int[] {i, j});
                }
            }
        }
    }
}
    

