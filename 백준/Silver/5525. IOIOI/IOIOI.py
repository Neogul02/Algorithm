N = int(input())
M = int(input())
S = input()

cnt = 0
i = 0
pattern = 0

while i < M - 1:
    if S[i:i+3] == 'IOI':
        pattern += 1
        if pattern == N:
            cnt += 1
            pattern -= 1
        i += 2
    else:
        pattern = 0
        i += 1

print(cnt, end='')