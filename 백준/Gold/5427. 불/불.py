import sys
from collections import deque

input = sys.stdin.readline

# 5427. 불
# @author neogul02
#
# - '.' : 빈 공간, '#' : 벽, '@' : 상근이의 시작 위치, '*' : 불의 시작 위치
# - 불이 4방향으로 이동, 상근이도 4방향으로 이동, 불이 먼저 이동
# - 상근이가 탈출할 수 있는 빠른 시간, 탈출할 수 없는 경우 IMPOSSIBLE

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def bfs(start_x, start_y, grid, visited, fire_queue, H, W):
    queue = deque()
    queue.append((start_x, start_y))  # 상근이 초기 위치
    visited[start_x][start_y] = True  # 방문 처리

    time = 0  # 걸린 시간

    while queue:
        time += 1

        # 1. 불 먼저 이동
        fire_size = len(fire_queue)
        for _ in range(fire_size):
            fire_x, fire_y = fire_queue.popleft()

            for d in range(4):
                next_fire_x = fire_x + dx[d]
                next_fire_y = fire_y + dy[d]

                # 아웃바운드면 pass
                if next_fire_x < 0 or next_fire_x >= H or next_fire_y < 0 or next_fire_y >= W:
                    continue

                # 벽이거나 이미 불이 있으면 pass
                if grid[next_fire_x][next_fire_y] == '#' or grid[next_fire_x][next_fire_y] == '*':
                    continue

                # 불이 이동할 수 있는 곳이면 불 확산
                grid[next_fire_x][next_fire_y] = '*'
                fire_queue.append((next_fire_x, next_fire_y))

        # 2. 상근이 이동
        size = len(queue)
        for _ in range(size):
            temp_x, temp_y = queue.popleft()

            # 상근이가 이동할 수 있는 4방향 탐색
            for d in range(4):
                next_x = temp_x + dx[d]
                next_y = temp_y + dy[d]

                # 아웃바운드면 탈출 성공
                if next_x < 0 or next_x >= H or next_y < 0 or next_y >= W:
                    return time

                # 방문한 곳이면 pass
                if visited[next_x][next_y]:
                    continue

                # 벽이거나 불이면 pass
                if grid[next_x][next_y] == '#' or grid[next_x][next_y] == '*':
                    continue

                # 상근이가 이동할 수 있는 곳이면 탐색
                visited[next_x][next_y] = True
                queue.append((next_x, next_y))

    # 상근이가 움직일 수 있는 수를 다 돌았는데 탈출 return이 안됐다?
    return "IMPOSSIBLE"


def solve():
    W, H = map(int, input().split())  # 너비, 높이

    grid = []
    visited = [[False] * W for _ in range(H)]
    fire_queue = deque()
    start_x, start_y = 0, 0

    for i in range(H):
        row = list(input().strip())
        grid.append(row)

        for j in range(W):
            # 시작 위치 찾기
            if grid[i][j] == '@':
                start_x, start_y = i, j

            if grid[i][j] == '*':
                fire_queue.append((i, j))

    result = bfs(start_x, start_y, grid, visited, fire_queue, H, W)
    print(result)


T = int(input())
for _ in range(T):
    solve()