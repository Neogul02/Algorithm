arr = [[0] *100 for _ in range(100)]

for ask in range(int(input())):
    row, col = map(int, input().split())  # row : 열, col : 행
    for i in range(10):
        for j in range(10):
            arr[row + i][col + j] = 1

sum = 0
for a in range(100):
    for b in range(100):
        if arr[a][b] == 1:
            sum += 1

print(sum)