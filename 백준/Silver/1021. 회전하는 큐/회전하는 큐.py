import sys
from collections import deque

input = sys.stdin.readline
N, M = map(int, input().split())

deque = deque(range(1, N+1))
arr = list(map(int, input().split()))
cnt = 0

for i in arr:
    while True:
        if deque[0] == i:
            deque.popleft()
            break
        else:
            idx = deque.index(i)
            if idx <= len(deque) // 2:
                deque.append(deque.popleft())
                cnt += 1
            else:
                deque.appendleft(deque.pop())
                cnt += 1
print(cnt)