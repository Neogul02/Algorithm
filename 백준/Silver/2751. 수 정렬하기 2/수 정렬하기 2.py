import sys

def input():
    return sys.stdin.readline()

N = int(input())
arr = sorted(int(input()) for _ in range(N))

for i in arr : print(i)
