from collections import defaultdict

N = int(input())
arr = list(map(int, input().split()))
count = defaultdict(int)
left = 0
answer = 0

for right in range(N):
    count[arr[right]] += 1
    while len(count) > 2:
        count[arr[left]] -= 1
        if count[arr[left]] == 0:
            del count[arr[left]]
        left += 1
    answer = max(answer, right - left + 1)

print(answer)