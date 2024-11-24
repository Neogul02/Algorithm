N = int(input())
n = 0
num = 0
while True:
    n += 1
    if '666' in str(n):
        num += 1
        if num == N:
            break
print(n)