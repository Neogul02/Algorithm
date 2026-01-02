def rotate(matrix):
    N = len(matrix)
    ret = [[0] * N for _ in range(N)]

    for r in range(N):
        for c in range(N):
            ret[c][N - 1 - r] = matrix[r][c]
    return ret

T = int(input())

for test_case in range(1, T + 1):
    N = int(input())
    arr = [input().split() for _ in range(N)]

    arr_90 = rotate(arr)
    arr_180 = rotate(arr_90)
    arr_270 = rotate(arr_180)

    print(f"#{test_case}")
    for i in range(N):
        row_90 = "".join(arr_90[i])
        row_180 = "".join(arr_180[i])
        row_270 = "".join(arr_270[i])

        print(f"{row_90} {row_180} {row_270}")
