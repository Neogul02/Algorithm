import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().split())
arr = [input().split() for _ in range(n)]
visited = [[False] * m for _ in range(n)]
ans = [[0] * m for _ in range(n)]
directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

# 시작점
q = deque((i, j) for i in range(n) for j in range(m) if arr[i][j] == "2")
for x, y in q:
    visited[x][y] = True

# BFS
while q:
    x, y = q.popleft()
    for dx, dy in directions:
        nx, ny = x + dx, y + dy
        if 0 <= nx < n and 0 <= ny < m and arr[nx][ny] == "1" and not visited[nx][ny]:
            visited[nx][ny] = True
            ans[nx][ny] = ans[x][y] + 1
            q.append((nx, ny))

# 결과 출력
for i in range(n):
    for j in range(m):
        print(-1 if arr[i][j] == "1" and not visited[i][j] else ans[i][j], end=" ")
    print()