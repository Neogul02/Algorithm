class Solution {
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        int t = bandage[0]; // 시전 시간 <- 이 시간만큼 성공하면 + y라는거임
        int x = bandage[1]; // 초당 회복량 <- 이건 1초마다 되는거
        int y = bandage[2]; // 추가 회복량

        int userHealth = health; // 유저 체력 초기화
        int combo = 0; // 콤보 초기화
        int idx = 0;
        int maxTime = attacks[attacks.length - 1][0]; // 공격이 들어오는 마지막 시간 + 1초

        for (int time = 0; time <= maxTime; time++) { // 0 초부터 시뮬레이션 시작
            // 1. 공격이 들어오는 시간인지 체크
            if (time == attacks[idx][0]) {
                userHealth -= attacks[idx][1]; // 공격 받음
                idx++; // 다음 공격으로 이동
                combo = 0; // 공격 받으면 콤보 초기화
                
                // 맞아서 디1짐
                if (userHealth <= 0) {
                    return -1;
                }
                continue;
            }

            // 2. 붕대 감기
            userHealth += x; // 초당 회복량만큼 회복
            combo++; // 콤보 증가

            if (combo == t) { // t만큼 콤보면
                userHealth += y;
                combo = 0; // 콤보 초기화
            }

            // 최대 체력을 넘을 수 없음
            if (userHealth > health) {
                userHealth = health;
            }
            

        }
        // 시뮬레이션이 끝났는데 살아남음
        return userHealth;
    }
}