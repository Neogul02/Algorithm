N = int(input())
M = int(input())
S = input()

ioi = 'IO' * N + 'I'

cnt = 0
for i in range(M):
    tmp = S[i:i+2*N+1]
    if tmp == ioi:
        cnt += 1
print(cnt)