N = int(input())
words = input().split(' ')

for i in range(N):
    words[i] = words[i]+'DORO'
print(' '.join(words), end='')