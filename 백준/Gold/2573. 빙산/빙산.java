import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


/**
 * 2573. 빙산
 * @author neogul02
 * 
 * @output 두 덩어리 이상으로 빙산들이 분리될때 걸린 year 수, 없으면 0
 * 
 * @Logic
 * 1. 가로길이 N이랑 세로길이M 을 입력받아 빙산전체 map을 저장하고 시작. @see
 * 2. year 변수를 0부터 선언하고 년도를 1씩 증가시키면서 bfs 시뮬레이션, 
 *  2-1. 숫자인칸은 전부 방문처리
 *  2-2. bfs가 돈 숫자만큼 = 덩어리
 * 3. 덩어리갯수 >= 2 return year
 * 4. 덩어리갯수 == 0 return 0
 */
class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[][] map;

    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        input();
        solve();
        System.out.print(sb);
    }

    public static void solve(){
        // 1. year 변수를 선언하고 각 년도가 지나는 경우를 시뮬레이션
        int year = 1;

        // 덩어리가 0일때까지 loop 돌기
        while(true){

            int bingSanCnt = 0;
            visited = new boolean[N][M];

            // 빙산 녹이기
            melt();

            // 모든 칸을 순회하면서 덩어리가 몇개인지 찾기
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[i][j]!=0 && visited[i][j]==false){
                        bfs(i, j);
                        bingSanCnt++;
                    }
                }
            }

            // 덩어리가 2개 이상이면 return
            if(bingSanCnt>= 2){
                sb.append(year);
                return;
            }

            // // 덩어리가 하나도 없으면 0 출력 하고 return
            if(bingSanCnt == 0){
                sb.append(0);
                return;
            }
            year ++;
        }
        

    }

    public static void melt(){
        // 맵 복사
        int[][] meltMap = new int[N][M];

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                meltMap[i][j] = map[i][j];
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                // 수정해야할 meltMap의 각 칸을 봐야함, 0이 아닌칸의 주변 4칸에 0이 몇개인지?
                if(map[i][j]!=0){
                    int tempBingSan = map[i][j];
                    int minusValue = 0;

                    for(int d=0; d<4; d++){
                        int nx = dx[d] + i;
                        int ny = dy[d] + j;

                        if(nx<0 || ny<0 || nx>=N || ny>=M) continue;

                        if(map[nx][ny]==0) minusValue++;
                    }

                    meltMap[i][j] = Math.max(tempBingSan-minusValue,0);

                }
                
            }
        }

        // 다시 원본 Map에 적용
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = meltMap[i][j];
            }
        }

    }

    public static void bfs(int i, int j){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j}); // 시작 점
        visited[i][j] = true;

        while(queue.isEmpty() != true){
            int[] temp = queue.poll();
            int tempX = temp[0];
            int tempY = temp[1];

            for(int d=0; d<4; d++){
                int nx = dx[d] + tempX;
                int ny = dy[d] + tempY;

                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;

                if(visited[nx][ny] == true) continue;

                if(map[nx][ny] != 0){
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
                
            }

        }
    }

    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}