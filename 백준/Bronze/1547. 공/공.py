M = int(input())

arr = [1,0,0]
for i in range(M):
    X, Y = map(int, input().split())
    arr[X-1], arr[Y-1] = arr[Y-1], arr[X-1]

for i in arr:
    if i == 1:
        print(arr.index(i)+1)