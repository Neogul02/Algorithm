from collections import deque

T = int(input())

for _ in range(T):
    p = input()
    n = int(input())
    arr_input = input().strip()[1:-1]
    arr = deque(arr_input.split(',')) if arr_input else deque()
    reverse = False
    error = False

    for cmd in p:
        if cmd == 'R':
            reverse = not reverse
        elif cmd == 'D':
            if not arr or arr == deque(['']):
                error = True
                break
            if reverse:
                arr.pop()
            else:
                arr.popleft()

    if error:
        print('error')
    else:
        if reverse:
            arr.reverse()
        print('[' + ','.join(arr) + ']')
