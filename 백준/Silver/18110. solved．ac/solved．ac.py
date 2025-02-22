import sys

def roundUp(num):
    if (num - int(num)) >= 0.5: # 0.5 이상이면 올림
        return int(num) + 1
    else: # 0.5 미만이면 내림
        return int(num)

n = int(sys.stdin.readline().rstrip())

if n == 0:
    print(0)
else:
    arr = sorted(int(sys.stdin.readline().rstrip()) for _ in range(n))

    arr.sort()
    border = roundUp(n * 0.15)

    print(roundUp(sum(arr[border:n - border]) / len(arr[border:n - border])))