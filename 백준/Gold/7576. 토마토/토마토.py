import sys
from collections import deque

input = sys.stdin.readline
M, N = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]

dq = deque()
for i in range(N):
    for j in range(M):
        if board[i][j] == 1:
            dq.append((i, j))

dirs = ((1, 0), (-1, 0), (0, 1), (0, -1))
while dq:
    x, y = dq.popleft()
    for dx, dy in dirs:
        nx, ny = x + dx, y + dy
        if 0 <= nx < N and 0 <= ny < M and board[nx][ny] == 0:
            board[nx][ny] = board[x][y] + 1
            dq.append((nx, ny))

max_day = 0
for row in board:
    for v in row:
        if v == 0:
            print(-1)
            sys.exit()
        if v > max_day:
            max_day = v

print(max_day - 1)