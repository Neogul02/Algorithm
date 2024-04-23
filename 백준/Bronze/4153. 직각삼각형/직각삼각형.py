while True:
    A, B, C = sorted(int(x)**2 for x in input().split())
    if A + B + C == 0:
        break

    if A + B == C:
        print("right")
    else:
        print("wrong")