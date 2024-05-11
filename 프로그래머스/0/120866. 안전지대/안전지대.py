def solution(board):
    n = len(board)
    m = len(board[0])
    answer = 0

    # 주변 8칸을 확인하면서 1이 아닌 부분을 만나면 안전지대로 처리
    for i in range(n):
        for j in range(m):
            if board[i][j] == 1:
                for dx in [-1, 0, 1]:
                    for dy in [-1, 0, 1]:
                        if dx == 0 and dy == 0:  # 현재 위치일 경우 건너뜀
                            continue
                        nx, ny = i + dx, j + dy
                        if 0 <= nx < n and 0 <= ny < m and board[nx][ny] != 1:
                            board[nx][ny] = 2

    # 안전지대의 개수를 카운트
    for i in range(n):
        for j in range(m):
            if board[i][j] == 0:
                answer += 1

    return answer


