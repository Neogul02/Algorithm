N = int(input())
arr = []
for i in range(N):
    num = int(input())
    arr.append(num)
arr.sort()
for i in arr:
    print(i)