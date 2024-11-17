N = int(input())
total = 1

for i in range(N):
    total += i * 6
    if N <= total:
        print(i + 1)
        break