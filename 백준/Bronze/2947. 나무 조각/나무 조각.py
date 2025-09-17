arr = list(map(int, input().split()))
n = len(arr)

while arr != [1, 2, 3, 4, 5]:
    for i in range(n-1):
        if arr[i] > arr[i+1]:
            arr[i], arr[i+1] = arr[i+1], arr[i]
            print(' '.join(map(str, arr)))