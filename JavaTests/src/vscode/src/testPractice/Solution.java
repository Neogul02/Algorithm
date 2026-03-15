package testPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N; // 고객의 수

    static int[] company;
    static int[] home;

    static int[][] customers;

    static boolean [] visited;

    static int minDistance;

    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            input();

            permutationDFS(0, company, 0);

            sb.append('#').append(tc).append(' ')
                    .append(minDistance).append('\n');
            
        }
        System.out.print(sb);

    }
    
    public static void permutationDFS(int depth, int[] current , int distance) {
        // 가지치기
        if(distance > minDistance) return;

        // 기저조건 depth가 N 이 되면 종료하고 최소값 갱신
        if (depth == N) {
            int tempDistance = distance + manhattan(current[0], current[1], home[0], home[1]);
            minDistance = Math.min(minDistance, tempDistance);
            return;
        }

        // 수행 로직
        for (int i = 0; i < N; i++) {
            if (visited[i] == true)
                continue;

            visited[i] = true;

            int newDistance = distance + manhattan(current[0], current[1], customers[i][0], customers[i][1]);
            permutationDFS(depth + 1, customers[i], newDistance);
            
            visited[i] = false; // backTracking    
        }
    }
    
    // 두 점간의 거리 구하기, 맨해튼 거리
    public static int manhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    public static void input() throws IOException {
        // Line 1: people count
        N = Integer.parseInt(br.readLine().trim());

        // Line 2: companyX, companyY, homeX, homeY, + N x
        st = new StringTokenizer(br.readLine().trim(), " ");
        company = new int[2];
        home = new int[2];

        company[0] = Integer.parseInt(st.nextToken());
        company[1] = Integer.parseInt(st.nextToken());
        home[0] = Integer.parseInt(st.nextToken());
        home[1] = Integer.parseInt(st.nextToken());

        // 고객수 만큼의 x, y 좌표
        customers = new int[N][2];
        for (int i = 0; i < N; i++) {
            customers[i][0] = Integer.parseInt(st.nextToken());
            customers[i][1] = Integer.parseInt(st.nextToken());
        }

        // Init
        minDistance = Integer.MAX_VALUE;
        visited = new boolean[N];

    }
}
