import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        st= new StringTokenizer(br.readLine().trim());
        int F = Integer.parseInt(st.nextToken()); // F층으로 이루어진 건물
        int S = Integer.parseInt(st.nextToken()); // 강호가 현재 있는 곳
        int G = Integer.parseInt(st.nextToken()); // 목표 층 수

        int U = Integer.parseInt(st.nextToken()); // U 만큼 위로 가는 버튼
        int D = Integer.parseInt(st.nextToken()); // D 만큼 아래로 가는 버튼

        if(S==G) {
            System.out.print(0);
            return;
        }

        boolean[] visited = new boolean[F+1];
        int[] dist = new int[F+1];

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(S);
        visited[S] = true;

        while(queue.isEmpty()!=true){
            int temp = queue.poll();

            int up = temp + U;
            if(up<=F && visited[up]==false){
                visited[up] = true;
                dist[up] = dist[temp] + 1;
                if(up==G){
                    System.out.print(dist[up]);
                    return;
                }
                queue.add(up);
            }

            int down = temp - D;
            if(down>=1 && visited[down]==false){
                visited[down] = true;
                dist[down] = dist[temp] + 1;
                if(down == G){
                    System.out.print(dist[down]);
                    return;
                }
                queue.add(down);
            }
        }
        System.out.println("use the stairs");
    }
    
}
