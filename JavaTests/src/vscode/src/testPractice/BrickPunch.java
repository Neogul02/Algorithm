package testPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;



public class BrickPunch {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();    

    static int N, W, H;
    static int[][] map;
    static int[][] copyMap;

    static int[] selected;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int answer;


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for(int tc =1; tc<=T; tc++){
            // 1. input()
            input();

            // 2. solve()
            solve(tc);
        }
        System.out.print(sb);
    }

    public static void solve(int tc){
        sb.append('#').append(tc).append(' ');

        // 0. init
        answer = Integer.MAX_VALUE;

        // 1. 구슬을 떨구는 경우의수, 같은 열에 N번 떨굴수도 있음 =중복 가능 순열 
        

        permutation(0);
        
        sb.append(answer).append('\n');
       
    }

    public static void permutation(int depth){
        if(depth == N){
            // 1. 중복 순열이 N자리 만큼 완성되면 시뮬레이션 시작
            simulation();
            return;
        }

        for(int i=0; i<W; i++){
            selected[depth] = i;
            permutation(depth+1);
        }

    }

    public static void simulation(){
        if(answer == 0) return; // 남은 벽돌이 0개면 더 이상 시뮬레이션 돌릴 필요 없음

        // 맵 복사
        copyMap = new int[H][W];
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                copyMap[i][j] = map[i][j];
            }
        }
        

        // 1. 구슬을 떨구는 시뮬레이션
        for(int i=0; i<N; i++){
            int col = selected[i]; // 구슬을 떨어뜨릴 열

            // 2. 해당 열에서 가장 위에 있는 벽돌 찾기
            for(int row=0; row<H; row++){
                if(copyMap[row][col] != 0){
                    // 3. 벽돌이 존재하는 경우, 벽돌을 깨뜨리고 주변 벽돌도 함께 깨뜨리는 시뮬레이션
                    breakBricks(row, col);
                    // 4. 벽돌이 깨진 후, 남은 벽돌을 아래로 떨어뜨리기
                    dropBricks();
                    break; // 다음 구슬로 넘어가기
                }
            }
        }

        int minBrickCnt = 0;
        // 남은 벽돌 개수 세기
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                if(copyMap[i][j] != 0){
                    minBrickCnt++;
                }
            }
        }
        // 최소값 갱신
        answer = Math.min(answer, minBrickCnt);
    }

    public static void breakBricks(int row, int col){
        ArrayDeque <int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {row, col, copyMap[row][col]}); // 벽돌의 위치와 벽돌의 숫자
        copyMap[row][col] = 0; // 벽돌 깨뜨리기

        while(queue.isEmpty() != true){
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];
            int power = temp[2];

            // 벽돌의 숫자만큼 4방향으로 깨뜨리는 시뮬레이션
            for(int d=0; d<4; d++){
                for(int p=1; p<power; p++){
                    int nx = x + dx[d]*p;
                    int ny = y + dy[d]*p;

                    if(nx<0 || ny<0 || nx>=H || ny>=W) continue;

                    if(copyMap[nx][ny] == 0) continue;

                    queue.add(new int[] {nx, ny, copyMap[nx][ny]});
                    copyMap[nx][ny] = 0; // 벽돌 깨뜨리기
                }
            }
        }
    }
    
    public static void dropBricks(){

        for(int y=0; y<W; y++){
            for(int x=H-1; x>=0; x--){

                if(copyMap[x][y] == 0){
                    for(int k=x-1; k>=0; k--){
                        if(copyMap[k][y] != 0){
                            copyMap[x][y] = copyMap[k][y];
                            copyMap[k][y] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken()); // 구슬 떨구는 횟수

        W = Integer.parseInt(st.nextToken()); // WIDTH
        H = Integer.parseInt(st.nextToken()); // HEIGHT

        selected = new int[N];

        map = new int[H][W];
        for(int i=0; i<H; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j=0; j<W; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
