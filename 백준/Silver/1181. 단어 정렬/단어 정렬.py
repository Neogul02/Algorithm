N = int(input())
arr = [input() for _ in range(N)]
arr = sorted(set(arr))
arr.sort(key=len)

for i in arr:
    print(i)