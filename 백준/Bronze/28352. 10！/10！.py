N = int(input())
fac = 1
for i in range(1, N + 1):
    fac *= i
print(fac//(60*60*24*7))
