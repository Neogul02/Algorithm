N = int(input())
S = list(input())
for i in range(N):
  if S[i] == 'l':
    S[i] = 'L'
  else:
    S[i] = 'i'
print(''.join(S))