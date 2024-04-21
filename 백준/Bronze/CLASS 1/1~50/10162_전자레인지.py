timeList = [300, 60, 10]
arr = []

T = int(input())

if T % 10 != 0:
    print(-1)
else:
    for i in timeList:
        arr.append(T // i)
        T = T % i
    print(' '.join(map(str, arr)))
