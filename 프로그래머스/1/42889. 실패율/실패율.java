import java.util.HashMap;

class Solution {
    public static int[] solution(int N, int[] stages) {

        int [] challenger = new int[N + 2];
        for(int stage: stages){
            challenger[stage]++;
        }
        HashMap<Integer, Double> fails = new HashMap<>();
        double total = stages.length;

        for(int i = 1; i <= N; i++) {
            if(challenger[i] == 0) {
                fails.put(i, 0.0);
            } else {
                fails.put(i, challenger[i] / total);
                total -= challenger[i];
            }
        }
        return fails.entrySet().stream()
            .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
            .mapToInt(e -> e.getKey())
            .toArray();
    }
}