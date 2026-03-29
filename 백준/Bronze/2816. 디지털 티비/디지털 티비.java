import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 1. 화살표를 한 칸 아래로하면 i에서 i+1로 이동
 * 2. 화살표를 한 칸 위로하면 i에서 i-1로 이동
 * 3. 화살표를 오른쪽으로 하면 i와 i+1의 값을 서로 바꾼다. 
 * 4. 화살표를 왼쪽으로 하면 i에서 i-1의 값을 서로 바꾼다.
 * + 범위 넘어가면 무시, 
 */
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine().trim());
        ArrayList<String> channels = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            channels.add(br.readLine().trim());
        }

        int cursor = 0;

        int kbs1Idx = channels.indexOf("KBS1");
        while (cursor < kbs1Idx) {
            sb.append('1');
            cursor++;
        }

        while (kbs1Idx > 0) {
            swap(channels, kbs1Idx, kbs1Idx - 1);
            sb.append('4');
            kbs1Idx--;
            cursor--;
        }

        int kbs2Idx = channels.indexOf("KBS2");
        while (cursor < kbs2Idx) {
            sb.append('1');
            cursor++;
        }

        while (kbs2Idx > 1) {
            swap(channels, kbs2Idx, kbs2Idx - 1);
            sb.append('4');
            kbs2Idx--;
            cursor--;
        }

        System.out.print(sb);
    }

    private static void swap(ArrayList<String> channels, int i, int j) {
        String temp = channels.get(i);
        channels.set(i, channels.get(j));
        channels.set(j, temp);
    }
}