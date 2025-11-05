import itertools

while True:
    array = list(map(int, input().split()))

    k = array[0]
    S = array[1:]

    if k == 0:
        break

    for i in itertools.combinations(S, 6):
        print(*i)
    print()