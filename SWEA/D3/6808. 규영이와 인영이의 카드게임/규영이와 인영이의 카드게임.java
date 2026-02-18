import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 6808. 규영이와 인영이의 카드게임
 *
 * 18장의 카드로 게임을 함
 * 규영이와 인영이는 각각 9장씩 카드를 가짐
 * 높은 수가 적힌 카드를 낸 사람은 두 카드에 적힌 수의 합만큼 점수를 얻고,
 * 낮은 수가 적힌 카드를 낸 사람은 아무 점수도 얻지 못함
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] gyuCards; // 규영이 카드
    static int[] inCards; // 인영이 카드

    static int gyuWinCount; // 규영이 승리 횟수
    static int inWinCount; // 인영이 승리 횟수

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for(int tc=1; tc<=T; tc++) {
            input();
            solve();

            sb.append("#").append(tc).append(" ")
                    .append(gyuWinCount).append(" ").append(inWinCount).append("\n");
        }
        System.out.print(sb);
    }

    private static void solve() {
        gyuWinCount = 0;
        inWinCount = 0;

        boolean[] isSelected = new boolean[9]; // 인영이 카드 선택 여부
        int[] permutation = new int[9]; // 인영이 카드 순열 저장 배열
        // 인영이 카드의 모든 순열을 생성하여 규영이 카드와 비교
        permuteInCards(0, isSelected, permutation);
    }

    private static void permuteInCards(int depth, boolean[] isSelected, int[] permutation) {
        if(depth == 9){
            playGame(permutation);
            return;
        }
        for(int i=0; i<9; i++) {
            if(!isSelected[i]) {
                isSelected[i] = true;
                permutation[depth] = inCards[i];
                permuteInCards(depth+1, isSelected, permutation);
                isSelected[i] = false;
            }
        }
    }

    private static void playGame(int[] permutation) {

        int tempGyuScore = 0;
        int tempInScore = 0;

        for(int round=0; round<9; round++) {
            if(gyuCards[round] > permutation[round]) {
                tempGyuScore += gyuCards[round] + permutation[round];
            } else if (gyuCards[round] < permutation[round]) {
                tempInScore += gyuCards[round] + permutation[round];
            }
        }
        if(tempGyuScore > tempInScore) {
            gyuWinCount++;
        } else if (tempGyuScore < tempInScore) {
            inWinCount++;
        }
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim()," ");
        int[] isSelected = new int[19]; // 1~18 카드 중 선택된 카드 표시
        gyuCards = new int[9];
        inCards = new int[9];

        for(int i=0; i<9; i++) {
            gyuCards[i] = Integer.parseInt(st.nextToken());
            isSelected[gyuCards[i]] = 1; // 규영이가 선택한 카드 표시
            // 1 3 5 7 9 11 13 15 17
        }
        int idx = 0;
        for(int i=1; i<=18; i++) {
            // 남은 카드는 인영이거
            if(isSelected[i] == 0) {
                inCards[idx] = i; // 인영이 카드에 추가
                idx++;
            }
        }
//        System.out.println("규영이 카드: " + Arrays.toString(gyuCards));
//        System.out.println("인영이 카드: " + Arrays.toString(inCards));
    }
}
