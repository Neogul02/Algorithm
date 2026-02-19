package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/**
 * @author neogul02
 * 민기는 햄버거를 좋아한다.
 *
 * [입력]
 * 1. 테스트 케이스의 수 T를 입력받는다.
 * 2. 각 테스트 케이스마다 재료의 수 N과 제한 칼로리 L을 입력받는다.
 * 3. N 개의 줄만큼 반복한다.
 *  3-1. 맛 점수와 칼로리를 입력받는다.
 * 4. 부분집합(Subset) 재귀 알고리즘을 사용한다.
 *  4-1. 각 재료마다 "선택" 또는 "미선택" 두 가지 경우를 재귀적으로 탐색한다.
 *  4-2. 모든 부분집합(2^N개)을 확인하여 제한 칼로리 이하인 조합을 찾는다.
 *  4-3. 조건을 만족하는 조합 중 최대 맛 점수를 찾아 출력한다.
 *
 * [출력]
 * 5. 제한 칼로리 이하의 조합 중 최고의 맛 점수를 출력한다.
 */
public class 햄버거다이어트5125_부분집합 {
 
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
 
    static int N, L;
    static int[] score, cal;
    // score : [100, 300, 250, 500, 400], cal :[200, 500, 300, 1000, 400]
    static int maxScore;
 
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
 
        // 1. 테스트 케이스의 수 T를 입력받는다.
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            inputTestCase();
 
            // 초기화
            maxScore = 0;
            findSubset(0, 0, 0);
 
            sb.append(String.format("#%d %d\n", tc, maxScore));
        }
        System.out.print(sb);
    }
 
    public static void inputTestCase() throws IOException {
        // 2. 각 테스트 케이스마다 재료의 수 N과 제한 칼로리 L을 입력받는다.
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
 
            score = new int[N];
            cal = new int[N];
 
            // 3. N 개의 줄만큼 반복한다.
            for(int i=0; i<N; i++) {
                //  3-1. 맛 점수와 칼로리를 입력받는다.
                st = new StringTokenizer(br.readLine());
                score[i] = Integer.parseInt(st.nextToken());
                cal[i] = Integer.parseInt(st.nextToken());
            }
    }
 
    public static void findSubset(int index, int currentScore, int currentCal) {
        // 기저조건: 모든 재료를 확인했을 때
        if(index == N) {
            // 4-2. 모든 부분집합(2^N개)을 확인하여 제한 칼로리 이하인 조합을 찾는다.
            if(currentCal <= L) {
                maxScore = Math.max(maxScore, currentScore);
            }
            return;
        }
 
        // 4-1. 각 재료마다 "선택" 또는 "미선택" 두 가지 경우를 재귀적으로 탐색한다.
        // 현재 재료를 선택하는 경우
        findSubset(index + 1, currentScore + score[index], currentCal + cal[index]);
 
        // 현재 재료를 선택하지 않는 경우
        findSubset(index + 1, currentScore, currentCal);
    }
}