for _ in range(int(input())):
    H, W, N = map(int, input().split())  # H: 층수, W: 방수, N: 몇 번째 손님
    arr = [[0 for _ in range(H)] for _ in range(W)]

    for i in range(W):
        for j in range(H):
            arr[i][j] = (j+1)*100 + (i+1) # [0

    arr = sum(arr, [])
    print(arr[N-1])
