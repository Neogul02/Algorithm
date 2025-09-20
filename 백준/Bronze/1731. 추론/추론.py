N = int(input())
arr =[0]*N
for i in range(N):
    arr[i] = int(input())

if arr[1]-arr[0] == arr[2]-arr[1]:
    print(arr[N-1]+(arr[1]-arr[0]))
else :
    print(arr[N-1]*(arr[1]//arr[0]))