import sys
input = sys.stdin.readline

arr = [[int(input()), i+1] for i in range(8)]
arr.sort(key=lambda x: x[0], reverse=True)

sum = 0
idx = []

for i in range(5):
    sum += arr[i][0]
    idx.append(arr[i][1])
    
idx.sort()

print(sum)
for i in range(5): print(idx[i],end=' ')
