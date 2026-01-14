from collections import deque

T = int(input().strip())

dirs = [(-1,-1),(-1,0),(-1,1),(0,-1),(0,1),(1,-1),(1,0),(1,1)]

for tc in range(1, T+1):
    n_line = input().strip()
    while n_line == "":
        n_line = input().strip()
    N = int(n_line)
    grid = [list(input().strip()) for _ in range(N)]

    adj = [[0]*N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if grid[i][j] == '*':
                adj[i][j] = -1
                continue
            cnt = 0
            for dx, dy in dirs:
                ni, nj = i+dx, j+dy
                if 0 <= ni < N and 0 <= nj < N and grid[ni][nj] == '*':
                    cnt += 1
            adj[i][j] = cnt

    visited = [[False]*N for _ in range(N)]
    clicks = 0

    for i in range(N):
        for j in range(N):
            if grid[i][j] == '.' and adj[i][j] == 0 and not visited[i][j]:
                clicks += 1
                dq = deque()
                visited[i][j] = True
                dq.append((i,j))
                while dq:
                    x,y = dq.popleft()
                    for dx, dy in dirs:
                        nx, ny = x+dx, y+dy
                        if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny] and grid[nx][ny] == '.':
                            visited[nx][ny] = True
                            # 인접 지뢰 수가 0이면 연쇄로 확장
                            if adj[nx][ny] == 0:
                                dq.append((nx,ny))

    for i in range(N):
        for j in range(N):
            if grid[i][j] == '.' and not visited[i][j]:
                clicks += 1

    print(f"#{tc} {clicks}")