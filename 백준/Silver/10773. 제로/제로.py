K = int(input())
arr = []
for _ in range(K):
    N = int(input())
    if N == 0: arr.pop()
    else: arr.append(N)
print(sum(arr))