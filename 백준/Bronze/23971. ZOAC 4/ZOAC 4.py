import sys
input = sys.stdin.readline

H, W, N, M = map(int, input().split())

rows_count = (H - 1) // (N + 1) + 1
cols_count = (W - 1) // (M + 1) + 1

print(rows_count * cols_count)