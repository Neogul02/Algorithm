N, X = map(int, input().split())
A = list(map(int, input().split()))

result = [str(i) for i in A if i < X]
print(' '.join(result))

