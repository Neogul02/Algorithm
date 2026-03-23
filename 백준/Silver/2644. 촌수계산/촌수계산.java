
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N; // 전체 사람 수
    static int M;

    static int peopleA, peopleB;

    static boolean[] visited;

    static int ans;

    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException{

        input();

        ans = -1;

        dfs(0, peopleA);

        System.out.print(ans);

    }

    public static void dfs(int depth, int temp){
        if(temp == peopleB){
            ans = depth;
            return;
        }

        visited[temp] = true;

        for(int next: graph.get(temp)){
            if(visited[next] == true) continue;

            dfs(depth+1, next);
        }
        
    }

    public static void input() throws IOException{
        // 전체 사람 수 N
        N = Integer.parseInt(br.readLine().trim());

        visited = new boolean[N+1];

        // 촌수 계산해야하는 두 사람의 번호
        st = new StringTokenizer(br.readLine().trim());
        peopleA = Integer.parseInt(st.nextToken());
        peopleB = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine().trim());

        graph = new ArrayList<>();
        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i =1; i<M+1; i++){
            st= new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }
}