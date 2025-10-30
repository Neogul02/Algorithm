from collections import defaultdict

def solution(participant, completion):
    answer = defaultdict(int)
    
    for p in participant:
        answer[p] += 1
    
    for c in completion:
        answer[c] -= 1
        
    for p, c in answer.items():
        if c != 0:
            return p