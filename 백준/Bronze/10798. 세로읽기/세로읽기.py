arr = []
max_str = 0

for _ in range(5):
    arr.append(input().strip())

for i in arr:
    if len(i) > max_str:
        max_str = len(i)

for i in range(max_str):
    for j in range(len(arr)):
            try:
                print(arr[j][i], end='')
            except:
                pass