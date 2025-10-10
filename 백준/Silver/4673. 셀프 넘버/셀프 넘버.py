def d(n):
    return n + sum(map(int, str(n)))

self_num = set()
for i in range(1, 10001):
    self_num.add(d(i))
    if i not in self_num:
        print(i)