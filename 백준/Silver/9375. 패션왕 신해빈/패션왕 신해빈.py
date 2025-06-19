import sys

def test(n):
    if n == 0:
        return 0
    clothes = {}
    for _ in range(n):
        _, category = sys.stdin.readline().rstrip().split()
        clothes[category] = clothes.get(category, 0) + 1

    count = 1
    for num in clothes.values():
        count *= (num + 1)
    return count - 1

t = int(sys.stdin.readline())
for _ in range(t):
    print(test(int(sys.stdin.readline().rstrip())))