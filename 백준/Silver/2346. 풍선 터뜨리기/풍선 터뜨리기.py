import sys
input = sys.stdin.readline

N = int(input())
arr = list(map(int, input().split()))

balloons = [(i+1, arr[i]) for i in range(N)]
result = []
current = 0 

for _ in range(N):
    balloon_num, paper_value = balloons.pop(current)
    result.append(balloon_num)
    
    if not balloons:
        break
    
    if paper_value > 0:
        current = (current + paper_value - 1) % len(balloons)
    else:
        current = (current + paper_value) % len(balloons)

print(*result)