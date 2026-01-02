T = int(input())

for t in range(1, T + 1):
    N, M = map(int, input().split())
    A_arr = list(map(int, input().split()))
    B_arr = list(map(int, input().split()))

    if N < M:
        short_arr = A_arr
        long_arr = B_arr
    else:
        short_arr = B_arr
        long_arr = A_arr
    
    new_arr = []

    for k in range(len(long_arr) - len(short_arr) + 1):
        current_sum = 0
        for i in range(len(short_arr)):
            current_sum += short_arr[i] * long_arr[k + i]
        new_arr.append(current_sum)

    print(f"#{t} {max(new_arr)}")