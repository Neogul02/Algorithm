import sys

input = sys.stdin.readline
N, M = map(int, input().split())
arr = list(map(int, input().split()))

left, right = 0, max(arr)
answer = 0

while left <= right:
    mid = (left + right) // 2
    wood = sum(max(0, h - mid) for h in arr)
    if wood >= M:
        answer = mid
        left = mid + 1
    else:
        right = mid - 1

print(answer)
