ans = {input()[0] for _ in range(int(input()))}
alpha = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
idx = 0

while idx < len(alpha) and alpha[idx] in ans:
    idx += 1

print(idx)