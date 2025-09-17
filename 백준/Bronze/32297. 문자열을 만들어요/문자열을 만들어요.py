N = int(input())

S = input()
condition = False
for i in range(N):
    if S[i:i+4] == 'gori':
        condition = True
print('YES' if condition else 'NO')