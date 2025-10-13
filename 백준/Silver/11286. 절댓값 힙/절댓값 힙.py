import sys
import heapq

input = sys.stdin.readline
N = int(input())
heap = []

for _ in range(N):
    X = int(input())
    if X != 0:
        heapq.heappush(heap, (abs(X), X))
    else:
        if heap:
            print(heapq.heappop(heap)[1])
        else:
            print(0)

