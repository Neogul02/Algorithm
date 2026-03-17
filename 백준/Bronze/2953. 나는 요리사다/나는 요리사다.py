arr = [list(map(int, input().split())) for _ in range (5)]
maxSum = 0
maxIdx = 0

for idx, score in enumerate(arr):
    tempSum = sum(score)
    
    if(maxSum < tempSum):
        maxSum = tempSum
        maxIdx = idx+1
        
print(f'{maxIdx} {maxSum}')