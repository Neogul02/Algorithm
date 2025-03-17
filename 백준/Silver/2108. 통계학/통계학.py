import sys

N = int(sys.stdin.readline())

arr = []
for _ in range(N):
    arr.append(int(sys.stdin.readline()))

# 산술평균
print(round(sum(arr) / N))

# 중앙값
arr.sort()
print(arr[N // 2])

# 최빈값
frequency = {}
for num in arr:
    if num in frequency:
        frequency[num] += 1
    else:
        frequency[num] = 1

max_freq = max(frequency.values())
mode_list = [num for num, freq in frequency.items() if freq == max_freq]

mode_list.sort()
if len(mode_list) > 1:
    print(mode_list[1])
else:
    print(mode_list[0])

# 범위
print(arr[-1] - arr[0])