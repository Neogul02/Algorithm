N = int(input())
arr = []

for i in range(N):
    arr.append(f'{str(i+1)}. {input()}')

for i in range(N):
    print(arr[i])