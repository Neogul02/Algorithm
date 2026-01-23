import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};

        int[] person1 = {1, 2, 3, 4, 5};
        int[] person2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] person3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] score = new int[3];

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == person1[i % person1.length]) {
                score[0]++;
            }
            if (answers[i] == person2[i % person2.length]) {
                score[1]++;
            }
            if (answers[i] == person3[i % person3.length]) {
                score[2]++;
            }
        }
        int maxScore = Math.max(score[0], Math.max(score[1], score[2]));
        List<Integer> topScorers = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            if (score[i] == maxScore) {
                topScorers.add(i + 1);
            }
        }
        answer = new int[topScorers.size()];
        for (int i = 0; i < topScorers.size(); i++) {
            answer[i] = topScorers.get(i);
        }

        return answer;
    }
}