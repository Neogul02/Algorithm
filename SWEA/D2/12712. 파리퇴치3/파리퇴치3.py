T = int(input())

for tc in range(1, T + 1):
    N, M = map(int, input().split())
    A = [list(map(int, input().split())) for _ in range(N)]
    best = 0
    for r in range(N):
        for c in range(N):
            # '+' 모양
            s_plus = A[r][c]
            for d in range(1, M):
                if 0 <= r - d < N:
                    s_plus += A[r - d][c]
                if 0 <= r + d < N:
                    s_plus += A[r + d][c]
                if 0 <= c - d < N:
                    s_plus += A[r][c - d]
                if 0 <= c + d < N:
                    s_plus += A[r][c + d]
            if s_plus > best:
                best = s_plus
            # 'x' 모양
            s_x = A[r][c]
            for d in range(1, M):
                if 0 <= r - d < N and 0 <= c - d < N:
                    s_x += A[r - d][c - d]
                if 0 <= r - d < N and 0 <= c + d < N:
                    s_x += A[r - d][c + d]
                if 0 <= r + d < N and 0 <= c - d < N:
                    s_x += A[r + d][c - d]
                if 0 <= r + d < N and 0 <= c + d < N:
                    s_x += A[r + d][c + d]
            if s_x > best:
                best = s_x
    print(f"#{tc} {best}")
