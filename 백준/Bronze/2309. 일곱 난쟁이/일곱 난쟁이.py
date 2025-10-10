short_peoples = [int(input()) for i in range(9)]

for i in range(9):
    for j in range(i + 1, 9):
        if sum(short_peoples) - (short_peoples[i] + short_peoples[j]) == 100:
            a, b = short_peoples[i], short_peoples[j]
            break
peoples = [x for x in short_peoples if x != a and x != b]

for p in sorted(peoples):
    print(p)