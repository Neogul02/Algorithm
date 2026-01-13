def max_same(grid):
    n = len(grid)
    ans = 1
    for i in range(n):
        cnt = 1
        for j in range(1, n):
            if grid[i][j] == grid[i][j-1]:
                cnt += 1
            else:
                if cnt > ans: ans = cnt
                cnt = 1
        if cnt > ans: ans = cnt
    for j in range(n):
        cnt = 1
        for i in range(1, n):
            if grid[i][j] == grid[i-1][j]:
                cnt += 1
            else:
                if cnt > ans: ans = cnt
                cnt = 1
        if cnt > ans: ans = cnt
    return ans

N = int(input().strip())
board = [list(input().strip()) for _ in range(N)]

result = 0
for i in range(N):
    for j in range(N):
        if j+1 < N and board[i][j] != board[i][j+1]:
            board[i][j], board[i][j+1] = board[i][j+1], board[i][j]
            cur = max_same(board)
            if cur > result: result = cur
            board[i][j], board[i][j+1] = board[i][j+1], board[i][j]
            
        if i+1 < N and board[i][j] != board[i+1][j]:
            board[i][j], board[i+1][j] = board[i+1][j], board[i][j]
            cur = max_same(board)
            if cur > result: result = cur
            board[i][j], board[i+1][j] = board[i+1][j], board[i][j]

print(result)
