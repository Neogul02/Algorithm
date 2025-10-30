from collections import defaultdict

def solution(nums):
    answer = defaultdict(int)    
    N = int(len(nums) // 2)
    for i in nums:
        answer[i] += 1
        
    if N >= len(answer.keys()):
        return len(answer.keys())
    if N < len(answer.keys()):
        return N