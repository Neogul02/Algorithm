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
// 조합으로 푼 버전
public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N, L;
    static int[] score, cal;
    // score : [100, 300, 250, 500, 400]
    // cal :[200, 500, 300, 1000, 400]
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
            for (int r = 1; r <= N; r++) {
                findCombination(0, 0, r, 0, 0);
            }

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

    public static void findCombination(int cnt, int start, int targetR, int currentScore, int currentCal) {
        // 1. 가지치기: 이미 칼로리 넘었으면 더 볼 필요 없음 (효율성 증가)
        if (currentCal > L) return;

        // 2. 기저조건: 목표한 개수(targetR)만큼 다 뽑았을 때
        if (cnt == targetR) {
            maxScore = Math.max(maxScore, currentScore);
            return;
        }

        for (int i = start; i < N; i++) {
            // i번째 재료를 뽑고 다음 재귀로 (i+1을 전달해서 중복 방지)
            findCombination(cnt + 1, i + 1, targetR, currentScore + score[i], currentCal + cal[i]);
        }
    }
}