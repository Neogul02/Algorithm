map = [list(map(int, input().split())) for _ in range(9)]
max = -1;
for i in range(9):
    for j in range(9):
        if( max < map[i][j] ):
            max = map[i][j]
            x = i + 1
            y = j + 1
print(max)
print(x, y)