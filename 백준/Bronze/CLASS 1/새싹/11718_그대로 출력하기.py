for _ in range(100):
    try:
        print(input())
    except EOFError:
        break


import sys

for i in sys.stdin:
    print(i, end='')