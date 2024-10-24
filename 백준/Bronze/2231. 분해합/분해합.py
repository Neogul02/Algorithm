n = int(input())
for i in range(1, n + 1):
    if sum(map(int, str(i))) + i == n:
        print(i)
        break
else:
    print(0)