S, D = map(int, input().split())

if (S + D) % 2 != 0 or (S - D) % 2 != 0:
    print(-1)
else:
    x = (S + D) // 2
    y = (S - D) // 2
    if x < 0 or y < 0:
        print(-1)
    else:
        print(x, y)
