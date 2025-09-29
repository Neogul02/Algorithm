python
N = int(input())
cnt = 0
for i in range(1, N + 1):
    s = str(i)
    cnt += s.count('3') + s.count('6') + s.count('9')
print(cnt)