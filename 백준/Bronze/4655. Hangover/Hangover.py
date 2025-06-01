import sys
input = sys.stdin.readline
while True:
    c = float(input().strip())
    if c == 0.00:
        break

    overhang = 0.0
    cards = 0

    while overhang < c:
        cards += 1
        overhang += 1 / (cards + 1)  # 1/2 .. 1/3 .. 1/4 .. 1/n

    print(f"{cards} card(s)")