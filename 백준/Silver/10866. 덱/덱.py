import sys
from collections import deque

input = sys.stdin.readline
N = int(input())
dq = deque()
result = []

for _ in range(N):
    cmd = input().strip().split()
    if cmd[0] == 'push_front':
        dq.appendleft(int(cmd[1]))
    elif cmd[0] == 'push_back':
        dq.append(int(cmd[1]))
    elif cmd[0] == 'pop_front':
        result.append(str(dq.popleft()) if dq else '-1')
    elif cmd[0] == 'pop_back':
        result.append(str(dq.pop()) if dq else '-1')
    elif cmd[0] == 'size':
        result.append(str(len(dq)))
    elif cmd[0] == 'empty':
        result.append('1' if not dq else '0')
    elif cmd[0] == 'front':
        result.append(str(dq[0]) if dq else '-1')
    elif cmd[0] == 'back':
        result.append(str(dq[-1]) if dq else '-1')

print('\n'.join(result))
