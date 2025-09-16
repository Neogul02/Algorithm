def star(arr, x, y, n):
    if n == 1:
        arr[x][y] = '*'
        return
    size = n // 3
    for dx in range(3):
        for dy in range(3):
            if dx == 1 and dy == 1:
                continue
            star(arr, x + dx * size, y + dy * size, size)

if __name__ == "__main__":
    N = int(input())
    arr = [[' ' for _ in range(N)] for _ in range(N)]
    star(arr, 0, 0, N)
    for row in arr:
        print(''.join(row))