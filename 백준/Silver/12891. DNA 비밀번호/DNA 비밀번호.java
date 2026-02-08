import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 12891. DNA비밀번호
 * @author neogul02
 * 
 * {'A','C','G','T'}
 * 1. 전체길이 S와 비밀번호 길의 P를 입력받는다
 * 2. 원본 DNA 문자열을 입력받고, 각 최소조건을 입력받는다. 
 * 3. 투 포인터를 사용해서 0번째 인덱스부터 비밀번호길이의 인덱스만큼 범위를 지정해서 검사한다.
 */
public class Main {
    static int getIdx(char c) {
        switch(c) {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken()); // 전체 길이 S
        int P = Integer.parseInt(st.nextToken()); // 비밀번호(윈도우) 길이 P
        
        char[] dnaStr = br.readLine().toCharArray();
        
        // 최소 조건 (A, C, G, T)
        int[] minArr = new int[4];
        st = new StringTokenizer(br.readLine());
        
        int checkSecret = 0; // 조건을 몇 개나 만족했는지 체크
        
        for(int i=0; i<4; i++) {
            minArr[i] = Integer.parseInt(st.nextToken());
            if(minArr[i] == 0) checkSecret++;
        }

        // 현재 내 윈도우
        int[] myArr = new int[4];
        int ans = 0;

        // 처음 P개 (0 ~ P-1)만 먼저 윈도우에 넣기
        for(int i=0; i<P; i++) {
            int idx = getIdx(dnaStr[i]); // 문자 -> 숫자 변환
            myArr[idx]++; // 개수 증가
            
            // 개수가 조건과 같아지는 순간
            if(myArr[idx] == minArr[idx]) checkSecret++;
        }
        
        // 초기 윈도우가 조건 만족하면 정답 1 증가
        if(checkSecret == 4) ans++;
        
        // 투 포인터 슬라이딩 한 칸씩 옆으로 밀기
        // right: 새로 들어오는 애 (P부터 끝까지)
        // left : 빠져나가는 애 (i - P)
        for(int right=P; right<S; right++) {
            int left = right - P; // 윈도우의 맨 왼쪽 끝

            // 오른쪽 추가 (+)
            int addIdx = getIdx(dnaStr[right]);
            myArr[addIdx]++;
            if(myArr[addIdx] == minArr[addIdx]) checkSecret++;

            // 왼쪽 제거 (-)
            int removeIdx = getIdx(dnaStr[left]);
            if(myArr[removeIdx] == minArr[removeIdx]) checkSecret--;
            myArr[removeIdx]--;

            // 현재 상태 검사
            if(checkSecret == 4) ans++;
        }
        System.out.println(ans);
    }
}