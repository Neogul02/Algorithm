import sys

K, N = map(int, input().split())
lan = [int(sys.stdin.readline()) for _ in range(K)]
start = 1
end = max(lan)

while start <= end:
    mid = (start + end) // 2  # 중간 찾기
    lines = 0
    for i in lan:
        lines += i // mid

    if lines >= N:
        start = mid + 1
    else:
        end = mid - 1
print(end)