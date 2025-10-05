P = int(input())

for _ in range(P):
    N, D, A, B, F = map(float, input().split())
    
    time = D / (A + B)
    distance = F * time
    print(f"{int(N)} {distance:.6f}")