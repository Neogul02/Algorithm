T = int(input())
for tc in range(T):
    N = int(input())
    prices = list(map(int, input().split()))
    profit = 0
    max_price = 0
    for p in reversed(prices):
        if p < max_price:
            profit += max_price - p
        else:
            max_price = p
    print(f"#{tc+1} {profit}")