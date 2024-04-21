N, M = map(int, input().split())
print((N-M) if(N-M) > 0 else -(N-M) if(N-M) < 0 else 0)  