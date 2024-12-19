import sys
input = sys.stdin.readline

n = int(input())
stack = []

for _ in range(n):
    command = input().split()
    cmd = command[0]

    if cmd == 'push':
        stack.append(command[1])
    elif cmd == 'pop':
        print(stack.pop() if stack else -1)
    elif cmd == 'size':
        print(len(stack))
    elif cmd == 'empty':
        print(1 if not stack else 0)
    elif cmd == 'top':
        print(stack[-1] if stack else -1)