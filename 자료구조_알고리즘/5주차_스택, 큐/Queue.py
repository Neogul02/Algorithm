N = int(input())
arr = []
heap = []


for _ in range(N):
    X = int(input())
    arr.append(X)

for i in range(N):
    if i != 0:
        heap.append(arr[i])