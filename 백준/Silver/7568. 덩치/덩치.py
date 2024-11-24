N = int(input())
arr = []

for _ in range(N):
    weight, height = map(int, input().split())
    arr.append((weight, height))

for i in arr:
    rank = 1
    for j in arr:
        if i[0] < j[0] and i[1] < j[1]:
                rank += 1
    print(rank, end = " ")
