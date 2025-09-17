a, b, c = map(int, input().split())

if a + b == c:
    print(f"{a}+{b}={c}")
elif a - b == c:
    print(f"{a}-{b}={c}")
elif a * b == c:
    print(f"{a}*{b}={c}")
elif b != 0 and a / b == c:
    print(f"{a}/{b}={c}")
elif a == b + c:
    print(f"{a}={b}+{c}")
elif a == b - c:
    print(f"{a}={b}-{c}")
elif a == b * c:
    print(f"{a}={b}*{c}")
elif c != 0 and a == b / c:
    print(f"{a}={b}/{c}")