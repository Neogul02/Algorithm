import sys
import heapq

input = sys.stdin.readline
N = int(input())
arr = []

for _ in range(N):
    X = int(input())
    if X > 0:
        heapq.heappush(arr, X)
    elif X == 0:
        if arr:
            print(heapq.heappop(arr))
        else:
            print(0)
