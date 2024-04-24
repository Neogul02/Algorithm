N = int(input())
L = [ord(c) - 64 - 32 for c in input()]
result = sum(L[i] * (31 ** i) for i in range(N))
print(result)