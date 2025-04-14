import sys
input = sys.stdin.readline

def func(x):
    if x <= 3:
        return [0, 1, 2, 4][x]
    return func(x - 1) + func(x - 2) + func(x - 3)

t = int(input())
for _ in range(t):
    print(func(int(input())))