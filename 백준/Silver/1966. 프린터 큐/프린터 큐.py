t = int(input())

for _ in range(t):
    n, m = map(int, input().split())
    data = list(map(int, input().split()))
    result = 1

    while True:
        if data[0] == max(data):
            if m == 0:
                break
            data.pop(0)
            result += 1
        else:
            data.append(data.pop(0))

        m = (m - 1) % len(data)

    print(result)