n = int(input())
sequence = [int(input()) for _ in range(n)]

stack = []
answer = []
current = 1
possible = True

for num in sequence:
    while current <= num:
        stack.append(current)
        answer.append('+')
        current += 1
    if stack[-1] == num:
        stack.pop()
        answer.append('-')
    else:
        possible = False
        break

if possible:
    for i in answer:
        print(i)
else:
    print('NO')