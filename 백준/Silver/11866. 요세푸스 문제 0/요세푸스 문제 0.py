import sys

n, k = map(int, sys.stdin.readline().split())

queue = list(range(1, n + 1)) # [1, 2, 3, 4, 5, 6, 7...]
result = []
idx = 0

while queue:
    idx = (idx + k - 1) % len(queue)
    result.append(str(queue.pop(idx)))

print("<" + ", ".join(result) + ">")