import sys

m = int(sys.stdin.readline())
S = set()

for _ in range(m):
    command = sys.stdin.readline().strip().split()

    if command[0] == "all":
        S = set(range(1, 21))
    elif command[0] == "empty":
        S.clear()
    else:
        func, x = command[0], int(command[1])
        if func == "add":
            S.add(x)
        elif func == "remove":
            S.discard(x)
        elif func == "check":
            print(1 if x in S else 0)
        elif func == "toggle":
            S.discard(x) if x in S else S.add(x)