import java.util.Arrays;

class Solution {
    public int[] solution(int N, int[] stages) {
        double[] failPercent = new double[N];

        int [] players = new int[N];
        int [] noClearPlayers = new int[N];

        for(int i=0; i<N; i++){
            int player = 0;
            int noClearPlayer=0;
            for(int j=0; j<stages.length; j++){
                if(stages[j]>i){
                    // 해당 스테이지를 도전한 사람 수
                    player++;
                    if(stages[j]<=i+1){
                        // 해당 스테이지를 도전했는데, 클리어는 못한 사람 수
                        noClearPlayer++;
                    }
                }
                players[i] = player;
                noClearPlayers[i] = noClearPlayer;
                if(noClearPlayer == 0 ){
                    failPercent[i] = 0;
                }else{
                    failPercent[i] = (double) noClearPlayer /player;
                }
            }
        }
        int [] result = new int[N];
//        System.out.println(Arrays.toString(players));
//        System.out.println(Arrays.toString(noClearPlayers));
//        System.out.println(Arrays.toString(failPercent));
        for(int i =0; i<N; i++){
            double max = Arrays.stream(failPercent).max().getAsDouble();
            for(int j=0; j<N; j++){
                if(failPercent[j]==max){
                    result[i] = j+1;
                    failPercent[j] = -1;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(result));
        return result;
    }
}