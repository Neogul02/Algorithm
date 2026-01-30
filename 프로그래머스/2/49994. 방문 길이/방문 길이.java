import java.util.HashMap;
import java.util.HashSet;
class Solution {
    public int solution(String dirs) {
        HashMap<String, Integer[]> UDRL = new HashMap<>();
        UDRL.put("U", new Integer[]{0, 1});
        UDRL.put("D", new Integer[]{0, -1});
        UDRL.put("R", new Integer[]{1, 0});
        UDRL.put("L", new Integer[]{-1, 0});

        // 5*5 격자 0-10
        boolean[][] visited = new boolean[11][11];
        int x = 5;
        int y = 5;
        HashSet<String> answer = new HashSet<>();

        for (int i = 0; i < dirs.length(); i++) {
            String dir = String.valueOf(dirs.charAt(i));
            int nextX = x + UDRL.get(dir)[0];
            int nextY = y + UDRL.get(dir)[1];

            // 범위 체크
            if (nextX < 0 || nextX > 10 || nextY < 0 || nextY > 10) {
                continue;
            }
            answer.add(x + "," + y + "," + nextX + "," + nextY);
            answer.add(nextX + "," + nextY + "," + x + "," + y);
            
            x = nextX;
            y = nextY;
        }
        return answer.size()/2;
        
    }
}