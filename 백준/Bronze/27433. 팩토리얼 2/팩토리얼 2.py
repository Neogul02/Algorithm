import sys
input = sys.stdin.readline

N = int(input())
f = 1
for i in range(1,N+1):
    f *= i
print(f)
