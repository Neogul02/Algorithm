T = int(input())
count_0 = [0] * 41
count_1 = [0] * 41

count_0[0], count_1[0] = 1,0
count_0[1], count_1[1]  = 0,1

for i in range(2, 41):
    count_0[i] = count_0[i-1] + count_0[i-2]
    count_1[i] = count_1[i-1] + count_1[i-2]

for _ in range(T):
    N = int(input())
    print(f"{count_0[N]} {count_1[N]}")